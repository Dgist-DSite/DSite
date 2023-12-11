FROM openjdk:17-jdk
COPY build/libs/DSite-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/bootsecurity.p12 /app/src/main/resources/
ENTRYPOINT ["java", "-jar", "app.jar"]