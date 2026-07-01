# Placement Drive Table

## Purpose

Stores placement drive details.

---

## Table Name

placement_drive

---

## Columns

| Column Name | Data Type | Constraints |
|-------------|-----------|-------------|
| id | BIGINT | Primary Key |
| company_id | BIGINT | Foreign Key |
| drive_title | VARCHAR(150) | NOT NULL |
| drive_date | DATE | NOT NULL |
| location | VARCHAR(100) | NOT NULL |
| package_lpa | DECIMAL(5,2) | NOT NULL |
| eligibility | TEXT | NULL |
| status | VARCHAR(20) | OPEN / CLOSED |

---

Relationship

One Company

↓

Many Placement Drives

---

Status

Ready for Database Creation