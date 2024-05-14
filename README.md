"# fxDeals" 


## Description

This Spring Boot application processes deals via a POST request.
 It enforces data validation, ensures unique deal IDs, 
persists data to a PostgreSQL database using spring JPA,
 and employs robust error handling and logging.
 A RestControllerAdvice class offers centralized exception handling. 
 Docker for containerized deployment.

## Technologies

- Java 17
- Spring Boot
- Spring Web
- Spring JPA for database interaction
- PostgreSQL
- Maven
- JUnit 5 and Mockito for unit testing
- Docker 


## Features

- Accepts POST requests for creating new deals
- Validates incoming data (e.g., required fields, currency code format..etc)
- Prevents duplicate deals by enforcing unique deal IDs in the database
- Saves validated deals to a PostgreSQL database using Spring JPA
- Provides error messages using RestControllerAdvice
- Offers centralized exception handling with proper logging
- Unit tests cover service and controller for robust functionality
- Optional Dockerized deployment for portability

## DataBase
- DB name: fx_deals
- DB username: postgres
- DB password: admin

```sql
CREATE TABLE deals(
id BIGSERIAL PRIMARY KEY,
deal_id UUID NOT NULL UNIQUE,
from_currency VARCHAR(3) NOT NULL,
to_currency varchar(3) NOT NULL,
deal_time TIMESTAMP NOT NULL,
amount DECIMAL NOT NULL 
);

CREATE INDEX dx_deal_id ON deals(deal_id);
```

## Testing

Unit tests using JUnit 5 and Mockito ensure functionality at the service and controller levels. 
test the endpoint working well using Postman.


## Endpoint
```
POST:
/api/create/deal

{
    "dealId": "304a671b-f18d-4f89-b36a-42bb607ffea9",
    "fromCurrency": "USD",
    "toCurrency": "EUR",
    "dealTime": "2024-05-13T19:46:25.255092Z",
    "amount": 150.0
}
```


### Manual Deployment
- Configure database connection details in application.properties.

### Dockerized Deployment
- Configure database connection details in application.properties.
spring.datasource.url=jdbc:postgresql://postgresdb:5432/fx_deals
- open terminal in the project Folder then run: docker-compose up -d

