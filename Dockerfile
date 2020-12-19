FROM openjdk:15-alpine

WORKDIR /app

COPY build/libs/api-boilerplate-*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]