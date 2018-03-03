# Quadratic Equation Solver

A Spring Boot-based REST API that that solves quadratic equations.

## How To Compile and Run

The project uses Maven and can be compiled by running the following command from the project's root directory:

    mvn clean compile
    
Tests can be run using:
  
   mvn test
   
The application can be run locally using the Maven plugin:

    mvn spring-boot:run
    
See the [Spring Boot documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html) for more information. Note that Spring Boot will bind to `http://localhost:8080` by default.

## Solving An Equation

You must supply the `a`, `b` and `c` parameters of the equation 

```
ax^2 + bx + c
```

in your query. 

Optionally, you may also want to increase precision. The application uses Java's [BigDecimal](https://docs.oracle.com/javase/8/docs/api/java/math/BigDecimal.html) and permits you to alter the `scale` parameter.
The application uses the standard quadratic equation and supports complex numbers. **Scale is set to 10 by default**.

### Example Queries

These queries can be run from your web browser:

Query: `http://localhost:8080/v1/solution?a=2&b=4&c=1`

Expected Response:

```json

{
   "roots":[
      "-0.2928932188",
      "-1.7071067812"
   ],
   "discriminant":8.0000000000
}

```

