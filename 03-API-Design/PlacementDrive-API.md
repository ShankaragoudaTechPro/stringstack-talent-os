# Placement Drive API Documentation

## Base URL

http://localhost:8080/api/v1/placement-drives

---

## Authentication

Authorization

Bearer <JWT_TOKEN>

---

# Create Placement Drive

POST /api/v1/placement-drives

## Request

```json
{
    "driveCode":"PD001",
    "driveTitle":"Infosys Campus Drive 2026",
    "companyId":1,
    "jobRole":"Java Backend Developer",
    "jobLocation":"Bengaluru",
    "packageOffered":7.5,
    "eligibilityCriteria":"BE/BTech, 60% throughout",
    "driveDate":"2026-08-15",
    "lastDateToApply":"2026-08-10",
    "status":"OPEN",
    "active":true
}
```

## Response

```json
{
    "id":1,
    "driveCode":"PD001",
    "driveTitle":"Infosys Campus Drive 2026",
    "companyId":1,
    "companyName":"Infosys",
    "jobRole":"Java Backend Developer",
    "jobLocation":"Bengaluru",
    "packageOffered":7.5,
    "eligibilityCriteria":"BE/BTech, 60% throughout",
    "driveDate":"2026-08-15",
    "lastDateToApply":"2026-08-10",
    "status":"OPEN",
    "active":true
}
```

---

## Get All Placement Drives

GET /api/v1/placement-drives

---

## Get Placement Drive By Id

GET /api/v1/placement-drives/{id}

---

## Update Placement Drive

PUT /api/v1/placement-drives/{id}

---

## Delete Placement Drive

DELETE /api/v1/placement-drives/{id}

---

## Status Codes

200 OK

201 CREATED

400 BAD REQUEST

401 UNAUTHORIZED

404 NOT FOUND

500 INTERNAL SERVER ERROR