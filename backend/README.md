# FNAtlas Backend

A Spring Boot REST API for the FNAtlas Fortnite Creative Analytics Dashboard.

## Getting Started

### 1. Prerequisites

- Java 21
- PostgreSQL 12+
- Maven 3.6+

### 2. Environment Setup

Set the required environment variables:

```bash
export DATABASE_URL=jdbc:postgresql://localhost:5432/fnatlas
export DATABASE_USERNAME=your_db_user
export DATABASE_PASSWORD=your_db_password
export FN_API_BASE_URL=https://fortnite-api.com
```

### 3. Run the Application

```bash
cd backend
./mvnw spring-boot:run
```

The backend will run on `http://localhost:8080`

## Project Structure

```
backend/src/main/java/com/fnatlas/api/
├── controllers/       # REST API endpoints
├── services/         # Business logic
├── entities/         # Database models
├── repositories/     # Data access layer
├── exceptions/       # Custom exceptions
└── config/           # Configuration classes
```

## API Endpoints

### Authentication
- `POST /api/auth/login` - User login (returns session token)
- `POST /api/auth/logout` - User logout (invalidates token)
- `GET /api/auth/me` - Get current authenticated user profile

### User Management
- `POST /api/users` - Create user account
- `GET /api/users/{id}` - Get user profile by ID
- `GET /api/users` - Get all users
- `PUT /api/users/{id}` - Update user profile
- `DELETE /api/users/{id}` - Delete user account

### Collections (User-Nested)
- `POST /api/users/{userId}/collections` - Create collection
- `GET /api/users/{userId}/collections` - Get user's collections
- `GET /api/users/{userId}/collections/{collectionId}` - Get collection details
- `PUT /api/users/{userId}/collections/{collectionId}` - Update collection
- `DELETE /api/users/{userId}/collections/{collectionId}` - Delete collection

**Collection Maps:**
- `POST /api/users/{userId}/collections/{collectionId}/maps` - Add map to collection
- `GET /api/users/{userId}/collections/{collectionId}/maps` - Get maps in collection
- `DELETE /api/users/{userId}/collections/{collectionId}/maps/{mapCode}` - Remove map from collection

### Reviews (User-Nested)
- `POST /api/users/{userId}/reviews` - Create review
- `GET /api/users/{userId}/reviews` - Get user's reviews
- `GET /api/users/{userId}/reviews/{reviewId}` - Get specific review
- `PUT /api/users/{userId}/reviews/{reviewId}` - Update review
- `DELETE /api/users/{userId}/reviews/{reviewId}` - Delete review

### Maps & Epic API Integration
- `GET /api/maps/{mapCode}` - Get map data from Epic API
- `GET /api/maps/{mapCode}/metrics/{interval}` - Get map metrics with time-series data
- `GET /api/maps/{mapCode}/reviews` - Get all reviews for a map

#### Map Metrics Parameters
- **interval**: `hour`, `day`, `minute`
- **metrics** (query param): Array of metric types to include
- **from** (query param): Start date for metrics
- **to** (query param): End date for metrics

## Testing the Application

### 1. API Documentation

Once running, visit:
- **Swagger UI:** `http://localhost:8080/api/swagger-ui.html`
- **OpenAPI Spec:** `http://localhost:8080/api/api-docs`

### 2. Postman Collection

Use this [Postman collection](https://www.postman.com/abdelrahman-abdelaal/workspace/fn-atlas-workspace/collection/36850448-fc0e2df0-f536-4228-be1b-b479c3839974) for comprehensive API testing.

---

**Tech Stack:** Java 21, Spring Boot 3.5.3, PostgreSQL, Epic Games API

**Note:** This project is not affiliated with Epic Games. This is AI generated.
