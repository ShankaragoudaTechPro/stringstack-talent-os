# Attendance Table

## Purpose

Stores daily student attendance.

---

## Table Name

attendance

---

## Columns

| Column Name | Data Type | Constraints | Description |
|-------------|-----------|-------------|-------------|
| id | BIGINT | Primary Key |
| student_id | BIGINT | Foreign Key |
| batch_id | BIGINT | Foreign Key |
| attendance_date | DATE | NOT NULL |
| status | VARCHAR(20) | PRESENT / ABSENT / LEAVE |
| remarks | VARCHAR(255) | NULL |

---

## Relationship

One Student

↓

Many Attendance Records

One Batch

↓

Many Attendance Records

---

Status

Ready for Database Creation