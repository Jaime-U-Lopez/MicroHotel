
## Microservicio de Reservas de Hotel
Este microservicio de API Rest en Java con Spring Boot permite el control de operaciones relacionadas con las reservas, creación de clientes y habitaciones en una empresa de hoteles.

El proyecto se ha desarrollado utilizando una arquitectura de capas y código limpio. Incluye operaciones CRUD con diferentes entidades, integración continua con Git Actions para la validación de tests y su respectivo deploy en RailWay. En el front se apoya en Swagger.

Para acceder a la documentación de la API, haz clic [aquí](http://localhost:8080/swagger-ui/index.html#/)

<div style="display:flex;">

<img src="https://user-images.githubusercontent.com/50783391/232255841-ca02df30-398c-4b98-b9eb-098f2adc092c.png" width="200">
<img src="https://user-images.githubusercontent.com/50783391/232256220-e0f32b8e-a432-432a-ba9a-c023f9894e79.png" width="250">

</div >



## Tecnologías utilizadas
- Java 11
- Spring Boot 2.7.11
- Conexión a base de datos MySQL (com.mysql:mysql-connector-j:8.0.32)
- Data JPA 2.7.11
- JUnit 4 version 2.7.11
- Mockito para pruebas unitarias como interceptor y emulador de base de datos
- Swagger 3.0.0
- Query personalizados de JPA
- Inyección de dependencias
- Maven como gestor de dependencias
- Patrones DTO y diseño DAO para Repository
- Principios SOLID
- Diseño de Cadena de Responsabilidad
- Diseño por capas (Código Limpio)



## Entidades :

- Cliente
- Reserva
- Habitación

## Diagrama de entidades :

![image](https://user-images.githubusercontent.com/50783391/232255670-c81b5b6f-8f83-4c98-9947-a2dd1e7d1141.png)



## Diagrama de Clases :
![DiseñoClasesDiagramaReservas](https://user-images.githubusercontent.com/50783391/229843341-1ecf333b-0e3f-4128-88cf-7cd095d0ab08.png)

## Peticiones de EndPoint


### Crear un Cliente

![image](https://user-images.githubusercontent.com/50783391/232256036-1bbd3be7-6531-4ddc-9d4a-ee0f06daecc0.png)


### Crear una Habitación

![image](https://user-images.githubusercontent.com/50783391/232256056-802af773-8704-4b10-a51c-7af101edaaeb.png)


### Crear una Reserva

![image](https://user-images.githubusercontent.com/50783391/232256068-42788c2b-03a8-4748-bd6b-e6c31db806c4.png)

