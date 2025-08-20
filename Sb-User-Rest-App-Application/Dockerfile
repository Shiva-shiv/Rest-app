# ---------- Stage 1: Build the JAR ----------
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application (skip tests if needed)
RUN mvn clean package -DskipTests

# ---------- Stage 2: Run the JAR ----------
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose application port (Spring Boot default is 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
