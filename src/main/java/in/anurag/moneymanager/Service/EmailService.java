package in.anurag.moneymanager.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.UserCredentials;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Collections;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${google.client.id}")
    private String clientId;

    @Value("${google.client.secret}")
    private String clientSecret;

    @Value("${google.refresh.token}")
    private String refreshToken;

    @Value("${gmail.from.email}")
    private String fromEmail;

    public void sendEmail(String to, String subject, String body) {
        try {
            GoogleCredentials credentials =
                    UserCredentials.newBuilder()
                            .setClientId(clientId)
                            .setClientSecret(clientSecret)
                            .setRefreshToken(refreshToken)
                            .build()
                            .createScoped(
                                    Collections.singleton(GmailScopes.GMAIL_SEND)
                            );

            Gmail gmailService =
                    new Gmail.Builder(
                            GoogleNetHttpTransport.newTrustedTransport(),
                            GsonFactory.getDefaultInstance(),
                            new HttpCredentialsAdapter(credentials)
                    )
                            .setApplicationName("Money Manager")
                            .build();

            Properties properties = new Properties();
            Session session = Session.getDefaultInstance(properties, null);

            MimeMessage email = new MimeMessage(session);

            email.setFrom(new InternetAddress(fromEmail));
            email.setRecipient(
                    RecipientType.TO,
                    new InternetAddress(to)
            );
            email.setSubject(subject);
            email.setContent(body, "text/html; charset=UTF-8");

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            email.writeTo(buffer);

            String encodedEmail =
                    Base64.getUrlEncoder()
                            .withoutPadding()
                            .encodeToString(buffer.toByteArray());

            Message message = new Message();
            message.setRaw(encodedEmail);

            gmailService.users()
                    .messages()
                    .send("me", message)
                    .execute();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}