#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /app-backend/src
COPY pom.xml /app-backend
RUN mvn -f /app-backend/pom.xml clean package

#
# Package stage
#
FROM openjdk:19-jre-slim
COPY --from=build /app-backend/target/app-backend-0.0.1-SNAPSHOT.jar /usr/local/lib/backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/falcon.jar"]