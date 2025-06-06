FROM openjdk:17-jdk-alpine

RUN addgroup -S appgroup && adduser -S appuser -G appgroup

WORKDIR /app

RUN chown -R appuser:appgroup /app

USER appuser

COPY target/gs-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
