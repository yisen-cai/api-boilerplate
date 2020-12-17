FROM openjdk:14-alpine

WORKDIR /app

COPY build/libs/api-boilerplate-*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]