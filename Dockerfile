FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/*SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java", "-server", "-XX:+UseZGC", "-Xms384m", "-Xmx384m", "-XX:MaxMetaspaceSize=128m", "-jar", "/app.jar"]
