# Role API Documentation

## Module

Role Management

---

## Base URL

```
http://localhost:8080/api/v1/roles
```

---

## 1. Create Role

### Method

POST

### Endpoint

```
POST /api/v1/roles
```

### Request Body

```json
{
    "roleName": "ADMIN",
    "description": "System Administrator",
    "active": true
}
```

### Success Response

**Status:** 201 Created

```json
{
    "id": 1,
    "roleName": "ADMIN",
    "description": "System Administrator",
    "active": true,
    "createdAt": "2026-07-01T10:30:00",
    "updatedAt": "2026-07-01T10:30:00"
}
```

---

## 2. Get All Roles

### Method

GET

### Endpoint

```
GET /api/v1/roles
```

### Success Response

**Status:** 200 OK

```json
[
    {
        "id": 1,
        "roleName": "ADMIN",
        "description": "System Administrator",
        "active": true
    }
]
```

---

## 3. Get Role By Id

### Method

GET

### Endpoint

```
GET /api/v1/roles/{id}
```

### Example

```
GET /api/v1/roles/1
```

---

## 4. Update Role

### Method

PUT

### Endpoint

```
PUT /api/v1/roles/{id}
```

### Request Body

```json
{
    "roleName": "SUPER_ADMIN",
    "description": "Updated Administrator",
    "active": true
}
```

---

## 5. Delete Role

### Method

DELETE

### Endpoint

```
DELETE /api/v1/roles/{id}
```

### Example

```
DELETE /api/v1/roles/1
```

---

## Validation

- roleName must be unique.
- roleName cannot be blank.
- description cannot be blank.
- active cannot be null.

---

## Database Table

roles

| Column | Type |
|---------|------|
| id | BIGINT |
| role_name | VARCHAR(50) |
| description | VARCHAR(255) |
| active | BOOLEAN |
| created_at | DATETIME |
| updated_at | DATETIME |