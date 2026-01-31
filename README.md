# 🎬 Movie Booking System — Backend (Spring Boot)

## 📘 Overview

This project is a **backend Movie Booking System** built using **Java and Spring Boot** as part of a cross-skilling assignment.

The goal of this project is to demonstrate:
- Clean backend architecture
- Proper domain modeling
- Secure, role-based APIs
- Realistic system behavior  

---

## ✨ Features Implemented

### 🔐 Authentication & Security
- JWT-based authentication
- Role-based authorization (`USER`, `ADMIN`)
- Spring Security with `@PreAuthorize`
- Global exception handling with meaningful client responses

---

### 🎬 Movie Management
- Create and view movies
- Movie rating with incremental average calculation
- Role-protected movie creation (ADMIN only)

---

### 🏛️ Theatre Management
- Create and list theatres
- Screens (Audis) under theatres
- Seat maps per screen
- Seat types: `REGULAR`, `PREMIUM`

---

### 🕒 Show Scheduling
- Schedule movies on screens
- Fetch shows for a selected movie
- Forms the basis of the movie booking page

---

### 🎟️ Booking System
- Book seats for a show
- Automatic price calculation
- Booking lifecycle:
  - `UPCOMING`
  - `COMPLETED`
  - `CANCELLED`
- Cancel booking (only if UPCOMING)

---

### 👤 User Profile
- View user profile details
- Update username and email
- View own bookings
- View user notifications

---

### 🔔 Notifications
- Booking confirmation notification
- Booking cancellation notification
- Notifications returned as part of user profile

---

## 🧱 Architecture Style

The system follows a **Modular Monolith Architecture**:

```bash
auth → Authentication & JWT
user → User profile & preferences
movie → Movies & ratings
theatre → Theatre, Screen, Seat
show → Movie scheduling
booking → Booking lifecycle
notification → User notifications
```

Each module contains:
- Controller
- Service
- Repository
- Model

Modules communicate via **service calls**, not direct database access.

---

## 🔐 Security Model

- JWT tokens are issued by the Auth module
- Every request is authenticated via JWT filter
- Authorization is enforced using `@PreAuthorize`
- Roles:
  - `ADMIN` → system management
  - `USER` → booking and profile actions

---

## 💰 Pricing Logic

Final booking price is calculated as:

| Seat Type | Extra Price |
|----------|-------------|
| REGULAR  | +0          |
| PREMIUM  | +50         |

Pricing is calculated at booking time and stored with the booking to keep it immutable.

---

## 🧠 Booking Status Handling

- Booking status is automatically updated:
  - `UPCOMING → COMPLETED` when show time has passed
- Status updates are performed **lazily during read operations**
- No background schedulers or cron jobs are used

---

## 🚀 Microservices Design (Conceptual)

If this system were implemented as **microservices**:

- Each module would become an independent service
  - Auth Service
  - Movie Service
  - Booking Service, etc.
- Auth Service would issue JWT tokens
- Each service would:
  - Validate JWT independently
  - Enforce authorization locally
- Services would communicate via REST APIs
- No runtime dependency on Auth Service for authorization

This project intentionally remains a **modular monolith** to keep complexity manageable.

---

## 🗄️ Database Design (Conceptual)

The project uses **Spring Data JPA** and is database-agnostic.

In a real-world setup:
- PostgreSQL / MySQL would be used
- Database would typically run in Docker for local development
- Spring Boot would connect via JDBC configuration
- No Java code changes would be required — only configuration changes

---

## 🧪 Testing Strategy

- JUnit test skeletons are provided for:
  - Controllers
  - Services
  - Repositories
- Focus is on **structure and intent**, not exhaustive coverage

---