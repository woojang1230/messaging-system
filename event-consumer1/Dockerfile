FROM openjdk:17-jdk-oracle

EXPOSE 8080

ADD ./build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar", "-Dspring.profiles.active=docker"]
