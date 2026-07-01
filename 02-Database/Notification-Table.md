# Notification Table

## Purpose

Stores system notifications.

---

## Table Name

notification

---

## Columns

| Column Name | Data Type | Constraints |
|-------------|-----------|-------------|
| id | BIGINT | Primary Key |
| user_id | BIGINT | Foreign Key |
| title | VARCHAR(150) | NOT NULL |
| message | TEXT | NOT NULL |
| is_read | BOOLEAN | Default FALSE |
| created_at | TIMESTAMP | NOT NULL |

---

Relationship

One User

↓

Many Notifications

---

Status

Ready for Database Creation