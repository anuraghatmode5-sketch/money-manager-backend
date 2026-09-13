package in.anurag.moneymanager.Controller;

import in.anurag.moneymanager.Dto.AuthDTO;
import in.anurag.moneymanager.Dto.ProfileDTO;
import in.anurag.moneymanager.Service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileservice;

    @PostMapping("/register")
    public ResponseEntity<ProfileDTO> registerProfile(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO registerProfile = profileservice.registerProfile(profileDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerProfile);
    }

    @GetMapping("/activate")
    public ResponseEntity<String> activateProfile(@RequestParam String token) {
        boolean isActivated = profileservice.activateProfile(token);

        if (isActivated) {
            return ResponseEntity.ok("Profile activated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Activation token not found or already used");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody AuthDTO authDTO) {
        try {
            if (!profileservice.isAccountActive(authDTO.getEmail())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(Map.of(
                                "message", "Account is not active. Please activate your account first."
                        ));
            }

            Map<String, Object> response =
                    profileservice.authenticateAndGenerateToken(authDTO);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDTO> getPublicProfile() {
        ProfileDTO profileDTO = profileservice.getPublicProfile(null);
        return ResponseEntity.ok(profileDTO);
    }


}
