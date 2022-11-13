#
# Build stage
#
FROM maven:3.8.6-eclipse-temurin-19-alpine AS build
COPY src /app-backend/src
COPY pom.xml /app-backend
RUN mvn -f /app-backend/pom.xml clean package

#
# Package stage
#
FROM openjdk:19-jdk-alpine
COPY --from=build /app-backend/target/app-backend-0.0.1-SNAPSHOT.jar /usr/local/lib/backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/backend.jar"]