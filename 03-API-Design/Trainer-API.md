# Trainer API Documentation

## Base URL

```
http://localhost:8080/api/v1/trainers
```

---

# Authentication

All Trainer APIs require a JWT Bearer Token.

Authorization Header

```
Authorization: Bearer <JWT_TOKEN>
```

---

## Create Trainer

**Method**

```
POST
```

**URL**

```
/api/v1/trainers
```

### Request Body

```json
{
    "trainerCode":"TR001",
    "firstName":"Ramesh",
    "lastName":"Kumar",
    "email":"ramesh@gmail.com",
    "phone":"9876543222",
    "specialization":"Java Full Stack",
    "experience":6.5,
    "joiningDate":"2026-07-13",
    "active":true,
    "userId":1
}
```

### Response

```json
{
    "id":1,
    "trainerCode":"TR001",
    "firstName":"Ramesh",
    "lastName":"Kumar",
    "email":"ramesh@gmail.com",
    "phone":"9876543222",
    "specialization":"Java Full Stack",
    "experience":6.5,
    "joiningDate":"2026-07-13",
    "active":true,
    "userId":1,
    "userEmail":"shankar@gmail.com",
    "createdAt":"2026-07-13T13:20:00",
    "updatedAt":"2026-07-13T13:20:00"
}
```

---

## Get All Trainers

**Method**

```
GET
```

```
GET /api/v1/trainers
```

---

## Get Trainer By Id

**Method**

```
GET
```

```
GET /api/v1/trainers/{id}
```

---

## Update Trainer

**Method**

```
PUT
```

```
PUT /api/v1/trainers/{id}
```

Request Body

```json
{
    "trainerCode":"TR001",
    "firstName":"Ramesh",
    "lastName":"Kumar",
    "email":"ramesh@gmail.com",
    "phone":"9876543222",
    "specialization":"Spring Boot",
    "experience":7.5,
    "joiningDate":"2026-07-13",
    "active":true,
    "userId":1
}
```

---

## Delete Trainer

**Method**

```
DELETE
```

```
DELETE /api/v1/trainers/{id}
```

### Response

```text
Trainer deleted successfully.
```

---

## HTTP Status Codes

| Status | Description |
|---------|-------------|
| 200 | Success |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 404 | Trainer Not Found |
| 409 | Duplicate Resource |
| 500 | Internal Server Error |

---

## Notes

- JWT Authentication is mandatory.
- Trainer email must be unique.
- Trainer phone must be unique.
- Trainer code must be unique.
- User must exist before creating a Trainer.