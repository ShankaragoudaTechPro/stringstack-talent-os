# User API Documentation

## Module

User Management

---

## Description

This module is used to manage system users such as Admin, HR, Trainer, and Student.

---

## Base URL

```
http://localhost:8080/api/v1/users
```

---

# 1. Create User

## Method

POST

## Endpoint

```
POST /api/v1/users
```

## Request Body

```json
{
    "firstName": "Shankar",
    "lastName": "Pagad",
    "email": "shankar@gmail.com",
    "phone": "9876543210",
    "password": "Admin@123",
    "roleId": 1,
    "active": true
}
```

## Success Response

**Status:** 201 Created

```json
{
    "id": 1,
    "firstName": "Shankar",
    "lastName": "Pagad",
    "email": "shankar@gmail.com",
    "phone": "9876543210",
    "role": "ADMIN",
    "active": true,
    "createdAt": "2026-07-02T10:30:00",
    "updatedAt": "2026-07-02T10:30:00"
}
```

---

# 2. Get All Users

## Method

GET

## Endpoint

```
GET /api/v1/users
```

## Success Response

**Status:** 200 OK

```json
[
  {
    "id": 1,
    "firstName": "Shankar",
    "lastName": "Pagad",
    "email": "shankar@gmail.com",
    "role": "ADMIN",
    "active": true
  }
]
```

---

# 3. Get User By Id

## Method

GET

## Endpoint

```
GET /api/v1/users/{id}
```

## Example

```
GET /api/v1/users/1
```

---

# 4. Update User

## Method

PUT

## Endpoint

```
PUT /api/v1/users/{id}
```

## Request Body

```json
{
    "firstName": "Shankar",
    "lastName": "Pagad",
    "email": "shankar@gmail.com",
    "phone": "9876543210",
    "password": "Admin@123",
    "roleId": 2,
    "active": true
}
```

---

# 5. Delete User

## Method

DELETE

## Endpoint

```
DELETE /api/v1/users/{id}
```

## Example

```
DELETE /api/v1/users/1
```

---

# Validation Rules

- First Name is required.
- Last Name is required.
- Email must be unique.
- Email must be a valid email address.
- Phone number must be unique.
- Password is required.
- Password must contain at least 8 characters.
- Role ID must exist in the Role table.
- Active cannot be null.

---

# Database Table

users

| Column | Type |
|----------|----------|
| id | BIGINT |
| first_name | VARCHAR(100) |
| last_name | VARCHAR(100) |
| email | VARCHAR(150) |
| phone | VARCHAR(20) |
| password | VARCHAR(255) |
| role_id | BIGINT (FK) |
| active | BOOLEAN |
| created_at | DATETIME |
| updated_at | DATETIME |

---

# Relationship

```
Role (1)
   │
   │
   └───────────────< User (Many)
```

One Role can be assigned to multiple Users.

---

# Future Enhancements

- Login API
- JWT Authentication
- Forgot Password
- Change Password
- Reset Password
- User Profile
- Search User
- Pagination & Sorting
- Filter by Role
- Activate/Deactivate User