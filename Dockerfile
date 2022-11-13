FROM openjdk:19-jdk-alpine
EXPOSE 8080
WORKDIR .

RUN apt-get update
RUN apt-get install -y maven

ADD pom.xml /pom.xml
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]

# Adding source, compile and package into a fat jar
ADD src ./src
RUN ["mvn", "package"]

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-jar","/app.jar"]