# E-Commerce Application

A simple Spring Boot e-commerce demo built with Java 21. The app includes a home page, an inventory page backed by H2, and a minimal security setup for local development.

## Tech Stack

- Java 21
- Spring Boot 3.5.12
- Spring Web
- Spring Data JPA
- Spring Security
- Thymeleaf
- H2 Database

## Features

- Clean landing page with navigation to inventory
- Inventory page that reads items from the database
- In-memory H2 database with schema and seed data
- Docker image for containerized runs

## Requirements

- JDK 21
- Maven 3.9+
- Docker, if you want to run the container image

## Run Locally

```bash
mvn spring-boot:run
```

Open the app at `http://localhost:8086`.

## Run With Docker

```bash
docker build -t ecommerce-app .
docker run -p 8086:8086 ecommerce-app
```

## Project Structure

- `src/main/java` - application code
- `src/main/resources/templates` - Thymeleaf views
- `src/main/resources/schema.sql` - database schema
- `src/main/resources/data.sql` - sample inventory data
- `Dockerfile` - container build configuration

## Notes

- The application uses an in-memory H2 database, so data resets on restart.
- Spring Security is configured for open local access during development.