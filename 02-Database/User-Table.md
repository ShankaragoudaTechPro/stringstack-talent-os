# User Table

## Purpose

The User table stores login credentials and common information for all users in the system.

Every Admin, Trainer, Student and HR will have one user account.

---

## Table Name

user

---

## Columns

| Column Name | Data Type | Constraints | Description |
|-------------|----------|-------------|-------------|
| id | BIGINT | Primary Key, Auto Increment | Unique User ID |
| first_name | VARCHAR(100) | NOT NULL | First Name |
| last_name | VARCHAR(100) | NULL | Last Name |
| email | VARCHAR(150) | UNIQUE, NOT NULL | Email Address |
| mobile | VARCHAR(15) | UNIQUE, NOT NULL | Mobile Number |
| password | VARCHAR(255) | NOT NULL | Encrypted Password |
| role_id | BIGINT | Foreign Key | References Role Table |
| status | VARCHAR(20) | NOT NULL | ACTIVE / INACTIVE |
| last_login | TIMESTAMP | NULL | Last Login Time |
| created_at | TIMESTAMP | NOT NULL | Record Created Time |
| updated_at | TIMESTAMP | NOT NULL | Record Updated Time |

---

## Foreign Key

role_id

References

Role(id)

---

## Relationships

One Role

↓

Many Users

(1:N)

---

## Business Rules

- Email must be unique.
- Mobile number must be unique.
- Password should be encrypted.
- Every user must belong to one role.
- Status should be ACTIVE or INACTIVE.
- Email cannot be changed without verification.

---

## Example Data

| id | first_name | email | role |
|----|------------|------|------|
| 1 | Admin | admin@stringstack.ai | ADMIN |
| 2 | Rahul | rahul@gmail.com | STUDENT |
| 3 | Priya | priya@gmail.com | TRAINER |
| 4 | Ravi | ravi@gmail.com | HR |

---

Status

Ready for Database Creation