# Smart Campus REST API (JAX-RS)

## Overview
This project implements a RESTful API for a Smart Campus system using JAX-RS.  
The API manages Rooms, Sensors, and Sensor Readings with proper validation, nested resources, and structured error handling.

---

## Base URL
http://localhost:8080/Coursework/api/v1

---

## How to Run
1. Open the project in Apache NetBeans
2. Clean and Build the project
3. Run using Apache Tomcat
4. Use Postman or curl to test endpoints

---

## Sample CURL Commands

### Get API Info
## Create Room
curl -X POST http://localhost:8080/Coursework/api/v1/rooms \
-H "Content-Type: application/json" \
-d "{\"id\":\"LIB-301\",\"name\":\"Library Study Room\",\"capacity\":50}"

##Get All Rooms

curl http://localhost:8080/Coursework/api/v1/rooms

##Create Sensor

curl -X POST http://localhost:8080/Coursework/api/v1/sensors \
-H "Content-Type: application/json" \
-d "{\"id\":\"CO2-001\",\"type\":\"CO2\",\"status\":\"ACTIVE\",\"currentValue\":0.0,\"roomId\":\"LIB-301\"}"

##Get Sensor Readings

curl http://localhost:8080/Coursework/api/v1/sensors/CO2-001/readings

---

# Conceptual Report

## Part 1: Service Architecture & Setup

### JAX-RS Resource Lifecycle
By default, JAX-RS resource classes are request-scoped, meaning a new instance is created for each incoming request. This avoids shared state between threads and reduces concurrency issues. If singleton resources were used, shared data structures such as HashMaps could cause race conditions, requiring synchronization or thread-safe alternatives like ConcurrentHashMap.

### HATEOAS (Hypermedia)
Hypermedia allows APIs to include navigational links in responses so clients can dynamically discover available resources. This reduces dependency on static documentation and makes the API more flexible and self-descriptive.

---

## Part 2: Room Management

### Returning IDs vs Full Objects
Returning only IDs reduces response size and bandwidth usage but requires additional requests to fetch full details. Returning full objects provides complete data immediately but increases payload size. The choice depends on performance versus convenience.

### DELETE Idempotency
DELETE is idempotent because sending the same DELETE request multiple times results in the same system state. After the first deletion, additional requests do not change the system further.

---

## Part 3: Sensor Operations & Linking

### Content-Type Mismatch
If a client sends a request in a format other than JSON, JAX-RS returns a 415 Unsupported Media Type error because the server cannot process unsupported formats.

### QueryParam vs PathParam
Query parameters are better for filtering because they represent optional conditions on collections. Path parameters identify specific resources.

---

## Part 4: Sub-Resource Locator

### Benefits
The sub-resource locator pattern separates nested resource logic into different classes. This improves code structure, reduces complexity, and makes the API easier to maintain and scale.

---

## Part 5: Error Handling & Logging

### 422 vs 404
HTTP 422 is more appropriate when the request is valid but contains incorrect data, such as an invalid room reference. 404 indicates a missing endpoint, not invalid payload data.

### Stack Trace Security
Exposing stack traces can reveal internal system details such as class names and file paths. This information can be exploited by attackers, so APIs should return generic error messages instead.

### Logging with Filters
Using JAX-RS filters centralizes logging logic, ensuring consistent request and response logging across all endpoints while keeping resource classes clean.

---

## Technologies Used
- Java
- JAX-RS (Jersey)
- Apache Tomcat
- Maven

---

## Author
W2120112 Tharin Perera
