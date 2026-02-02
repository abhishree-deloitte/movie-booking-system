# API Contract Templates — Movie Booking System

> This document describes **how to test each API** with sample data and placeholders.
> Responses are intentionally omitted as per requirements.

---

## AUTH MODULE

### 1 Register User

**Endpoint**

```
POST /auth/register
```

**Auth**: Public

**Headers**

```
Content-Type: application/json
```

**Sample Request (Test Data)**

```json
{
  "username": "john_doe",
  "email": "john.doe@example.com",
  "password": "StrongPassword@123",
  "role": "USER"
}
```

**How to Test**

* Call API without Authorization header
* Use unique username and email
* Copy JWT token from response for further APIs

---

### 2 Login User

**Endpoint**

```
POST /auth/login
```

**Auth**: Public

**Headers**

```
Content-Type: application/json
```

**Sample Request (Test Data)**

```json
{
  "username": "john_doe",
  "password": "StrongPassword@123"
}
```

**How to Test**

* Use credentials from registration
* Save returned JWT token

---

## USER MODULE

### 3 Get My Profile

**Endpoint**

```
GET /users/me
```

**Auth**: USER

**Headers**

```
Authorization: Bearer <JWT_TOKEN>
```

**How to Test**

* Call API with valid USER token
* Verify profile belongs to logged-in user

---

### 4 Update Username

**Endpoint**

```
PUT /users/me/username
```

**Auth**: USER

**Headers**

```
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json
```

**Sample Request**

```json
{
  "username": "john_updated"
}
```

**How to Test**

* Login and use JWT
* Change username and re-fetch profile

---

### 5 Update Email

**Endpoint**

```
PUT /users/me/email
```

**Auth**: USER

**Headers**

```
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json
```

**Sample Request**

```json
{
  "email": "john.updated@example.com"
}
```

**How to Test**

* Use valid email format
* Fetch profile again to verify change

---

## MOVIE MODULE

### 6 Create Movie

**Endpoint**

```
POST /movies
```

**Auth**: ADMIN

**Headers**

```
Authorization: Bearer <ADMIN_JWT>
Content-Type: application/json
```

**Sample Request**

```json
{
  "title": "Inception",
  "durationMins": 180,
  "genre": "Sci-fi thriller",
  "basePrice": 200
}
```

**How to Test**

* Login as ADMIN
* Create movie and note {movieId}

---

### 7 Get All Movies

**Endpoint**

```
GET /movies
```

**Auth**: USER / ADMIN

**Headers**

```
Authorization: Bearer <JWT_TOKEN>
```

**How to Test**

* Call API as USER and ADMIN
* Verify list contains created movies

---

### 8 Rate Movie

**Endpoint**

```
POST /movies/{movieId}/rating
```

**Path Param**

* movieId → ID from movie creation

**Auth**: USER

**Headers**

```
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json
```

**Sample Request**

```json
{
  "rating": 4
}
```

**How to Test**

* Rate same movie multiple times
* Verify average rating changes

---

## THEATRE MODULE

### 9 Create Theatre

**Endpoint**

```
POST /theatres
```

**Auth**: ADMIN

**Headers**

```
Authorization: Bearer <ADMIN_JWT>
Content-Type: application/json
```

**Sample Request**

```json
{
  "name": "PVR Orion Mall",
  "location": "Bangalore"
}
```

**How to Test**

* Create theatre and save {theatreId}

---

### 10 Get Theatres

**Endpoint**

```
GET /theatres
```

**Auth**: USER / ADMIN

**Headers**

```
Authorization: Bearer <JWT_TOKEN>
```

---

## SCREEN & SEAT MODULE

### 11 Create Screen

**Endpoint**

```
POST /theatres/{theatreId}/screens
```

**Auth**: ADMIN

**Query Param**

* name=Audi-1

**Headers**

```
Authorization: Bearer <ADMIN_JWT>
```

---

### 12 Create Seat

**Endpoint**

```
POST /screens/{screenId}/seats
```

**Auth**: ADMIN

**Query Params**

* row=A
* number=1
* type=PREMIUM

**Headers**

```
Authorization: Bearer <ADMIN_JWT>
```

---

## SHOW MODULE

### 13 Create Show

**Endpoint**

```
POST /movies/{movieId}/shows
```

**Auth**: ADMIN

**Query Params**

* screenId={screenId}
* startTime=2026-02-01T18:30

---

## BOOKING MODULE

### 14 Create Booking

**Endpoint**

```
POST /bookings
```

**Auth**: USER

**Query Params**

* showId={showId}
* seatIds={seatId1},{seatId2}

**Headers**

```
Authorization: Bearer <JWT_TOKEN>
```

---

### 15 Cancel Booking

**Endpoint**

```
POST /bookings/{bookingId}/cancel
```

**Auth**: USER

**Headers**

```
Authorization: Bearer <JWT_TOKEN>
```

---

## NOTIFICATION MODULE

### 16 Get My Notifications

**Endpoint**

```
GET /users/me/notifications
```

**Auth**: USER

**Headers**

```
Authorization: Bearer <JWT_TOKEN>
```

---

## Testing Notes

* All protected APIs require JWT
* ADMIN token needed for setup APIs
* USER token used for booking flow
* IDs should be chained from previous API calls
