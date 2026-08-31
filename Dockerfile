# --- STEP 1: Build Stage ---
# Use an official Maven image with Java 21 to compile the code
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the project files
COPY . .

# Build the jar file
RUN mvn clean package -DskipTests

# --- STEP 2: Run Stage ---
# Use the lightweight Temurin 21 image for running the app
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Copy the jar from the build stage (adjust 'API1-0.0.1-SNAPSHOT.jar' to match your actual jar name)
COPY --from=build /app/target/API1-1.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]