FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/bms-0.0.1-SNAPSHOT.jar /app/bms-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "bms-0.0.1-SNAPSHOT.jar"]
