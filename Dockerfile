FROM openjdk:17
ARG JAR_FILE=build/libs/*-SNAPSHOT.jar
WORKDIR /app
COPY . /app
RUN ["chmod", "+x", "gradlew"]
RUN ["./gradlew", "clean", "build"]
COPY ${JAR_FILE} product.jar
EXPOSE 8080
#ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=dev","/app/build/libs/*-SNAPSHOT.jar"]
ENTRYPOINT java -jar -Dspring.profiles.active=dev product.jar
#ENTRYPOINT /app/gradlew bootRun
