# Attendance API Documentation

## Base URL

http://localhost:8080/api/v1/attendances

---

## Authentication

Authorization

Bearer <JWT_TOKEN>

---

## Create Attendance

POST /api/v1/attendances

### Request

```json
{
    "attendanceCode":"ATT001",
    "enrollmentId":1,
    "attendanceDate":"2026-07-20",
    "status":"PRESENT",
    "remarks":"Present in class",
    "active":true
}
```

### Response

```json
{
    "id":1,
    "attendanceCode":"ATT001",
    "enrollmentId":1,
    "studentName":"Rahul Patil",
    "batchName":"Java Full Stack",
    "attendanceDate":"2026-07-20",
    "status":"PRESENT",
    "remarks":"Present in class",
    "active":true,
    "createdAt":"2026-07-20T10:00:00",
    "updatedAt":"2026-07-20T10:00:00"
}
```

---

## Get All Attendance

GET /api/v1/attendances

---

## Get Attendance By Id

GET /api/v1/attendances/{id}

---

## Update Attendance

PUT /api/v1/attendances/{id}

---

## Delete Attendance

DELETE /api/v1/attendances/{id}

---

## Status Codes

200 OK

201 CREATED

400 BAD REQUEST

401 UNAUTHORIZED

404 NOT FOUND

500 INTERNAL SERVER ERROR

---

## Validation

- Attendance Code must be unique
- Enrollment must exist
- JWT Token is required