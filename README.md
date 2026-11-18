# Pricing Service

Enterprise pricing microservice built with Spring Boot and Hexagonal Architecture.

## Prerequisites
- Java 17+
- Maven 3.8+

## How to Build and Run
```bash
mvn clean install
mvn spring-boot:run
```

## Running Tests
To run all unit and functional/E2E REST-assured tests:
```bash
mvn test
```

## API Endpoint
`GET /api/prices`

### Parameters:
- `brandId` (Long): Brand identifier (e.g., `1`)
- `productId` (Long): Product code identifier (e.g., `35455`)
- `applicationDate` (String): Date formatted as `yyyy-MM-dd-HH.mm.ss` (e.g., `2020-06-14-10.00.00`)

### Example Request:
```bash
curl -X GET "http://localhost:8080/api/prices?brandId=1&productId=35455&applicationDate=2020-06-14-10.00.00"
```
