# FNAtlas - Fortnite Creative Analytics Dashboard

A personal analytics workspace for Fortnite Creative map creators and players to track, organize, and review maps using Epic Games' Fortnite Data API.

## Overview

FNAtlas bridges the gap between Epic's raw Fortnite Data API and community-driven insights. While Epic provides basic metrics, FNAtlas enables users to create personal map collections, write reviews, and build a curated discovery experience beyond Epic's algorithm.

## Features

### Core Functionality
- **User Management**: Account creation and profile management
- **Map Collections**: Create and organize custom map collections (e.g., "Favorite Parkour Maps")
- **Community Reviews**: Rate and review maps with 1-5 star ratings and detailed feedback
- **Epic API Integration**: Real-time map data and statistics from Epic Games' Fortnite Data API

### Key Capabilities
- Track map performance over time
- Discover maps through community curation
- Organize maps into themed collections
- Share insights through reviews and ratings

## Tech Stack

- **Backend**: Java 21, Spring Boot 3.5.3
- **Database**: PostgreSQL with Hibernate/JPA
- **Build Tool**: Maven
- **External API**: Epic Games Fortnite Data API
- **HTTP Client**: RestTemplate
- **Architecture**: RESTful API

## API Documentation

### User Endpoints
```
POST   /api/users          - Create user
GET    /api/users/{id}     - Get user profile
GET    /api/users          - Get all users
PUT    /api/users/{id}     - Update user profile
DELETE /api/users/{id}     - Delete user
```

### Authentication Endpoints
```
POST   /api/auth/login      - User login
POST   /api/auth/logout     - User logout
GET    /api/auth/me         - Get current authenticated user
```

### Collection Endpoints
```
POST   /api/collections                    - Create collection
GET    /api/collections/user/{userId}      - Get user's collections
GET    /api/collections/{id}               - Get collection details
PUT    /api/collections/{id}               - Update collection
DELETE /api/collections/{id}               - Delete collection
POST   /api/collections/{id}/maps          - Add map to collection
GET   /api/collections/{id}/maps           - Get maps in collection
DELETE /api/collections/{id}/maps/{mapId} - Remove map from collection
```

### Review Endpoints
```
POST   /api/reviews                - Create review
GET    /api/reviews/map/{mapCode}  - Get reviews for map
GET    /api/reviews/user/{userId}  - Get user's reviews
PUT    /api/reviews/{id}           - Update review
DELETE /api/reviews/{id}           - Delete review
```

### Map Data Endpoints
```
GET    /api/maps/{mapCode}       - Get map data from Epic API
GET    /api/maps/{mapCode}/stats - Get map statistics
```

## Usage Examples

### Creating a Map Collection
```bash
curl -X POST http://localhost:8080/api/collections \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Epic Parkour Maps",
    "description": "My favorite challenging parkour maps",
    "userId": 1
  }'
```

### Adding a Map to Collection
```bash
curl -X POST http://localhost:8080/api/collections/1/maps \
  -H "Content-Type: application/json" \
  -d '{
    "mapCode": "1234-5678-9012"
  }'
```

### Creating a Review
```bash
curl -X POST http://localhost:8080/api/reviews \
  -H "Content-Type: application/json" \
  -d '{
    "mapCode": "1234-5678-9012",
    "userId": 1,
    "rating": 5,
    "content": "Amazing parkour map with creative mechanics!"
  }'
```

### Getting Map Data
```bash
curl http://localhost:8080/api/maps/1234-5678-9012
```

---

**Note**: This project is not affiliated with Epic Games. Fortnite is a trademark of Epic Games, Inc.