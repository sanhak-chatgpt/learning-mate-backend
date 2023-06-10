FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/*SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java", "-XX:+UseZGC", "-jar", "/app.jar"]
