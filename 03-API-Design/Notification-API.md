# Notification API Documentation

## Base URL

```http
http://localhost:8080/api/v1/notifications
```

---

# 1. Create Notification

## Endpoint

```http
POST /api/v1/notifications
```

## Headers

```http
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json
```

## Request Body

```json
{
  "notificationCode": "NOT001",
  "title": "Placement Drive",
  "message": "Java Backend Developer interview starts tomorrow at 10:00 AM.",
  "recipientType": "STUDENT",
  "studentId": 1,
  "trainerId": null,
  "companyId": null,
  "sentAt": "2026-07-17",
  "status": "SENT",
  "active": true
}
```

## Success Response (201 Created)

```json
{
  "id": 1,
  "notificationCode": "NOT001",
  "title": "Placement Drive",
  "message": "Java Backend Developer interview starts tomorrow at 10:00 AM.",
  "recipientType": "STUDENT",
  "studentId": 1,
  "studentName": "Shankaragouda Pagad",
  "trainerId": null,
  "trainerName": null,
  "companyId": null,
  "companyName": null,
  "sentAt": "2026-07-17",
  "status": "SENT",
  "active": true,
  "createdAt": "2026-07-17T10:20:45",
  "updatedAt": "2026-07-17T10:20:45"
}
```

---

# 2. Get All Notifications

## Endpoint

```http
GET /api/v1/notifications
```

## Headers

```http
Authorization: Bearer <JWT_TOKEN>
```

## Success Response

```json
[
  {
    "id": 1,
    "notificationCode": "NOT001",
    "title": "Placement Drive",
    "recipientType": "STUDENT",
    "status": "SENT",
    "active": true
  }
]
```

---

# 3. Get Notification By Id

## Endpoint

```http
GET /api/v1/notifications/{id}
```

### Example

```http
GET /api/v1/notifications/1
```

## Success Response

```json
{
  "id": 1,
  "notificationCode": "NOT001",
  "title": "Placement Drive",
  "message": "Java Backend Developer interview starts tomorrow at 10:00 AM.",
  "recipientType": "STUDENT",
  "studentId": 1,
  "studentName": "Shankaragouda Pagad",
  "trainerId": null,
  "trainerName": null,
  "companyId": null,
  "companyName": null,
  "sentAt": "2026-07-17",
  "status": "SENT",
  "active": true
}
```

---

# 4. Update Notification

## Endpoint

```http
PUT /api/v1/notifications/{id}
```

### Example

```http
PUT /api/v1/notifications/1
```

## Request Body

```json
{
  "notificationCode": "NOT001",
  "title": "Updated Placement Drive",
  "message": "Interview timing changed to 11:00 AM.",
  "recipientType": "STUDENT",
  "studentId": 1,
  "trainerId": null,
  "companyId": null,
  "sentAt": "2026-07-18",
  "status": "UPDATED",
  "active": true
}
```

## Success Response

```json
{
  "id": 1,
  "notificationCode": "NOT001",
  "title": "Updated Placement Drive",
  "status": "UPDATED",
  "active": true
}
```

---

# 5. Delete Notification

## Endpoint

```http
DELETE /api/v1/notifications/{id}
```

### Example

```http
DELETE /api/v1/notifications/1
```

## Success Response

```text
Notification deleted successfully.
```

---

# Database Relationship

```
Notification
   |
   |----> Student (Optional)
   |
   |----> Trainer (Optional)
   |
   |----> Company (Optional)
```

---

# HTTP Status Codes

| Status Code | Description |
|-------------|-------------|
| 201 | Created |
| 200 | Success |
| 400 | Bad Request |
| 401 | Unauthorized |
| 404 | Notification Not Found |
| 500 | Internal Server Error |

---

# Postman Testing Status

| API | Status |
|------|--------|
| Create Notification | ✅ Passed |
| Get All Notifications | ✅ Passed |
| Get Notification By Id | ✅ Passed |
| Update Notification | ✅ Passed |
| Delete Notification | ✅ Passed |

---

# Module Status

```
✅ Notification Module Completed Successfully
```