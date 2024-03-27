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

---

# User Module API Documentation

## Create User

- **Endpoint:** `POST /v1/api/user/`
- **Description:** Create a new user.
- **Request Body:**
  ```json
  {
    "name": "string",
    "email": "string",
    "password": "string",
    "about": "string"
  }
  ```
- **Example Request:**
  ```http
  POST http://127.0.0.1:9090/v1/api/user/
  Content-Type: application/json

  {
      "name": "Surajit",
      "email": "surajitshaw.official@gmail.com",
      "password": "123456",
      "about": "Hi, I am a Java developer."
  }
  ```
- **Response Body:**
  ```json
  {
    "status": 201,
    "message": "User created successfully",
    "data": {
        "id": "integer",
        "name": "string",
        "email": "string",
        "password": "string",
        "about": "string"
    },
    "location": "string"
  }
  ```
- **Example Response:**
  ```json
  {
      "status": 201,
      "message": "User created successfully",
      "data": {
          "id": 4,
          "name": "Surajit",
          "email": "surajitshaw.official@gmail.com",
          "password": "$2a$10$FWoTNX9tjpWNJIMS9abOmOwh4Z9wAB3290sSCzY3AyoIVoV3zthsm",
          "about": "Hi, I am a Java developer."
      },
      "location": "http://127.0.0.1:9090/v1/api/user/4"
  }
  ```

## Get All Users

- **Endpoint:** `GET /v1/api/user/`
- **Description:** Retrieve all users.
- **Example Request:**
  ```http
  GET http://127.0.0.1:9090/v1/api/user/
  ```
- **Response Body:**
  ```json
  {
    "status": 200,
    "message": "All users retrieved successfully",
    "data": [
        {
            "id": "integer",
            "name": "string",
            "email": "string",
            "password": "string",
            "about": "string"
        }
    ],
    "location": "null"
  }
  ```
- **Example Response:**
  ```json
  {
      "status": 200,
      "message": "All users retrieved successfully",
      "data": [
          {
              "id": 2,
              "name": "Surajit",
              "email": "surajit@gmail.com",
              "password": "$2a$10$x1bOchkNbeWw/.HU339KR.s60EHsbfagkWQy9YGai./yiH9GPr/DO",
              "about": "Hi, I am a Java developer."
          },
          {
              "id": 3,
              "name": "Surajit",
              "email": "surajit.official@gmail.com",
              "password": "$2a$10$x1bOchkNbeWw/.HU339KR.s60EHsbfagkWQy9YGai./yiH9GPr/DO",
              "about": "Hi, I am a Java developer."
          },
          {
              "id": 4,
              "name": "Surajit",
              "email": "surajitshaw.official@gmail.com",
              "password": "$2a$10$FWoTNX9tjpWNJIMS9abOmOwh4Z9wAB3290sSCzY3AyoIVoV3zthsm",
              "about": "Hi, I am a Java developer."
          }
      ],
      "location": "null"
  }
  ```

## Get Single User

- **Endpoint:** `GET /v1/api/user/{userId}`
- **Description:** Retrieve a single user by ID.
- **Example Request:**
  ```http
  GET http://127.0.0.1:9090/v1/api/user/3
  ```
- **Response Body:**
  ```json
  {
    "status": 200,
    "message": "User retrieved successfully",
    "data": {
        "id": "integer",
        "name": "string",
        "email": "string",
        "password": "string",
        "about": "string"
    },
    "location": "null"
  }
  ```
- **Example Response:**
  ```json
  {
      "status": 200,
      "message": "User retrieved successfully",
      "data": {
          "id": 3,
          "name": "Surajit",
          "email": "surajit.official@gmail.com",
          "password": "$2a$10$x1bOchkNbeWw/.HU339KR.s60EHsbfagkWQy9YGai./yiH9GPr/DO",
          "about": "Hi, I am a Java developer."
      },
      "location": "null"
  }
  ```

## Update User

- **Endpoint:** `PUT /v1/api/user/{userId}`
- **Description:** Update an existing user by ID.
- **Request Body:**
  ```json
  {
    "name": "string",
    "email": "string",
    "password": "string",
    "about": "string"
  }
  ```
