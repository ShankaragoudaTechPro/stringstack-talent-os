# Role Table

## Purpose

The Role table stores all user roles available in the system.

Every user must belong to one role.

---

## Table Name

role

---

## Columns

| Column Name | Data Type | Constraints | Description |
|-------------|----------|-------------|-------------|
| id | BIGINT | Primary Key, Auto Increment | Unique Role ID |
| role_name | VARCHAR(50) | UNIQUE, NOT NULL | Role Name |
| description | VARCHAR(255) | NULL | Role Description |
| created_at | TIMESTAMP | NOT NULL | Record Created Time |
| updated_at | TIMESTAMP | NOT NULL | Record Updated Time |

---

## Sample Data

| id | role_name |
|----|-----------|
| 1 | ADMIN |
| 2 | TRAINER |
| 3 | STUDENT |
| 4 | HR |

---

## Relationships

One Role

↓

Many Users

(1:N)

---

## Business Rules

- Role Name must be unique.
- Role cannot be empty.
- Every user must have exactly one role.
- Role should not be deleted if users are assigned.

---

## Future Roles

SUPER_ADMIN

CONTENT_MANAGER

INTERVIEW_PANEL

PLACEMENT_MANAGER

---

Status

Ready for Database Creation