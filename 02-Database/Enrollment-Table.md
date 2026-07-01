# Enrollment Table

## Purpose

Stores which students are enrolled in which batch.

---

## Table Name

enrollment

---

## Columns

| Column Name | Data Type | Constraints | Description |
|-------------|-----------|-------------|-------------|
| id | BIGINT | Primary Key |
| student_id | BIGINT | Foreign Key |
| batch_id | BIGINT | Foreign Key |
| enrollment_date | DATE | NOT NULL |
| status | VARCHAR(20) | ACTIVE / COMPLETED / DROPPED |

---

## Relationship

One Batch

↓

Many Students

Many Students

↓

Many Batches

(Many-to-Many using Enrollment)

---

Status

Ready for Database Creation