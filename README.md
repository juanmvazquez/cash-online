# REST API - Cash Online

## Technologies Used
* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [SpringBoot ](https://spring.io/)
* [Spring Security](https://docs.spring.io/spring-security/reference/index.html)
* [JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
* [H2 Data Base](https://www.h2database.com/html/main.html)
* [Mockito](https://site.mockito.org/)
* [JUnit](https://junit.org/junit5/)

## Propiedades del proyecto

El archivo application.properties se encuentra en el directorio src/main/resources.
```
# DataSource configuration
spring.datasource.url=jdbc:h2:mem:javatpoint;DB_CLOSE_ON_EXIT=TRUE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.sql.init.mode=always
spring.jpa.hibernate.ddl-auto=validate
#enabling the H2 console
spring.h2.console.enabled=true
```

## Seguridad

Se utilizó Spring Security para implementar la seguridad en la aplicación.

Para utilizar la aplicación, tienes dos tipos de usuarios:

Admin : Control total de la aplicación.
- username: admin
- password: admin

Usuario : este tipo puede ver la documentación, pero solo está autorizado para realizar solicitudes GET. No tiene permiso para entrar en la base de datos.
- username: user
- password: user

## Data Base
Como base de datos se utilizó H2 2.1.214. Si utiliza el usuario administrador, puede acceder a la base de datos:
[H2 Console](http://localhost:8080/h2-console/)

## Postman
Link a la colleccion de [Postman](https://api.postman.com/collections/16329113-fe4c727b-b0b7-4a68-8b73-baf80e9fbbd9?access_key=PMAT-01GYJWP2X9GAH661068W6CZV07)
