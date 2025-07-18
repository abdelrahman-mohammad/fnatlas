# FNAtlas Backend - Fortnite Creative Analytics Dashboard

A personal analytics workspace backend for Fortnite Creative map creators and players to track, organize, and review maps using Epic Games' Fortnite Data API.

## Overview

FNAtlas bridges the gap between Epic's raw Fortnite Data API and community-driven insights. While Epic provides basic metrics, FNAtlas enables users to create personal map collections, write reviews, and build a curated discovery experience beyond Epic's algorithm.

## Features

### Core Functionality
- **User Management**: Account creation, profile management, and session-based authentication
- **Map Collections**: Create and organize custom map collections with nested user-specific endpoints
- **Community Reviews**: Rate and review maps with 1-5 star ratings and detailed feedback (user-specific endpoints)
- **Epic API Integration**: Real-time map data and advanced metrics from Epic Games' Fortnite Data API
- **Session Management**: Token-based authentication with configurable expiration

### Key Capabilities
- Track map performance over time with detailed metrics
- Discover maps through community curation and reviews
- Organize maps into themed collections
- Share insights through reviews and ratings
- Advanced map metrics with time-series data (hourly, daily intervals)
- Comprehensive error handling and validation

## Tech Stack

- **Backend**: Java 21, Spring Boot 3.5.3
- **Database**: PostgreSQL with Hibernate/JPA
- **Build Tool**: Maven
- **External API**: Epic Games Fortnite Data API
- **HTTP Client**: WebClient (Reactive)
- **Architecture**: RESTful API with nested resources
- **Validation**: Bean Validation (JSR-303)
- **Documentation**: OpenAPI 3 (Swagger)

## Database Schema

### Entities
- **User**: User accounts with authentication
- **UserSession**: Session-based authentication tokens
- **Collection**: User-owned map collections
- **CollectionMap**: Maps within collections (many-to-many relationship)
- **Review**: User reviews for maps with ratings

## API Documentation

### User Management
```
POST   /api/users              - Create user account
GET    /api/users/{id}         - Get user profile by ID
GET    /api/users              - Get all users
PUT    /api/users/{id}         - Update user profile
DELETE /api/users/{id}         - Delete user account
```

### Authentication
```
POST   /api/auth/login         - User login (returns session token)
POST   /api/auth/logout        - User logout (invalidates token)
GET    /api/auth/me            - Get current authenticated user profile
```

### Collections (User-Nested)
```
POST   /api/users/{userId}/collections                           - Create collection
GET    /api/users/{userId}/collections                           - Get user's collections
GET    /api/users/{userId}/collections/{collectionId}            - Get collection details
PUT    /api/users/{userId}/collections/{collectionId}            - Update collection
DELETE /api/users/{userId}/collections/{collectionId}            - Delete collection

# Collection Maps
POST   /api/users/{userId}/collections/{collectionId}/maps       - Add map to collection
GET    /api/users/{userId}/collections/{collectionId}/maps       - Get maps in collection
DELETE /api/users/{userId}/collections/{collectionId}/maps/{mapCode} - Remove map from collection
```

### Reviews (User-Nested)
```
POST   /api/users/{userId}/reviews                 - Create review
GET    /api/users/{userId}/reviews                 - Get user's reviews
GET    /api/users/{userId}/reviews/{reviewId}      - Get specific review
PUT    /api/users/{userId}/reviews/{reviewId}      - Update review
DELETE /api/users/{userId}/reviews/{reviewId}      - Delete review
```

### Maps & Epic API Integration
```
GET    /api/maps/{mapCode}                         - Get map data from Epic API
GET    /api/maps/{mapCode}/metrics/{interval}      - Get map metrics with time-series data
GET    /api/maps/{mapCode}/reviews                 - Get all reviews for a map
```

#### Map Metrics Parameters
- **interval**: `hour`, `day`, `minute`
- **metrics** (query param): Array of metric types to include
- **from** (query param): Start date for metrics
- **to** (query param): End date for metrics

## Request/Response DTOs

### Authentication
- **LoginRequest**: `{ username, password, duration }`
- **LoginResponse**: `{ token, user }`
- **LogoutRequest**: `{ token }`

### Collections
- **CollectionRequest**: `{ name, description }`
- **CollectionMapRequest**: `{ mapCode }`

