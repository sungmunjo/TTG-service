# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

TTG-service is a Spring Boot REST API backend for "TalkTogether", a collaborative communication platform. It handles user authentication (JWT-based) and user management.

- **Language:** Java 17
- **Framework:** Spring Boot 3.2.3
- **Database:** PostgreSQL (prod), H2 (tests)
- **Build tool:** Gradle
- **Server port:** 20301

## Commands

All commands run from `talkTogether/` directory:

```bash
./gradlew build        # Build
./gradlew bootRun      # Run the application
./gradlew test         # Run all tests
./gradlew clean build  # Clean and rebuild
./gradlew bootJar      # Build executable JAR
```

## Architecture

### Package Structure

```
com.kr.moon.talktogether/
├── auth/                  # JWT authentication (login endpoint, filter, token provider)
├── config/                # SecurityConfig (JWT filter chain, CORS, public routes)
│                          # WebConfig (CORS for /v1/**)
├── user/
│   ├── entity/            # Users.java (active), Account.java (legacy, unused)
│   ├── repository/        # UsersRepository (active), AccountRepository (unused)
│   └── user_info/
│       ├── controller/    # AccountInfoController — GET/POST /v1/users
│       └── service/       # One subdirectory per operation (get_user_info, get_user_list, post_save_user)
│                          # Each has Service + In/Out DTOs
└── global/                # Empty, reserved for future cross-cutting concerns
```

### API Endpoints

| Method | Path | Auth | Description |
|--------|------|------|-------------|
| POST | `/api/auth/login` | Public | Login, returns JWT |
| POST | `/v1/users` | Public | Register new user |
| GET | `/v1/users` | Public | List all users |
| GET | `/swagger-ui/**` | Public | Swagger UI |
| GET | `/v3/api-docs/**` | Public | OpenAPI spec |

All other endpoints require a valid JWT `Authorization: Bearer <token>` header.

### Authentication Flow

1. Client posts credentials to `/api/auth/login`
2. `AuthService` validates user via `UsersRepository`
3. `JwtProvider` generates a signed HS256 token
4. `JwtAuthFilter` intercepts subsequent requests and validates tokens before `UsernamePasswordAuthenticationFilter`

### Service Layer Pattern

Each user operation follows a consistent structure:
- `UserInfo<Operation>.java` — service class with business logic
- `UserInfo<Operation>In.java` — input DTO
- `UserInfo<Operation>Out.java` — output DTO

### Known Issues

- `JwtProvider.validateToken()` always returns `true` — token validation is not fully implemented
- Passwords are stored and compared in plain text — no hashing
- `Account` entity and `AccountRepository` are legacy duplicates of `Users`/`UsersRepository` and can be removed
- CORS is configured redundantly in both `SecurityConfig` and `WebConfig`
