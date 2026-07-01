# Batch Table

## Purpose

Stores training batch information.

---

## Table Name

batch

---

## Columns

| Column Name | Data Type | Constraints | Description |
|-------------|-----------|-------------|-------------|
| id | BIGINT | Primary Key |
| batch_name | VARCHAR(50) | UNIQUE |
| course_id | BIGINT | Foreign Key |
| trainer_id | BIGINT | Foreign Key |
| start_date | DATE | NOT NULL |
| end_date | DATE | NOT NULL |
| batch_mode | VARCHAR(20) | ONLINE / OFFLINE / HYBRID |
| status | VARCHAR(20) | ACTIVE / COMPLETED |
| created_at | TIMESTAMP | NOT NULL |
| updated_at | TIMESTAMP | NOT NULL |

---

## Relationships

One Course

↓

Many Batches

One Trainer

↓

Many Batches

---

Status

Ready for Database Creation