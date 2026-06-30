# Student Table

## Purpose

The Student table stores student-specific information.

The login information is stored in the User table.

---

## Table Name

student

---

## Columns

| Column Name | Data Type | Constraints | Description |
|-------------|-----------|-------------|-------------|
| id | BIGINT | Primary Key, Auto Increment | Student ID |
| user_id | BIGINT | Foreign Key | References User Table |
| college_name | VARCHAR(150) | NOT NULL | College Name |
| branch | VARCHAR(100) | NOT NULL | Branch |
| graduation_year | INT | NOT NULL | Graduation Year |
| cgpa | DECIMAL(3,2) | NULL | CGPA |
| address | VARCHAR(255) | NULL | Address |
| city | VARCHAR(100) | NULL | City |
| state | VARCHAR(100) | NULL | State |
| resume_url | VARCHAR(255) | NULL | Resume File Path |
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

One Student

(1:1)

---

## Business Rules

- Every student must have one user account.
- One user can have only one student profile.
- Resume upload is optional.
- Graduation year cannot be less than current year - 10.

---

## Status

Ready for Database Creation