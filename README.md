# FNAtlas - Fortnite Creative Analytics Dashboard

A full-stack personal analytics workspace for Fortnite Creative map creators and players to track, organize, and review maps using Epic Games' Fortnite Data API.

## 🎯 Project Overview

FNAtlas bridges the gap between Epic's raw Fortnite Data API and community-driven insights. While Epic provides basic metrics, FNAtlas enables users to create personal map collections, write reviews, and build a curated discovery experience beyond Epic's algorithm.

## ✨ Features

### Core Functionality
- **User Management**: Account creation, authentication, and profile management
- **Map Collections**: Create and organize custom map collections (e.g., "Favorite Parkour Maps")
- **Community Reviews**: Rate and review maps with 1-5 star ratings and detailed feedback
- **Epic API Integration**: Real-time map data and statistics from Epic Games' Fortnite Data API

### Key Capabilities
- Track map performance over time
- Discover maps through community curation
- Organize maps into themed collections
- Share insights through reviews and ratings

## 🏗️ Project Structure

```
fnatlas/
├── backend/                    # Spring Boot REST API
│   ├── src/main/java/
│   │   └── com/fnatlas/api/
│   │       ├── controllers/    # REST controllers
│   │       ├── services/       # Business logic
│   │       ├── entities/       # Database entities
│   │       ├── repositories/   # Data access layer
│   │       └── exceptions/     # Custom exceptions
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
├── frontend/                   # HTML/CSS/JavaScript (placeholder for now - structure will change)
│   ├── index.html              # Main landing page
│   ├── login.html              # Authentication
│   ├── users.html              # User management
│   ├── collections.html        # Map collections
│   ├── reviews.html            # Review system
│   ├── css/
│   ├── js/
│   └── assets/            
└── README.md
```

## 🛠️ Tech Stack

### Backend
- **Language**: Java 21
- **Framework**: Spring Boot 3.5.3
- **Database**: PostgreSQL
- **ORM**: Hibernate/JPA
- **Build Tool**: Maven
- **External API**: Epic Games Fortnite Data API

### Frontend
- **Languages**: HTML5, CSS3, JavaScript (ES6+)
- **Styling**: Bootstrap 5 (maybe)
- **HTTP Client**: Fetch API
- **Storage**: localStorage for session management

## 📄 License

This project is for educational purposes. 

**Note**: This project is not affiliated with Epic Games. Fortnite is a trademark of Epic Games, Inc.

## 🔗 Resources

- [Epic Games API Documentation](https://api.fortnite.com/ecosystem/v1/docs/)