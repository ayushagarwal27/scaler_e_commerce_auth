# E-Commerce Authentication Service

A Spring Boot based authentication service for e-commerce applications that provides user management, role-based access control, and session management.

## 📋 Features

- **User Authentication**
  - User signup with email and password
  - Secure login with JWT token-based authentication
  - Token validation
  - Logout functionality

- **Role Management**
  - Create custom roles
  - Assign roles to users
  - Role-based access control

- **Security**
  - Password encryption using BCrypt
  - JWT token-based authentication
  - Session management with status tracking
  - Exception handling for common auth scenarios

## 🛠️ Technical Architecture

### Models
- `User`: Core user entity with email, password, and roles
- `Role`: Represents user roles for authorization
- `Session`: Tracks user sessions with token and status
- `BaseModel`: Common attributes for all entities

### Controllers
- `AuthController`: Handles authentication operations (signup, login, validation, logout)
- `RoleController`: Manages role creation
- `UserController`: Manages user operations and role assignments

### Services
- `AuthService`: Implements authentication logic
- `UserService`: Handles user-related operations
- `RoleService`: Manages role creation and assignment

### Repositories
- JPA repositories for data access to User, Role, and Session entities

### DTOs (Data Transfer Objects)
- Request/Response objects for all API endpoints
- Separation of concerns between internal models and external API contracts

### Exception Handling
- Global exception handling with `@ControllerAdvice`
- Custom exceptions for authentication scenarios:
  - `UserAlreadyExistsException`
  - `UserDoesNotExitsException` 
  - `PasswordIncorrectException`
  - `IncorrectTokenException`

### Security Configuration
- Spring Security integration
- BCrypt password encoding
- JWT for stateless authentication

## 🔄 API Endpoints

### Authentication

Repository Structure
Each entity has its own repository interface extending JPA Repository:

UserRepo: Finds users by email
RoleRepo: Finds roles by IDs
SessionRepository: Manages user sessions

Service Layer Implementation
Services follow the interface-implementation pattern:

Interfaces define contracts (AuthService, UserService, RoleService)
Implementations provide concrete logic (AuthServiceImpl, etc.)

## Dependencies

- Spring Boot
- Spring Security
- Spring Data JPA
- Lombok for reducing boilerplate code
- BCrypt for password hashing
- JJWT (Java JWT) for token handling
- Jakarta Persistence for ORM