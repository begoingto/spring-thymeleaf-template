#FROM gradle:8.4-jdk17-alpine As builder
#WORKDIR /app
#COPY . .
## Build the application
#RUN gradle build --no-daemon

# FROM openjdk:17
# ARG JAR_FILE=build/libs/*-SNAPSHOT.jar
# WORKDIR /app
# ENV APP_ENV=prod
# RUN mkdir -p /images
# RUN chmod 777 /images
# COPY ${JAR_FILE} app.jar
# EXPOSE 8080
# ENTRYPOINT java -jar -Dspring.profiles.active=$APP_ENV app.jar

FROM gradle:8.7-jdk17 AS builder
WORKDIR /app

COPY build.gradle settings.gradle ./
COPY gradle ./gradle
RUN gradle dependencies --no-daemon || true
COPY src ./src
RUN gradle clean bootJar -x test --no-daemon

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar spring-app.jar
EXPOSE 8080

# Healthcheck using actuator readiness endpoint
HEALTHCHECK CMD ["sh", "-c", "nc -z localhost 8080"]

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","spring-app.jar"]
