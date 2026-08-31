# --- STEP 1: Build Stage ---
FROM maven:3.9-eclipse-temurin-26 AS build
WORKDIR /app

# Copy the project files
COPY . .

# Build the jar file
RUN mvn clean package -DskipTests

# --- STEP 2: Run Stage ---
FROM eclipse-temurin:26-jdk
WORKDIR /app

# Copy the jar
COPY --from=build /app/target/API1-1.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]