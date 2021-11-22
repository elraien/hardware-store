# Spring Boot starter for Java 11
This is a starter including:
* Spring Boot Web starter
* Spring Boot Data JPA starter
    * Using MySQL
* Spring Boot Devtools and Annotation Processor
* A sample REST controller

## MySQL in Docker
`application.yml` already contains connection information to this DB.
```shell script
docker-compose up -d
```

Good luck!
Thank you!

-- Build from the command line with Maven --
mvn clean install

-- DB --
to create tables and insert initial values run create_tables.sql

-- Postman --
simple get request test:
http://localhost:8080/api/products

