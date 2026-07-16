# Course API Documentation

## Base URL

```
http://localhost:8080/api/v1/courses
```

---

# Authentication

All Course APIs require JWT Authentication.

Authorization Header

```
Authorization: Bearer <JWT_TOKEN>
```

---

## Create Course

**Method**

```
POST
```

**URL**

```
POST /api/v1/courses
```

### Request

```json
{
    "courseCode":"JAVA001",
    "courseName":"Java Full Stack",
    "description":"Complete Java Full Stack Development",
    "duration":180,
    "fee":25000,
    "level":"Intermediate",
    "active":true
}
```

### Response

```json
{
    "id":1,
    "courseCode":"JAVA001",
    "courseName":"Java Full Stack",
    "description":"Complete Java Full Stack Development",
    "duration":180,
    "fee":25000.0,
    "level":"Intermediate",
    "active":true,
    "createdAt":"2026-07-14T10:30:15",
    "updatedAt":"2026-07-14T10:30:15"
}
```

---

## Get All Courses

```
GET /api/v1/courses
```

---

## Get Course By Id

```
GET /api/v1/courses/{id}
```

---

## Update Course

```
PUT /api/v1/courses/{id}
```

### Request

```json
{
    "courseCode":"JAVA001",
    "courseName":"Advanced Java Full Stack",
    "description":"Advanced Java Full Stack Development",
    "duration":200,
    "fee":30000,
    "level":"Advanced",
    "active":true
}
```

---

## Delete Course

```
DELETE /api/v1/courses/{id}
```

### Response

```
Course deleted successfully.
```

---

# HTTP Status Codes

| Code | Description |
|------|-------------|
|200|Success|
|201|Created|
|400|Bad Request|
|401|Unauthorized|
|404|Course Not Found|
|409|Duplicate Course|
|500|Internal Server Error|

---

# Validation Rules

- Course Code must be unique.
- Course Name must be unique.
- Duration must be greater than 0.
- Fee must be greater than 0.
- JWT Token is mandatory.