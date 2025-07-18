# FNAtlas - Fortnite Creative Analytics Dashboard

A full-stack personal analytics workspace for Fortnite Creative map creators and players to track, organize, and review maps using Epic Games' Fortnite Data API.

Note: This README was AI-generated.

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
│   │       ├── exceptions/     # Custom exceptions
│   │       └── config/         # Configuration classes
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
├── frontend/                   # React application
│   ├── src/
│   │   ├── pages/             # React page components
│   │   ├── services/          # API service layer
│   │   ├── App.jsx            # Main app with routing
│   │   └── main.jsx           # Entry point
│   ├── package.json
│   └── vite.config.js
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
- **Framework**: React 19.1.0
- **Build Tool**: Vite
- **UI Library**: Ant Design
- **Routing**: React Router
- **HTTP Client**: Axios
- **Storage**: localStorage for session management

## 🚀 Getting Started

### Backend
```bash
cd backend
./mvnw spring-boot:run
```
Runs on `http://localhost:8080`

### Frontend
```bash
cd frontend
npm install
npm run dev
```
Runs on `http://localhost:5173`

See individual README files in `/backend` and `/frontend` for detailed setup instructions.

## 📄 License

This project is for educational purposes. 

**Note**: This project is not affiliated with Epic Games. Fortnite is a trademark of Epic Games, Inc.

## 🔗 Resources

- [Epic Games API Documentation](https://api.fortnite.com/ecosystem/v1/docs/)
