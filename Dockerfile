FROM openjdk:17-jdk-alpine
FROM maven:3.8.5-openjdk-17

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src/ ./src/

RUN mvn clean package -DskipTests=true

RUN mv target/live-0.0.1-SNAPSHOT.jar app-1.0.0.jar

#RUN pwd && ls -la

ENTRYPOINT [ "java", "-jar", "app-1.0.0.jar" ]
