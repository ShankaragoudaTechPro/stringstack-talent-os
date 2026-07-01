# Resume Table

## Purpose

Stores student resumes.

---

## Table Name

resume

---

## Columns

| Column Name | Data Type | Constraints |
|-------------|-----------|-------------|
| id | BIGINT | Primary Key |
| student_id | BIGINT | Foreign Key |
| file_name | VARCHAR(255) | NOT NULL |
| file_url | VARCHAR(500) | NOT NULL |
| uploaded_at | TIMESTAMP | NOT NULL |

---

Relationship

One Student

↓

Many Resume Versions

---

Status

Ready for Database Creation