- **Example Request:**
  ```http
  PUT http://127.0.0.1:9090/v1/api/user/3
  Content-Type: application/json

  {
      "name": "Updated Name",
      "email": "updated_email@example.com",
      "password": "newpassword",
      "about": "Updated about section"
  }
  ```
- **Response Body:**
  ```json
  {
    "status": 200,
    "message": "User updated successfully",
    "data": {
        "id": "integer",
        "name": "string",
        "email": "string",
        "password": "string",
        "about": "string"
    },
    "location": "null"
  }
  ```
- **Example Response:**
  ```json
  {
      "status": 200,
      "message": "User updated successfully",
      "data": {
          "id": 3,
          "name": "Updated Name",
          "email": "updated_email@example.com",
          "password": "$2a$10$FWoTNX9tjpWNJIMS9abOmOwh4Z9wAB3290sSCzY3AyoIVoV3zthsm",
          "about": "Updated about section"
      },
      "location": "null"
  }
  ```

## Delete User

- **Endpoint:** `DELETE /v1/api/user/{userId}`
- **Description:** Delete a user.



# Category Module API Documentation

## Create Category

- **Endpoint:** `POST /v1/api/category`
- **Description:** Create a new category.
- **Request Body:**
  ```json
  {
    "categoryTitle": "string",
    "categoryDescription": "string"
  }
  ```
- **Example Request:**
  ```http
  POST http://127.0.0.1:9090/v1/api/category
  Content-Type: application/json

  {
      "categoryTitle": "gaming",
      "categoryDescription": "Let's start with the best and interesting topic ever."
  }
  ```
- **Response Body:**
  ```json
  {
    "status": 201,
    "message": "Category created successfully",
    "data": {
        "id": "integer",
        "categoryTitle": "string",
        "categoryDescription": "string"
    },
    "location": "null"
  }
  ```
- **Example Response:**
  ```json
  {
      "status": 201,
      "message": "Category created successfully",
      "data": {
          "id": 5,
          "categoryTitle": "gaming",
          "categoryDescription": "Let's start with the best and interesting topic ever."
      },
      "location": "null"
  }
  ```

## Get All Categories

- **Endpoint:** `GET /v1/api/category`
- **Description:** Retrieve all categories.
- **Example Request:**
  ```http
  GET http://127.0.0.1:9090/v1/api/category
  ```
- **Response Body:**
  ```json
  {
    "status": 200,
    "message": "All categories retrieved successfully",
    "data": [
        {
            "id": "integer",
            "categoryTitle": "string",
            "categoryDescription": "string"
        }
    ],
    "location": "null"
  }
  ```
- **Example Response:**
  ```json
  {
      "status": 200,
      "message": "All categories retrieved successfully",
      "data": [
          {
              "id": 1,
              "categoryTitle": "sports",
              "categoryDescription": "All blog related to any sports should belong to this category."
          },
          {
              "id": 3,
              "categoryTitle": "programming",
              "categoryDescription": "where we will talk in binary only"
          },
          {
              "id": 5,
              "categoryTitle": "gaming",
              "categoryDescription": "Let's start with the best and interesting topic ever."
          }
      ],
      "location": "null"
  }
  ```

## Delete Category

- **Endpoint:** `DELETE /v1/api/category/{categoryId}`
- **Description:** Delete a category by ID.
- **Example Request:**
  ```http
  DELETE http://127.0.0.1:9090/v1/api/category/6
  ```
- **Response Body:**
  ```json
  {
    "status": 200,
    "message": "Category deleted successfully",
    "data": "null",
    "location": "null"
  }
  ```
- **Example Response:**
  ```json
  {
      "status": 200,
      "message": "Category deleted successfully",
      "data": "null",
      "location": "null"
  }
  ```


# Post Module API Documentation

## Create Post

- **Endpoint:** `POST /v1/api/posts/user/{userId}/category/{categoryId}`
- **Description:** Create a new post under a specific user and category.
- **Request Body:**
  ```json
  {
    "title": "string",
    "content": "string"
  }
  ```
- **Example Request:**
  ```http
  POST http://127.0.0.1:9090/v1/api/posts/user/3/category/3
  Content-Type: application/json

  {
      "title": "testing title",
      "content": "testing content"
  }
  ```
