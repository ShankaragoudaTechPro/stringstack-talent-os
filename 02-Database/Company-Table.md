# Company Table

## Purpose

Stores company details participating in placement drives.

---

## Table Name

company

---

## Columns

| Column Name | Data Type | Constraints | Description |
|-------------|-----------|-------------|-------------|
| id | BIGINT | Primary Key | Company ID |
| company_name | VARCHAR(150) | UNIQUE | Company Name |
| industry | VARCHAR(100) | NOT NULL | Industry Type |
| website | VARCHAR(255) | NULL | Company Website |
| hr_name | VARCHAR(100) | NOT NULL | HR Name |
| hr_email | VARCHAR(150) | NOT NULL | HR Email |
| hr_mobile | VARCHAR(15) | NULL | HR Contact |
| status | VARCHAR(20) | ACTIVE / INACTIVE | Company Status |
| created_at | TIMESTAMP | NOT NULL | Created Time |
| updated_at | TIMESTAMP | NOT NULL | Updated Time |

---

Status

Ready for Database Creation