# Changelog

All notable changes to this project will be documented in this file.

---

## Version 0.1.0 - Sprint 1 Day 1 (30 June 2026)

### Added

- Created project folder structure.
- Added README.md.
- Added .gitignore.
- Completed Requirement Analysis.
- Added Application Flow documentation.
- Added Database Design documentation.
- Added API Design folder.
- Added UI planning document.
- Initialized Git repository.
- Created GitHub repository.
- Completed first Git commit.

---

## Version 0.2.0 - Sprint 2

### Added

- Designed Role table.
- Designed User table.
- Designed Student table.
- Designed Trainer table.
- Defined relationships between Role, User, Student, and Trainer.

---

## Version 0.3.0 - Sprint 2

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

- Completed Version 1 database design.
- Finalized core database architecture.

Role-API.md

POST    /api/v1/roles

GET     /api/v1/roles

GET     /api/v1/roles/{id}

PUT     /api/v1/roles/{id}

DELETE  /api/v1/roles/{id}

Request 

{
    "roleName": "ADMIN",
    "description": "System Administrator",
    "active":true
}

Response 

{
    "id": 2,
    "roleName": "ADMIN",
    "description": " ",
    "active": true,
    "createdAt": "2026-07-01T17:37:48.0504571",
    "updatedAt": "2026-07-01T17:37:48.0504571"
}


## Version 0.4.0 - Sprint 4

### Added

- Completed User Module
- Implemented CRUD APIs
- Added Role ↔ User relationship
- Password encryption using BCrypt
- Added User API documentation
- Tested APIs using Postman

## Version 0.5.0 - Sprint 3

### Added

- Implemented Role Entity
- Implemented Role Repository
- Implemented Role DTOs
- Implemented Role Mapper
- Implemented Role Service
- Implemented Role Controller
- Completed Role CRUD APIs
- Tested APIs using Postman
- Verified data persistence in MySQL

---

## Upcoming

- ER Diagram
- SQL Schema
- Spring Boot Project Setup
- Authentication (JWT)
- Role-Based Authorization
- REST APIs
- React Frontend
- Testing
- Deployment