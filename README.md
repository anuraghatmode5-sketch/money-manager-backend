# Money Ledger — Backend

A full-stack personal finance management application backend built with Java and Spring Boot. It provides REST APIs for managing income, expenses, categories, user authentication, dashboard summaries, and transaction filtering.

## 🚀 Features

- **User Authentication:** Registration, login, and account activation.
- **JWT Security:** Token-based authentication using Spring Security and stateless session management.
- **Income Management:** Create and retrieve income records.
- **Expense Management:** Create, retrieve, and delete expense records.
- **Category Management:** Create, retrieve, and update transaction categories.
- **Dashboard:** Retrieve financial summaries for the dashboard.
- **Transaction Filtering:** Filter transactions using criteria such as type, date range, keyword, and sorting options.
- **Email Notifications:** Email-related functionality for account and application workflows.

## 🛠️ Tech Stack

- **Language:** Java 21
- **Framework:** Spring Boot
- **Security:** Spring Security, JWT
- **Persistence:** Spring Data JPA, Hibernate
- **Database:** MySQL, PostgreSQL
- **Build Tool:** Maven
- **Other:** REST APIs, JavaMail, Google Gmail API

## 📁 Project Structure

The backend follows a layered architecture to separate responsibilities:

- **Controller:** Handles HTTP requests and responses.
- **Service:** Contains application business logic.
- **Repository:** Handles database operations.
- **Entity / Model:** Represents persistent data.
- **DTO:** Transfers data between API layers.

## 🔌 API Overview

The API provides the following main endpoints:

| Feature | Endpoint | Method |
|---|---|---|
| Health check | `/status` | GET |
| Health check | `/health` | GET |
| Register | `/register` | POST |
| Activate account | `/activate` | GET |
| Login | `/login` | POST |
| User profile | `/profile` | GET |
| Categories | `/categories` | GET, POST |
| Categories by type | `/categories/{type}` | GET |
| Update category | `/categories/{categoryId}` | PUT |
| Dashboard summary | `/dashboard` | GET |
| Create income | `/incomes` | POST |
| Retrieve income | `/incomes` | GET |
| Delete income | `/incomes/{id}` | DELETE |
| Create expense | `/expenses` | POST |
| Retrieve expenses | `/expenses` | GET |
| Delete expense | `/expenses/{id}` | DELETE |
| Filter transactions | `/filter` | POST |

**Note:** These paths are relative to the configured server base URL. Protected endpoints require authentication.

## ⚙️ Getting Started

### Prerequisites

Install the following:

- Java Development Kit (JDK) 21
- Git
- MySQL or PostgreSQL
- Maven, or use the included Maven Wrapper

### 1. Clone the repository

```bash
git clone https://github.com/anuraghatmode5-sketch/money-manager-backend.git
cd money-manager-backend
```

### 2. Configure the application

Configure your database connection and the required application properties in your local configuration. Depending on your implementation, these may include:

- Database URL, username, and password
- JWT signing secret
- Email service credentials
- OAuth/Gmail API configuration, if required by the workflow

**Security:** Never commit real passwords, API keys, JWT secrets, or OAuth credentials to GitHub.

### 3. Run the application

On Windows:

```bash
mvnw.cmd spring-boot:run
```

On macOS/Linux:

```bash
./mvnw spring-boot:run
```

The application starts on the configured server port.

## 🔐 Authentication

The application uses Spring Security and JWT-based authentication to protect secured API endpoints. Authenticate through the login endpoint and provide the token in the format expected by the API when accessing protected resources.

## 🧪 API Testing

You can test the endpoints using Postman.

Test the main workflows:

1. Register and activate a user account.
2. Log in.
3. Access protected endpoints.
4. Create and retrieve income and expense records.
5. Manage transaction categories.
6. Retrieve dashboard data.
7. Filter transactions.

## 👨‍💻 Author

**Anurag Gajanan Hatmode**

- GitHub: [@anuraghatmode5-sketch](https://github.com/anuraghatmode5-sketch)

---

Built as a full-stack development project to practise REST API development, backend security, database integration, and application architecture.
