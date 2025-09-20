<<<<<<< HEAD
# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn -q -e -B -DskipTests dependency:go-offline

# Copy source code and build
COPY src ./src
RUN mvn -q -e -B -DskipTests package

# Stage 2: Run
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
=======
# Multi-stage Dockerfile for Game App
# This builds both backend and frontend

# Stage 1: Build Backend
FROM maven:3.9.6-eclipse-temurin-21 AS backend-build
WORKDIR /app/backend

# Copy backend files
COPY backend/pom.xml .
COPY backend/src ./src

# Build backend
RUN mvn -q -e -B -DskipTests dependency:go-offline
RUN mvn -q -e -B -DskipTests package

# Stage 2: Build Frontend
FROM node:18-alpine AS frontend-build
WORKDIR /app/client

# Copy frontend files
COPY client/package*.json ./
RUN npm ci

COPY client/ ./
RUN npm run build

# Stage 3: Final Runtime Image
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy backend jar
COPY --from=backend-build /app/backend/target/*.jar app.jar

# Copy frontend build
COPY --from=frontend-build /app/client/dist ./static

# Expose port
EXPOSE 8080

# Start backend (frontend will be served as static files)
ENTRYPOINT ["java", "-jar", "app.jar"]
>>>>>>> 2ebe552bc81967a4fa9bfec1352665c6c4cae172