- **Response Body:**
  ```json
  {
    "status": 201,
    "message": "Post created successfully",
    "data": {
        "id": "integer",
        "title": "string",
        "content": "string",
        "imageName": "string",
        "user": {
            "id": "integer",
            "name": "string",
            "email": "string",
            "password": "string",
            "about": "string"
        },
        "category": {
            "id": "integer",
            "catgoryTitle": "string",
            "catgeDescription": "string"
        },
        "createdAt": "string"
    },
    "location": "null"
  }
  ```
- **Example Response:**
  ```json
  {
      "status": 201,
      "message": "Post created successfully",
      "data": {
          "id": 6,
          "title": "testing title",
          "content": "testing content",
          "imageName": "default.png",
          "user": {
              "id": 3,
              "name": "Surajit",
              "email": "surajit.official@gmail.com",
              "password": "$2a$10$x1bOchkNbeWw/.HU339KR.s60EHsbfagkWQy9YGai./yiH9GPr/DO",
              "about": "Hi, I am a Java developer."
          },
          "category": {
              "id": 3,
              "catgoryTitle": "programming",
              "catgeDescription": "where we will talk in binary only"
          },
          "createdAt": "2024-03-26T22:49:59.730081"
      },
      "location": "null"
  }
  ```

## Get All Posts

- **Endpoint:** `GET /v1/api/posts?sortBy={sortBy}&sortDir={sortDir}`
- **Description:** Retrieve all posts with pagination and sorting.
- **Query Parameters:**
  - `sortBy` (optional): Field to sort by (e.g., title, createdAt).
  - `sortDir` (optional): Sorting direction (asc or desc).
- **Example Request:**
  ```http
  GET http://127.0.0.1:9090/v1/api/posts?sortBy=title&sortDir=desc
  ```
- **Response Body:**
  ```json
  {
    "status": 200,
    "message": "All posts retrieved successfully",
    "data": {
        "pageNumber": "integer",
        "pageSize": "integer",
        "totalElements": "integer",
        "totalpages": "integer",
        "lastPage": "boolean",
        "content": [
            {
                "id": "integer",
                "title": "string",
                "content": "string",
                "imageName": "string",
                "user": {
                    "id": "integer",
                    "name": "string",
                    "email": "string",
                    "password": "string",
                    "about": "string"
                },
                "category": {
                    "id": "integer",
                    "catgoryTitle": "string",
                    "catgeDescription": "string"
                },
                "createdAt": "string"
            }
        ]
    },
    "location": "null"
  }
  ```
- **Example Response:**
  ```json
  {
      "status": 200,
      "message": "All posts retrieved successfully",
      "data": {
          "pageNumber": 0,
          "pageSize": 10,
          "totalElements": 1,
          "totalpages": 1,
          "lastPage": true,
          "content": [
              {
                  "id": 6,
                  "title": "testing title",
                  "content": "testing content",
                  "imageName": "default.png",
                  "user": {
                      "id": 3,
                      "name": "Surajit",
                      "email": "surajit.official@gmail.com",
                      "password": "$2a$10$x1bOchkNbeWw/.HU339KR.s60EHsbfagkWQy9YGai./yiH9GPr/DO",
                      "about": "Hi, I am a Java developer."
                  },
                  "category": {
                      "id": 3,
                      "catgoryTitle": "programming",
                      "catgeDescription": "where we will talk in binary only"
                  },
                  "createdAt": "2024-03-26T22:49:59.730081"
              }
          ]
      },
      "location": "null"
  }
  ```


## Get Single Post

- **Endpoint:** `GET /v1/api/posts/{postId}`
- **Description:** Retrieve a single post by its ID.
- **Example Request:**
  ```http
  GET http://127.0.0.1:9090/v1/api/posts/6
  ```
- **Response Body:**
  ```json
  {
    "status": 200,
    "message": "Post retrieved successfully",
    "data": {
        "id": "integer",
        "title": "string",
        "content": "string",
        "imageName": "string",
        "user": {
            "id": "integer",
            "name": "string",
            "email": "string",
            "password": "string",
            "about": "string"
        },
        "category": {
            "id": "integer",
            "catgoryTitle": "string",
            "catgeDescription": "string"
        },
        "createdAt": "string"
    },
    "location": "null"
  }
  ```
