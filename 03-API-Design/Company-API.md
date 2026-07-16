# Company API Documentation

## Base URL

http://localhost:8080/api/v1/companies

---

## Authentication

Authorization

Bearer <JWT_TOKEN>

---

# Create Company

POST /api/v1/companies

## Request

```json
{
    "companyCode":"COMP001",
    "companyName":"Infosys",
    "website":"https://www.infosys.com",
    "email":"careers@infosys.com",
    "phone":"9876543210",
    "address":"Electronic City",
    "city":"Bengaluru",
    "state":"Karnataka",
    "country":"India",
    "contactPerson":"Rohit Sharma",
    "contactDesignation":"HR Manager",
    "active":true
}
```

## Response

```json
{
    "id":1,
    "companyCode":"COMP001",
    "companyName":"Infosys",
    "website":"https://www.infosys.com",
    "email":"careers@infosys.com",
    "phone":"9876543210",
    "address":"Electronic City",
    "city":"Bengaluru",
    "state":"Karnataka",
    "country":"India",
    "contactPerson":"Rohit Sharma",
    "contactDesignation":"HR Manager",
    "active":true,
    "createdAt":"2026-07-20T18:30:00",
    "updatedAt":"2026-07-20T18:30:00"
}
```

---

# Get All Companies

GET /api/v1/companies

---

# Get Company By Id

GET /api/v1/companies/{id}

---

# Update Company

PUT /api/v1/companies/{id}

---

# Delete Company

DELETE /api/v1/companies/{id}

---

# HTTP Status Codes

200 OK

201 CREATED

400 BAD REQUEST

401 UNAUTHORIZED

404 NOT FOUND

409 CONFLICT

500 INTERNAL SERVER ERROR

---

# Validation

- Company Code must be unique
- Company Name must be unique
- Email should be unique
- Phone should be unique
- JWT Token is mandatory