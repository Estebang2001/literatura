
# Proyecto de Literatura

Este proyecto es una aplicación Java basada en Spring Boot que permite gestionar libros y autores, así como realizar consultas específicas sobre ellos.

## Requisitos

- Java 17 o superior
- Maven 3.6.0 o superior
- Base de datos compatible con JPA (por ejemplo, MySQL, PostgreSQL, H2)

## Configuración

Clona el repositorio:

```sh
git clone https://github.com/tu-usuario/tu-repositorio.git
cd tu-repositorio

Configura la base de datos en el archivo application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/literatura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update

Compila y ejecuta la aplicación:

mvn clean install
mvn spring-boot:run

Uso
Listar Libros por Idioma
Ejecuta la clase Principal para iniciar el menú interactivo. Selecciona la opción para listar libros por idioma e ingresa el idioma deseado.  
Listar Autores Vivos en un Año
Selecciona la opción para listar autores vivos en un año específico e ingresa el año deseado.
Estructura del Proyecto
src/main/java/com/aluracursos/literatura/model/Autor.java: Entidad JPA que representa un autor.
src/main/java/com/aluracursos/literatura/model/Libro.java: Entidad JPA que representa un libro.
src/main/java/com/aluracursos/literatura/model/DatosAutor.java: Clase para mapear datos de autores desde una API externa.
src/main/java/com/aluracursos/literatura/model/Flat.java: Clase para mapear datos de libros desde una API externa.
src/main/java/com/aluracursos/literatura/repository/AutorRepository.java: Repositorio JPA para acceder a los datos de los autores.
src/main/java/com/aluracursos/literatura/repository/LibroRepository.java: Repositorio JPA para acceder a los datos de los libros.
src/main/java/com/aluracursos/literatura/service/ConsumoAPI.java: Servicio para consumir una API externa.
