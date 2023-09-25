# User Management
This project is simple a solution for a user management that handle user registration and user details management.

## Objective
The main objective of this project is to have high quality code and tests, with best practices.

## Installing / Getting started
This is a Spring Boot and Maven project, so it can directly run via maven with the following command:
- Via Maven:
```shell
mvn spring-boot:run
```
- Via Maven Wrapper:
```shell
./mvnw spring-boot:run
```

The project contains two REST APIs, the first one handle user registration and the second one returns details of a specific user.
Find below a specification and how to use each of them :

- User Registration API:

URL : ```http://localhost:9090/api/users ```

HTTP method: _POST_

Request body schema:
```json
{
  "userName" : "string",
  "birthday" : "yyyy-mm-dd",
  "countryOfResidence" : "string",
  "phoneNumber" : "string",
  "gender" : "enum"
}
```

> _username_, _birthday_ and _country_ are mandatory.

> _username_ is unique.

> _country_ value should be France.

> _birthday_ value should be grater then 18 years.

> _gender_ possible values are MALE, FEMALE or OTHERS.

Success response HTTP status: _201_ (Created)

Success response body schema:
```json
{
  "userName" : "string",
  "birthday" : "yyyy-mm-dd",
  "countryOfResidence" : "string",
  "phoneNumber" : "string",
  "gender" : "enum"
}
```

- User Details API:

URL : ```http://localhost:9090/api/users ```

HTTP method: _GET_

Path variable: _username_ (e.g: "MOUJAHID")

Success response HTTP status: _200_ (Ok)

Success response body schema:
```json
{
 "userName" : "string",
  "birthday" : "yyyy-mm-dd",
  "countryOfResidence" : "string",
  "phoneNumber" : "string",
  "gender" : "enum"
}
```

## Developing
### Built With
Technologies used in this project are:
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [JUnit 5](https://junit.org/junit5/)

### Prerequisites
- Java 17 or higher version already installed

## Tests
This project contains Integration and Unit tests that can be run via maven with the following command:
- Via Maven:
```shell
mvn test
```
- Via Maven Wrapper:
```shell
./mvnw test
```
## Docker image 
You can build and use the application as docker image.

ATTENTION : to build the image you have on the project directory

- Build the docker Image
```shell
docker build -t user-management:0.0.1 .
```

- Run image
```shell
docker run -p 9090:9090 user-management:0.0.1
```

## Other documentations support
Please find technical documentation (JavaDoc) and request samples (Postman collection) in the project.
