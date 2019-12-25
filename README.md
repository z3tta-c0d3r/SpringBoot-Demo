# Spring Boot JWT

![](https://img.shields.io/badge/build-success-brightgreen.svg)

# Stack

![](https://img.shields.io/badge/java_8-✓-blue.svg)
![](https://img.shields.io/badge/spring_boot-✓-blue.svg)
![](https://img.shields.io/badge/H2-%20%E2%9C%93-blue)
![](https://img.shields.io/badge/jwt-✓-blue.svg)
![](https://img.shields.io/badge/Junit-%E2%9C%93-blue)

# File structure

This project is a example for trainning with a lot of tools with java 8, spring-boot with spring security with jwt, H2 with JPA and junit test with mockito.

```
SpringDemo2/
 │
 ├── src/main/java/
 │   └── com.example.SpringDemo2
 │       ├── config
 │       │   └── details
 │       │   │   ├── CrmUserDetails.java
 │       │   │   └── CrmUserDetailsService.java
 │       │   │    
 |       |   ├── oauth2
 │       │   │   ├── AuthorizationServerConfig.java
 │       │   │   ├── JwtAuthorizationFilter.java
 │       │   │   ├── ResourceServerConfig.java
 │       │   │   ├── SecurityConfig.java
 │       │   │   └── ResourceServerConfig.java
 │       │   │
 │       │   ├── AppConfig.java
 │       │   ├── DbConfig.java
 │       │   └── SwaggerConfig.java
 │       │
 |       ├── constants
 │       │   └── Constants.java
 │       │   
 │       ├── controller
 │       │   ├── TestController.java
 │       │   └── UserController.java
 │       │
 │       ├── exception
 │       │   ├── CustomException.java
 │       │   └── GlobalExceptionController.java
 │       │   
 │       ├── model
 │       │   ├── User.java
 │       │   └── UserRole.java
 │       │
 │       ├── repository
 │       │   └── UserRepository.java
 │       │
 │       ├── service
 │       │   ├── IUserService.java
 │       │   └── UserService.java
 │       │
 │       └── SpringDemo2Application.java
 │
 ├── src/main/resources/
 │   ├── db
 │   ├── application.properties
 │   └── application.yml
 │
 ├── src/main/test/java
 │   └── com.example.SpringDemo2
 │       └── SpringDemo2ApplicationTests.java
 │
 ├── .gitignore
 ├── GITHUB.postman_collection.json
 ├── HELP.md
 ├── README.md
 ├── mvnw
 ├── mvnw.cmd
 └── pom.xml
```

# Introduction (https://jwt.io)



# How to use this code?

1. Make sure you have [Java 8](https://www.java.com/download/) and [Maven](https://maven.apache.org) installed

2. Fork this repository and clone it
  
  ```
  $ git clone https://github.com/<your-user>/SpringDemo2
  ```
  
3. Navigate into the folder  

  ```
  $ cd SpringDemo2 (or other name)
  ```

4. Install dependencies

  ```
  $ mvn install
  ```

5. Run the project

  ```
  $ mvn spring-boot:run
  ```

6. Navigate to `http://localhost:8080/swagger-ui.html` in your browser to check everything is working correctly.