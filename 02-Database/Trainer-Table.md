# Trainer Table

## Purpose

The Trainer table stores trainer-specific information.

The login information is stored in the User table.

---

## Table Name

trainer

---

## Columns

| Column Name | Data Type | Constraints | Description |
|-------------|-----------|-------------|-------------|
| id | BIGINT | Primary Key, Auto Increment | Trainer ID |
| user_id | BIGINT | Foreign Key | References User Table |
| employee_id | VARCHAR(20) | UNIQUE | Trainer Employee ID |
| specialization | VARCHAR(100) | NOT NULL | Area of Expertise |
| experience_years | INT | NOT NULL | Total Experience |
| qualification | VARCHAR(100) | NOT NULL | Highest Qualification |
| joining_date | DATE | NOT NULL | Date of Joining |
| status | VARCHAR(20) | NOT NULL | ACTIVE / INACTIVE |
| created_at | TIMESTAMP | NOT NULL | Record Created Time |
| updated_at | TIMESTAMP | NOT NULL | Record Updated Time |

---

## Foreign Key

user_id

References

User(id)

---

## Relationship

One User

↓

One Trainer

(1:1)

---

## Business Rules

- Every trainer must have one user account.
- Employee ID must be unique.
- Specialization cannot be empty.
- Experience cannot be negative.
- Joining date cannot be a future date.
- Status should be ACTIVE or INACTIVE.

---

## Example Data

| id | employee_id | specialization | experience_years |
|----|-------------|----------------|------------------|
| 1 | SS001 | Java Full Stack | 8 |
| 2 | SS002 | React.js | 5 |
| 3 | SS003 | DevOps | 6 |

---

## Status

Ready for Database Creation