### Reviews
- **ReviewRequest**: `{ mapCode, rating, content }`

### Maps
- **MapResponse**: `{ mapCode, creatorCode, displayName, title, category, createdIn, tags, exists }`
- **MapMetricsResponse**: Complex time-series data with multiple metric types

## Usage Examples

### User Registration
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "mapcrafter123",
    "email": "creator@example.com",
    "password": "securepass123"
  }'
```

### User Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "mapcrafter123",
    "password": "securepass123"
  }'
```

### Creating a Map Collection
```bash
curl -X POST http://localhost:8080/api/users/1/collections \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Epic Parkour Maps",
    "description": "My favorite challenging parkour maps"
  }'
```

### Adding a Map to Collection
```bash
curl -X POST http://localhost:8080/api/users/1/collections/1/maps \
  -H "Content-Type: application/json" \
  -d '{
    "mapCode": "1234-5678-9012"
  }'
```

### Creating a Review
```bash
curl -X POST http://localhost:8080/api/users/1/reviews \
  -H "Content-Type: application/json" \
  -d '{
    "mapCode": "1234-5678-9012",
    "rating": 5,
    "content": "Amazing parkour map with creative mechanics and perfect difficulty progression!"
  }'
```

### Getting Map Data
```bash
curl http://localhost:8080/api/maps/1234-5678-9012
```

### Getting Map Metrics
```bash
# Get daily metrics for the last week
curl "http://localhost:8080/api/maps/1234-5678-9012/metrics/day?metrics=plays,favorites,uniquePlayers&from=2025-01-10&to=2025-01-17"
```

## Environment Configuration

### Required Environment Variables
```bash
# Database
DATABASE_URL=jdbc:postgresql://localhost:5432/fnatlas
DATABASE_USERNAME=your_db_user
DATABASE_PASSWORD=your_db_password

# Fortnite API
FN_API_BASE_URL=https://fortnite-api.com

# Server
SERVER_PORT=8080
```

## Validation Rules

### Map Code Format
- Must follow pattern: `XXXX-XXXX-XXXX` (e.g., `1234-5678-9012`)

### User Constraints
- Username: 3-50 characters, unique
- Email: Valid email format, unique
- Password: Minimum 8 characters

### Review Constraints
- Rating: 1-5 stars (integer)
- Content: 10-1000 characters (optional)
- One review per user per map

### Collection Constraints
- Name: Maximum 100 characters, required
- Description: Maximum 500 characters (optional)

## Error Handling

The API uses standard HTTP status codes and returns structured error responses:

```json
{
  "status": 404,
  "message": "User not found with id: 123",
  "timestamp": "2025-01-17T10:30:00"
}
```

### Common Status Codes
- `200 OK` - Successful operation
- `201 Created` - Resource created successfully
- `204 No Content` - Successful operation with no response body
- `400 Bad Request` - Validation errors or invalid input
- `401 Unauthorized` - Authentication required or invalid
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Unexpected server error

## Development Setup

### Prerequisites
- Java 21
- PostgreSQL 12+
- Maven 3.6+

### Running the Application
```bash
# Clone repository
git clone [repository-url]
cd fnatlas/backend

# Set environment variables
export DATABASE_URL=jdbc:postgresql://localhost:5432/fnatlas
export DATABASE_USERNAME=your_user
export DATABASE_PASSWORD=your_password
export FN_API_BASE_URL=https://fortnite-api.com

# Run application
./mvnw spring-boot:run
``` 

### API Documentation
Once running, visit:
- Swagger UI: `http://localhost:8080/api/swagger-ui.html`
- OpenAPI Spec: `http://localhost:8080/api/api-docs`

## Testing

### Sample Data

Use this [Postman collection](https://www.postman.com/abdelrahman-abdelaal/workspace/fn-atlas-workspace/collection/36850448-fc0e2df0-f536-4228-be1b-b479c3839974?action=share&creator=36850448&active-environment=36850448-d1d0ebd2-5bfa-4a7e-b5d1-87c840c03f84) or the curl examples below to create test data, or import the Postman collection for comprehensive API testing.

### Database
The application uses `spring.jpa.hibernate.ddl-auto=update` for automatic schema updates during development.

---

**Note**: This project is not affiliated with Epic Games. Fortnite is a trademark of Epic Games, Inc.
This is AI generated.