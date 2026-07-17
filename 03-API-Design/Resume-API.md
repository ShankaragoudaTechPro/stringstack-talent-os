# Resume API Documentation

## Base URL

```http
http://localhost:8080/api/v1/resumes
```

---

## 1. Create Resume

### Endpoint

```http
POST /api/v1/resumes
```

### Request Body

```json
{
  "resumeCode": "RES001",
  "studentId": 1,
  "title": "Java Backend Resume",
  "fileName": "Shankaragouda_Resume.pdf",
  "fileType": "PDF",
  "filePath": "/uploads/resumes/Shankaragouda_Resume.pdf",
  "uploadedAt": "2026-07-17",
  "active": true
}
```

### Response

```json
{
  "id": 1,
  "resumeCode": "RES001",
  "studentId": 1,
  "studentName": "Shankaragouda Pagad",
  "title": "Java Backend Resume",
  "fileName": "Shankaragouda_Resume.pdf",
  "fileType": "PDF",
  "filePath": "/uploads/resumes/Shankaragouda_Resume.pdf",
  "uploadedAt": "2026-07-17",
  "active": true
}
```

---

## 2. Get All Resumes

### Endpoint

```http
GET /api/v1/resumes
```

---

## 3. Get Resume By Id

### Endpoint

```http
GET /api/v1/resumes/{id}
```

Example

```http
GET /api/v1/resumes/1
```

---

## 4. Update Resume

### Endpoint

```http
PUT /api/v1/resumes/{id}
```

Example

```http
PUT /api/v1/resumes/1
```

### Request Body

```json
{
  "resumeCode": "RES001",
  "studentId": 1,
  "title": "Updated Resume",
  "fileName": "Updated_Resume.pdf",
  "fileType": "PDF",
  "filePath": "/uploads/resumes/Updated_Resume.pdf",
  "uploadedAt": "2026-07-17",
  "active": true
}
```

---

## 5. Delete Resume

### Endpoint

```http
DELETE /api/v1/resumes/{id}
```

Example

```http
DELETE /api/v1/resumes/1
```

### Success Response

```text
Resume deleted successfully.
```

---

## HTTP Status Codes

| Status | Description |
|---------|-------------|
|201|Created|
|200|Success|
|400|Bad Request|
|404|Resume Not Found|
|500|Internal Server Error|