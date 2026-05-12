Proyecto Gremio: Arquitectura de Microservicios
Desarrollo FullStack I - Evaluación Parcial 2
Descripción del Proyecto
Este sistema es una solución distribuida basada en una arquitectura de microservicios diseñada para gestionar la logística y persistencia 
de un gremio de aventureros. El proyecto implementa un ecosistema donde se orquestan entidades como aventureros, equipos, gremios, 
profesiones y misiones, asegurando la integridad referencial y la escalabilidad mediante bases de datos independientes por servicio.
Integrantes
Eduardo Barrera  - Líder del equipo (Arma, Pocion, Mascota)
Gabriel Ibarra  - Desarrollador (Gremio, Mision, Faccion)
Diego Sotoe  - Desarrollador (Rango, Reputacion, Profesion)

Tecnologías utilizadas
Java 21 & Spring Boot 4.x
Spring Data JPA: Para la persistencia rúnica con Hibernate
MySQL: Motor de base de datos relacional
Lombok: Para la reducción de código boilerplate (@Data, @Builder)
Feign Client / WebClient: Comunicación entre microservicios
SLF4J + Logback: Logs estructurados para trazabilidad

Funcionalidades Implementadas
El proyecto cumple con los siguientes artefactos técnicos:
Patrón CSR (IE 1.2.1): Separación clara entre Controller, Service y Repository
Gestión de Aventureros: CRUD completo con persistencia real
Relaciones OneToOne, OneToMany, ManyToMany (con entidad intermedia)
Vínculos Sagrados (@OneToOne): Relación exclusiva entre Mascotas y Parties para asegurar el "Hechizo de Exclusividad" [Conversación previa, 24].
Reportes Personalizados
Manejo Global de Errores: Uso de @ControllerAdvice para devolver respuestas semánticas

Pasos para la Ejecución
1. Preparara la Base de Datos
Asegúrate de tener iniciado MySQL (Laragon/HeidiSQL)
Crea el esquema principal: CREATE DATABASE db_proyecto_gremio;

2. Configuración de application.properties
Verifica que las credenciales coincidan con tu entorno local:
spring.datasource.url=jdbc:mysql://localhost:3306/db_proyecto_gremio
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update

3. Despliegue
*   Clona el repositorio: `git clone [https://github.com/SavaMey/proyecto_gremio.git]
*   Importa el proyecto en VS Code
*   Ejecuta la clase principal `ProyecotGremioApplication.java`

---
