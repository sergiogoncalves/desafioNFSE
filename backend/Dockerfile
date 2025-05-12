FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/*.jar app.jar
CMD ["sh", "-c", "sleep 10 && java -jar app.jar"]