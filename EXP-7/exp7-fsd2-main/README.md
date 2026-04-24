# Experiment 7 - Role-Based Authorization in Spring Boot

This project demonstrates authentication and role-based authorization using Spring Security.

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Security
- Spring Data JPA
- H2 Database
- Maven

## Implemented Requirements

- Authentication using username/password
- Role assignment with `ROLE_USER` and `ROLE_ADMIN`
- Endpoint-level authorization
- Proper HTTP status handling:
  - `401 Unauthorized` when no/invalid authentication
  - `403 Forbidden` when role is insufficient
- Postman-friendly login endpoint for demonstration

## Demo Users

Users are seeded automatically at application startup (`DataInitializer`):

| Username | Password | Role |
|----------|----------|------|
| user1    | user123  | ROLE_USER |
| admin1   | admin123 | ROLE_ADMIN |

## Endpoint Access Rules

| Endpoint | Method | Access |
|----------|--------|--------|
| `/api/public/hello` | GET | Public |
| `/api/auth/login` | POST | Public |
| `/api/user/profile` | GET | USER, ADMIN |
| `/api/admin/dashboard` | GET | ADMIN |

## Run the Project

```bash
mvn spring-boot:run
```

App runs on:

`http://localhost:8080`

## Postman Test Flow

1. Public endpoint (no auth):
   - `GET http://localhost:8080/api/public/hello`
   - Expected: `200 OK`

2. Login:
   - `POST http://localhost:8080/api/auth/login`
   - Body:
     ```json
     {
       "username": "user1",
       "password": "user123"
     }
     ```
   - Expected: `200 OK` with `basicAuthToken` in response

3. USER accessing user endpoint:
   - `GET http://localhost:8080/api/user/profile`
   - Authorization: Basic Auth (`user1` / `user123`)
   - Expected: `200 OK`

4. USER accessing admin endpoint:
   - `GET http://localhost:8080/api/admin/dashboard`
   - Authorization: Basic Auth (`user1` / `user123`)
   - Expected: `403 Forbidden`

5. ADMIN accessing admin endpoint:
   - `GET http://localhost:8080/api/admin/dashboard`
   - Authorization: Basic Auth (`admin1` / `admin123`)
   - Expected: `200 OK`

6. No authentication on secured endpoint:
   - `GET http://localhost:8080/api/user/profile`
   - Expected: `401 Unauthorized`

## Suggested Screenshot Checklist

Store images inside `screenshots/`:

- `01-login-success.png`
- `02-user-endpoint-success.png`
- `03-admin-endpoint-success.png`
- `04-access-denied.png`
- `05-unauthorized-no-token.png` (recommended)
- `06-project-structure.png` (recommended)

## Tests

Run tests with:

```bash
mvn test
```

Covered test cases:

- Public endpoint accessible without auth
- `401` for missing authentication
- USER can access `/api/user/profile`
- USER gets `403` on `/api/admin/dashboard`
- ADMIN can access `/api/admin/dashboard`
- Login endpoint authenticates valid credentials
