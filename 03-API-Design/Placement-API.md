# Placement API Documentation

## Base URL

http://localhost:8080/api/v1/placements

---

## Authentication

Authorization

Bearer <JWT_TOKEN>

---

## Create Placement

POST /api/v1/placements

### Request

```json
{
    "placementCode":"PLC001",
    "enrollmentId":1,
    "placementDriveId":1,
    "companyId":1,
    "interviewStatus":"COMPLETED",
    "selectionStatus":"SELECTED",
    "offeredPackage":7.5,
    "joiningDate":"2026-09-01",
    "remarks":"Selected in Technical Interview",
    "active":true
}
```

### Response

```json
{
    "id":1,
    "placementCode":"PLC001",
    "enrollmentId":1,
    "studentName":"Rahul Patil",
    "placementDriveId":1,
    "driveTitle":"Infosys Campus Drive",
    "companyId":1,
    "companyName":"Infosys",
    "interviewStatus":"COMPLETED",
    "selectionStatus":"SELECTED",
    "offeredPackage":7.5,
    "joiningDate":"2026-09-01",
    "remarks":"Selected in Technical Interview",
    "active":true,
    "createdAt":"2026-08-01T10:00:00",
    "updatedAt":"2026-08-01T10:00:00"
}
```

---

## Get All Placements

GET /api/v1/placements

---

## Get Placement By Id

GET /api/v1/placements/{id}

---

## Update Placement

PUT /api/v1/placements/{id}

---

## Delete Placement

DELETE /api/v1/placements/{id}

---

## Status Codes

200 OK

201 CREATED

400 BAD REQUEST

401 UNAUTHORIZED

404 NOT FOUND

500 INTERNAL SERVER ERROR