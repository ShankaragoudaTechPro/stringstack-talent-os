# Table Design

---

# Table 1 : Role

Purpose

Stores different roles available in the application.

Columns

| Column | Data Type | Constraint |
|---------|-----------|------------|
| id | BIGINT | Primary Key |
| role_name | VARCHAR(50) | UNIQUE, NOT NULL |
| description | VARCHAR(255) | NULL |
| created_at | TIMESTAMP | NOT NULL |
| updated_at | TIMESTAMP | NOT NULL |

Example Data

ADMIN

TRAINER

STUDENT

HR

---

# Table 2 : User

Purpose

Stores login credentials and basic information.

Columns

| Column | Data Type | Constraint |
|---------|-----------|------------|
| id | BIGINT | Primary Key |
| first_name | VARCHAR(100) | NOT NULL |
| last_name | VARCHAR(100) | NULL |
| email | VARCHAR(150) | UNIQUE |
| mobile | VARCHAR(15) | UNIQUE |
| password | VARCHAR(255) | NOT NULL |
| role_id | BIGINT | Foreign Key |
| status | VARCHAR(20) | ACTIVE / INACTIVE |
| created_at | TIMESTAMP | NOT NULL |
| updated_at | TIMESTAMP | NOT NULL |

Relationship

One Role

↓

Many Users