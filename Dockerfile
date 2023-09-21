FROM openjdk:17
ARG JAR_FILE=build/libs/*-SNAPSHOT.jar
WORKDIR /app
COPY . /app
RUN ["chmod", "+x", "/app/gradlew"]
RUN ["ls", "-la", "/app"]
#COPY ${JAR_FILE} product.jar
EXPOSE 8080
#ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=dev","/app/build/libs/*-SNAPSHOT.jar"]
ENTRYPOINT java -jar -Dspring.profiles.active=dev /app/build/libs/*-SNAPSHOT.jar
#ENTRYPOINT /app/gradlew bootRun