# Database Design

## Project Name

StringStack Talent OS

---

# Objective

The database is designed to manage all data related to students, trainers, courses, batches, attendance, mock interviews, companies, placements, resumes, and notifications.

The database should be:

- Secure
- Scalable
- Easy to Maintain
- Normalized
- Performance Optimized

---

# Database Technology

Database : MySQL 8

ORM : Hibernate / JPA

Backend : Spring Boot

---

# Database Naming Convention

## Tables

Use Singular Names

Example

User
Role
Student
Trainer
Course
Batch

---

## Primary Key

Every table should have

id

Example

student.id

trainer.id

course.id

---

## Foreign Key

Foreign Keys should end with "_id"

Examples

user_id

course_id

trainer_id

batch_id

company_id

---

## Timestamp Columns

Every major table should contain

created_at

updated_at

---

## Status Columns

Use status wherever required

Examples

ACTIVE

INACTIVE

PENDING

PLACED

REJECTED

---

# Main Modules

1. Authentication

2. User Management

3. Student Management

4. Trainer Management

5. Course Management

6. Batch Management

7. Enrollment

8. Attendance

9. Study Material

10. Mock Interview

11. Interview Feedback

12. Company

13. Placement

14. Resume

15. Notification

---

# Future Enhancements

- AI Resume Review

- AI Mock Interview

- Coding Assessment

- Email Notification

- SMS Notification

- QR Certificate

- Mobile App

---

End of Document