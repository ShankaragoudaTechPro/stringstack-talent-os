# Course Table

## Purpose

The Course table stores all training courses offered by the institute.

---

## Table Name

course

---

## Columns

| Column Name | Data Type | Constraints | Description |
|-------------|-----------|-------------|-------------|
| id | BIGINT | Primary Key, Auto Increment | Course ID |
| course_name | VARCHAR(100) | NOT NULL | Course Name |
| description | TEXT | NULL | Course Description |
| duration_months | INT | NOT NULL | Course Duration |
| fee | DECIMAL(10,2) | NOT NULL | Course Fee |
| status | VARCHAR(20) | NOT NULL | ACTIVE / INACTIVE |
| created_at | TIMESTAMP | NOT NULL | Created Time |
| updated_at | TIMESTAMP | NOT NULL | Updated Time |

---

## Business Rules

- Course name must be unique.
- Duration must be greater than zero.
- Fee cannot be negative.

---

Status

Ready for Database Creation