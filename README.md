# Calculator REST API

## Description
This project impleents a RESTful API of a calculator that supports basic operations (sum, subtraction, division and multiplication).
The API communicates with the calculator service through Kafka.

## Technologies used
- **Java 17**
- **Spring Boot**
- **Gradle**
- **Apache Kafka**
- **Docker & Docker Compose**
- **SLF4J + Logback**


## Project Structure
The project is divided in two differents modules:

- **`rest`**: Exposes the API, send the request to the calculator and returns the response
from the calculator.
- **`calculator`**: Receives requests from the API and processes the calculations.

## Build and Run

### **Build the project**
First, compile the project using Gradle:
```sh
./gradlew build
```

### **Run with Docker Compose**
This command will start the API, the processing module, and the Kafka environment:
```sh
docker-compose up --build
```
The API will be available at `http://localhost:8080/api/{operation}?a=NUM1&b=NUM2`.

## Available Endpoints

| Method  | Endpoint                 | Description |
|---------|--------------------------|-------------|
| `GET`   | `/api/sum?a=1&b=2`       | Returns `1 + 2 = 3` |
| `GET`   | `/api/sub?a=5&b=2`       | Returns `5 - 2 = 3` |
| `GET`   | `/api/mul?a=3&b=4`       | Returns `3 * 4 = 12` |
| `GET`   | `/api/div?a=10&b=2`      | Returns `10 / 2 = 5` |

If an operation is not possible (e.g., division by zero), the system returns `400 Bad Request` with an error message.

## Usage Example

### **Request**:
```http
GET /api/sum?a=10.5&b=5.2 HTTP/1.1
Host: localhost:8080
Accept: application/json
```

### **Response**:
```json
{
  "result": 15.7
}
```

## Logging and Identifiers
- Each request receives a **X-Request-ID** for tracking.
- MDC is used to propagate this identifier in logs.
- The logs get appended to a file
