# Quadratic Equation Solver

A Spring Boot-based REST API that solves quadratic equations.

## How To Compile and Run

The project uses Maven and can be compiled by running the following command from the project's root directory:

     mvn clean compile
    
Tests can be run using:

    mvn test
   
The application can be run locally using the Maven plugin:

    mvn spring-boot:run
    
See the [Spring Boot documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html) for more information. Note that Spring Boot will bind to `http://localhost:8080` by default.

## Swagger Documentation

The application generates its own Swagger documentation and is accessible at the following endpoint...

    http://localhost:8080/swagger-ui.html

## Solving An Equation

You must supply the `a`, `b` and `c` parameters of the equation 

```
ax^2 + bx + c
```

in your query. **Note** that `a` is not permitted to be 0 as that results in a linear equation.

Optionally, you may also want to increase the scale. The application uses Java's [BigDecimal](https://docs.oracle.com/javase/8/docs/api/java/math/BigDecimal.html) and permits you to alter the `scale` parameter.
The application uses the standard quadratic equation and supports complex numbers. **Scale is set to 10 by default and must be between 10 and 20**.

### Example Queries

These queries can be run from your web browser:

Query: `http://localhost:8080/v1/solution?a=2&b=4&c=1`

Expected Response:

```json

{
  "roots": [
    -0.2928932188,
    -1.7071067812
  ],
  "discriminant": 8,
  "rootsComplex": false
}

```

Query: `http://localhost:8080/v1/solution?a=5&b=2&c=1`


Expected Response:

```json

{
  "roots": [
    {
      "real": -0.2,
      "sign": "+",
      "imaginary": 0.4
    },
    {
      "real": -0.2,
      "sign": "-",
      "imaginary": 0.4
    }
  ],
  "discriminant": -16
}

```

If the equation results in a [complex number](https://en.wikipedia.org/wiki/Complex_number) we receive the above result which translates to:

    -0.2 ± 0.4i

