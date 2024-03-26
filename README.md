# Blog API Documentation

Welcome to the documentation for the Blog API. This API provides endpoints for managing a simple blogging platform, allowing users to create, read, update, and delete blog posts.

## Base URL

The base URL for all API endpoints is:

```
http://127.0.0.1:9090/v1
```

# Authentication Endpoint

## Obtain JWT Token

- **Endpoint:** `POST /v1/auth/login`
- **Description:** Obtain a JSON Web Token (JWT) for authentication. This token is required for accessing all other endpoints in the application.
- **Request Body:**
  
  ```json
  {
    "username": "string",
    "password": "string"
  }
  ```
- **Example Request:**
  ```http
  POST http://127.0.0.1:9090/v1/auth/login
  Content-Type: application/json

  {
      "username": "surajit.official@gmail.com",
      "password": "123456"
  }
  ```
- **Response Body:**
  ```json
  {
    "jwtToken": "string",
    "username": "string"
  }
  ```
- **Example Response:**
  ```json
  {
      "jwtToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdXJhaml0Lm9mZmljaWFsQGdtYWlsLmNvbSIsImlhdCI6MTcxMTQ3MjQyNCwiZXhwIjoxNzExNDkwNDI0fQ.impXDeuWWaAbfMDB0g5b2COiBZYdY84m4egHerbqx7ba71riLdF66l6r7TCigGBbkc3-bmePMb6KWSKsTw118A",
      "username": "surajit.official@gmail.com"
  }
  ```

