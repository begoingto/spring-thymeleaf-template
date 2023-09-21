FROM openjdk:17

ARG JAR_FILE=build/libs/*-1.0.0.jar

COPY ${JAR_FILE} surveybox.jar

ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=dev","/surveybox.jar"]

EXPOSE 8080