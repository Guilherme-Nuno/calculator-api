FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/calculator-0.0.1-SNAPSHOT.jar /app/calculator-0.0.1-SNAPSHOT.jar
EXPOSE 9093
CMD ["java", "-jar", "calculator-0.0.1-SNAPSHOT.jar"]