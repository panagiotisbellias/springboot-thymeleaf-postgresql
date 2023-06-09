# springboot-thymeleaf-postgresql
Spring Boot CRUD example using Thymeleaf template engine for View layer and Spring Data JPA with Database

## Dependencies

- Java 11
- Maven 3.8.6

## Standalone Execution

Copy the [src/main/resources/local.application.properties](src/main/resources/local.application.properties) to `src/main/resources/application.properties`
```bash
cp src/main/resources/local.application.properties src/main/resources/application.properties
```

Define properly the [src/main/resources/application.properties](src/main/resources/application.properties) after a postgres database is available
```vim
spring.datasource.url= jdbc:postgresql://localhost:5432/testdb
spring.datasource.username= postgres
spring.datasource.password= password

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation= true
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.PostgreSQLDialect

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto= update
```

Run ```mvn spring-boot:run``` to run the application in default port 8080

Visit [http://localhost:8080/tutorials](http://localhost:8080/tutorials) to see the application running and play with the functionality.

## Docker Execution

Copy the [src/main/resources/docker.application.properties](src/main/resources/docker.application.properties) to `src/main/resources/application.properties`
```bash
cp src/main/resources/docker.application.properties src/main/resources/application.properties
```

Build the docker image locally and run with docker-compose by running
```bash
./mvnw clean package -DskipTests # prerequisite so the needed jar can be created
docker build -f nonroot.Dockerfile .
docker-compose up
```

Visit [http://localhost:8080/tutorials](http://localhost:8080/tutorials) to see the application running and play with the functionality.

### Vulnerabilities Scanning
One way is through Docker like this
```bash
docker run --rm --volume /var/run/docker.sock:/var/run/docker.sock --name Grype anchore/grype:latest belpanos/thymeleaf-demo:latest
```

The output is already thrown in [grype_vulnerabilities.txt](grype_vulnerabilities.txt)
## Code Analysis with SonarQube

### Requirements

- Sonarqube (standalone installation recommended) v8.5.1.38104

Run ```mvn sonar:sonar``` to see the project and its analysis from [http://localhost:9000/projects](http://localhost:9000/projects)

## Resources
[https://www.bezkoder.com/spring-boot-thymeleaf-example/](https://www.bezkoder.com/spring-boot-thymeleaf-example/)   
[https://www.baeldung.com/spring-boot-postgresql-docker](https://www.baeldung.com/spring-boot-postgresql-docker)

Copyright © BezKoder, [panagiotisbellias](mailto:belliaspan@gmail.com)