- **Example Response:**
  ```json
  {
      "status": 200,
      "message": "Post retrieved successfully",
      "data": {
          "id": 6,
          "title": "testing title",
          "content": "testing content",
          "imageName": "default.png",
          "user": {
              "id": 3,
              "name": "Surajit",
              "email": "surajit.official@gmail.com",
              "password": "$2a$10$x1bOchkNbeWw/.HU339KR.s60EHsbfagkWQy9YGai./yiH9GPr/DO",
              "about": "Hi, I am a Java developer."
          },
          "category": {
              "id": 3,
              "catgoryTitle": "programming",
              "catgeDescription": "where we will talk in binary only"
          },
          "createdAt": "2024-03-26T22:49:59.730081"
      },
      "location": "null"
  }
  ```

## Update Post

- **Endpoint:** `PUT /v1/api/posts/{postId}`
- **Description:** Update an existing post by its ID.
- **Request Body:**
  ```json
  {
    "title": "string",
    "content": "string"
  }
  ```
- **Example Request:**
  ```http
  PUT http://127.0.0.1:9090/v1/api/posts/6
  Content-Type: application/json

  {
      "title": "Updated title",
      "content": "Updated content"
  }
  ```
- **Response Body:**
  ```json
  {
    "status": 200,
    "message": "Post updated successfully",
    "data": {
        "id": "integer",
        "title": "string",
        "content": "string",
        "imageName": "string",
        "user": {
            "id": "integer",
            "name": "string",
            "email": "string",
            "password": "string",
            "about": "string"
        },
        "category": {
            "id": "integer",
            "catgoryTitle": "string",
            "catgeDescription": "string"
        },
        "createdAt": "string"
    },
    "location": "null"
  }
  ```
- **Example Response:**
  ```json
  {
      "status": 200,
      "message": "Post updated successfully",
      "data": {
          "id": 6,
          "title": "Updated title",
          "content": "Updated content",
          "imageName": "default.png",
          "user": {
              "id": 3,
              "name": "Surajit",
              "email": "surajit.official@gmail.com",
              "password": "$2a$10$x1bOchkNbeWw/.HU339KR.s60EHsbfagkWQy9YGai./yiH9GPr/DO",
              "about": "Hi, I am a Java developer."
          },
          "category": {
              "id": 3,
              "catgoryTitle": "programming",
              "catgeDescription": "where we will talk in binary only"
          },
          "createdAt": "2024-03-26T22:49:59.730081"
      },
      "location": "null"
  }
  ```

## Delete Post

- **Endpoint:** `DELETE /v1/api/posts/{postId}`
- **Description:** Delete a post by its ID.
- **Example Request:**
  ```http
  DELETE http://127.0.0.1:9090/v1/api/posts/6
  ```
- **Response Body:**
  ```json
  {
    "status": 200,
    "message": "Post deleted successfully",
    "data": "null",
    "location": "null"
  }
  ```
- **Example Response:**
  ```json
  {
      "status": 200,
      "message": "Post deleted successfully",
      "data": "null",
      "location": "null"
  }
  ```
# Getting Started: Setting Up the Project Locally

Setting up the project locally is quick and easy. Follow these steps to get started with your Spring Boot project:

1. **Clone the Repository:**
   - Begin by cloning the project repository from GitHub using the following command:
     ```
     git clone https://github.com/SURAJITSHAW/blog-app-api.git
     ```

2. **Open in IDE:**
   - Navigate to the project directory and open it in your preferred Integrated Development Environment (IDE) such as IntelliJ IDEA, Eclipse, or Spring Tool Suite.

3. **Configure MySQL Database:**
   - Ensure that MySQL is installed and running on your local machine.
   - Open the `application.properties` file located in the `src/main/resources` directory.
   - Configure the database connection properties such as URL, username, and password according to your MySQL setup.

4. **Build the Project:**
   - Build the project using Maven. Run the following command in the project root directory:
     ```
     mvn clean install
     ```

5. **Run the Application:**
   - Start the Spring Boot application by running the main class (`BlogAppApiApplication.java`) in your IDE.
   - Alternatively, you can run the application from the command line using Maven:
     ```
     mvn spring-boot:run
     ```

6. **Verify Installation:**
   - Once the application is up and running, access the API endpoints locally using tools like Postman or a web browser to ensure everything is working as expected.

With these steps, you should be all set to explore the functionalities of your Spring Boot project locally. Enjoy coding! 🚀
