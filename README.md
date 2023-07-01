# SpringBootDemo
Demo of Spring boot

Ref document for Application properties:
https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html


## Spring boot with H2 DB 
H2DB Console App:
- http://localhost:8080/spring-boot-demo/h2-console/


## Spring boot with Postgres DB.
- docker run -e PGADMIN_DEFAULT_EMAIL=db@local.com -e PGADMIN_DEFAULT_PASSWORD=password -p 8081:80 dpage/pgadmin4
- docker run --name dev-db -e POSTGRES_PASSWORD=password -e POSTGRES_USER=postgres -p 5432:5432 postgres
