# Microservices-Based Credit Management System

## Overview
This project is a microservices-based application for managing credit-related services, using Spring Boot, Eureka Server for service discovery, and Spring Cloud Gateway for routing. It includes several microservices:

- **msclient**: Manages client data.
- **mscards**: Handles credit card related operations.
- **mscreditrating**: Manages credit rating services.
- **Eureka Server**: For service discovery.
- **Spring Cloud Gateway**: Routes requests to the appropriate microservices.

## Prerequisites
- Java JDK 11 or later
- Maven 3.2+
- Spring Boot 2.x
- Docker and Docker Compose
- An instance of Eureka Server running on port 8761

## Running the Project
1. **Start RabbitMQ with Docker**: Run the RabbitMQ container using Docker Compose:
   ```shell
   docker-compose up -d

This starts RabbitMQ, which is accessible on ports 5672 (AMQP) and 15672 (management interface).

## Start the Eureka Server
Ensure the Eureka server is up and running on port 8761.

## Start the Microservices
Each microservice can be started using:

```shell
mvn spring-boot:run
```

## Ensure Registration with Eureka
Make sure that `msclient`, `mscards`, and `mscreditrating` are registered with Eureka.

## Start the Gateway
Run the Spring Cloud Gateway which routes requests to the microservices.

## Microservices

### msclient
- **Function**: Manages client data.
- **Endpoints**:
  - Check service status.
  - Save client information.
  - Retrieve client data by CPF.

### mscards
- **Function**: Handles operations related to credit cards.
- **Endpoints**:
  - Register new cards.
  - List cards based on income.
  - Retrieve cards associated with a client.

### mscreditrating
- **Function**: Manages credit rating processes.
- **Endpoints**:
  - Check service status.
  - Assess client credit rating.
  - Handle credit card issuance requests.

## Eureka Server
- **Role**: Acts as a service registry for service discovery among microservices.

## Spring Cloud Gateway
- **Function**: Routes HTTP requests to the appropriate microservices using Eureka for discovery.

## Docker
This project uses Docker to run RabbitMQ.
- **Configuration**: Defined in `docker-compose.yml`.
- **Requirements**:
  - Ensure Docker is installed.
  - Use `docker-compose` to start the service.

## Configuration
- **Requirement**: Ensure correct setup in `application.yml` or `application.properties` files for each microservice.
- **Includes**:
  - Eureka client settings.
  - Spring Cloud Gateway routing.
   
