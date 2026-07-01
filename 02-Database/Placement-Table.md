# Placement Table

## Purpose

Stores placement results.

---

## Table Name

placement

---

## Columns

| Column Name | Data Type | Constraints |
|-------------|-----------|-------------|
| id | BIGINT | Primary Key |
| student_id | BIGINT | Foreign Key |
| placement_drive_id | BIGINT | Foreign Key |
| interview_status | VARCHAR(30) | Selected / Rejected / Pending |
| joining_date | DATE | NULL |
| offered_package | DECIMAL(5,2) | NULL |

---

Status

Ready for Database Creation