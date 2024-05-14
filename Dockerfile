FROM maven:3.8.3-openjdk-17

WORKDIR /fxDeals

COPY . .

RUN mvn clean package

CMD mvn spring-boot:run