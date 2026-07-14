# Changelog

All notable changes to this project will be documented in this file.

---

# Version 0.1.0 - Sprint 1 (30 June 2026)

## Added

- Created project folder structure.
- Added README.md.
- Added .gitignore.
- Completed Requirement Analysis.
- Added Application Flow documentation.
- Added Database Design documentation.
- Added API Design folder.
- Added UI Planning document.
- Initialized Git repository.
- Created GitHub repository.
- Completed first Git commit.

---

# Version 0.2.0 - Sprint 2

## Database Design

### Added

- Designed Role table.
- Designed User table.
- Designed Student table.
- Designed Trainer table.
- Defined relationships between Role, User, Student, and Trainer.

---

# Version 0.3.0 - Sprint 3

## Database Design

### Added

- Designed Course table.
- Designed Batch table.
- Designed Enrollment table.
- Designed Attendance table.
- Designed Company table.
- Designed Placement Drive table.
- Designed Placement table.
- Designed Resume table.
- Designed Notification table.

### Completed

- Completed Version 1 Database Design.
- Finalized core database architecture.

---

# Version 0.4.0 - Sprint 4

## Role Module

### Added

- Implemented Role Entity.
- Implemented Role Repository.
- Implemented Role DTOs.
- Implemented Role Mapper.
- Implemented Role Service.
- Implemented Role Controller.
- Implemented Global Exception Handling.
- Completed Role CRUD APIs.
- Added Role API Documentation.
- Tested APIs using Postman.
- Verified data persistence in MySQL.

### Role APIs

```
POST    /api/v1/roles
GET     /api/v1/roles
GET     /api/v1/roles/{id}
PUT     /api/v1/roles/{id}
DELETE  /api/v1/roles/{id}
```

### Sample Request

```json
{
    "roleName": "ADMIN",
    "description": "System Administrator",
    "active": true
}
```

### Sample Response

```json
{
    "id": 1,
    "roleName": "ADMIN",
    "description": "System Administrator",
    "active": true,
    "createdAt": "2026-07-01T17:37:48.0504571",
    "updatedAt": "2026-07-01T17:37:48.0504571"
}
```

---

# Version 0.5.0 - Sprint 5

## User Module

### Added

- Implemented User Entity.
- Implemented User Repository.
- Implemented User DTOs.
- Implemented User Mapper.
- Implemented User Service.
- Implemented User Controller.
- Implemented User CRUD APIs.
- Established User ↔ Role relationship.
- Password encryption using BCrypt.
- Added User API Documentation.
- Tested APIs using Postman.
- Verified User persistence in MySQL.

---

# Version 0.6.0 - Sprint 6

## Authentication & Security

### Added

- Configured Spring Security.
- Added PasswordConfig.
- Added AuthenticationConfig.
- Implemented CustomUserDetailsService.
- Added JwtService.
- Configured JWT Secret and Expiration.
- Implemented Login API.
- Generated real JWT Token.
- Integrated BCrypt password verification.
- Added Authentication API Documentation.
- Tested Login API using Postman.
- Verified JWT Token generation.

### Authentication API

```
POST /api/v1/auth/login
```

### Sample Request

```json
{
    "email": "shankar@gmail.com",
    "password": "Admin@123"
}
```

### Sample Response

```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "tokenType": "Bearer",
    "userId": 1,
    "firstName": "Shankar",
    "lastName": "Pagad",
    "email": "shankar@gmail.com",
    "role": "ADMIN"
}
## Version 0.7.0 - Sprint 7

### Added

- Implemented Student Entity
- Implemented Student Repository
- Implemented Student DTOs
- Implemented Student Mapper
- Implemented Student Service
- Implemented Student Controller
- Added Student ↔ User relationship
- Completed Student CRUD APIs
- Added Student API documentation
- Tested Student APIs using Postman 


---
---

## Version 0.8.0 - Sprint 8

### Added

- Implemented Trainer Entity
- Implemented Trainer Repository
- Implemented Trainer DTOs
- Implemented Trainer Mapper
- Implemented Trainer Service
- Implemented Trainer Controller
- Added Trainer ↔ User relationship
- Completed Trainer CRUD APIs
- Protected Trainer APIs using JWT Authentication
- Added Trainer API Documentation
- Tested Trainer APIs using Postman

### Completed
---

## Version 1.0.0 - Sprint 10

### Course Module

#### Added

- Implemented Course Entity
- Implemented Course Repository
- Implemented Course DTOs
- Implemented Course Mapper
- Implemented Course Service
- Implemented Course Service Implementation
- Implemented Course Controller
- Completed Course CRUD APIs
- Protected Course APIs using JWT Authentication
- Added Course API Documentation
- Tested Course APIs using Postman

#### Completed

- Course CRUD implementation
- Database persistence verification
- API validation
- JWT secured endpoints



## Version 1.1.0 - Sprint 11

### Batch Module

#### Added

- Implemented Batch Entity
- Implemented Batch Repository
- Implemented Batch DTOs
- Implemented Batch Mapper
- Implemented Batch Service
- Implemented Batch Controller
- Added Batch ↔ Course relationship
- Added Batch ↔ Trainer relationship
- Completed Batch CRUD APIs
- Added Batch API Documentation
- Tested Batch APIs using Postman

#### Completed

- Batch CRUD implementation
- Database persistence verification
- JWT secured endpoints

# Upcoming

## Authentication

- JWT Authentication Filter
- JWT Validation
- Protected APIs
- Role-Based Authorization
- Refresh Token

## Modules

- Student Module
- Trainer Module
- Course Module
- Batch Module
- Enrollment Module
- Attendance Module
- Company Module
- Placement Module
- Resume Module
- Notification Module

## Documentation

- ER Diagram
- SQL Schema
- Swagger / OpenAPI Documentation

## DevOps

- Docker
- GitHub Actions CI/CD
- AWS / Render Deployment

## Frontend

- React Application
- Dashboard
- Authentication UI