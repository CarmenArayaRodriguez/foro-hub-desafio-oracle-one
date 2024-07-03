# ForoHub

ForoHub es una API desarrollada como parte de un desafío para un curso de Alura. Este proyecto consiste en una API RESTful para gestionar tópicos (temas de discusión) en un foro, con autenticación basada en JWT (JSON Web Tokens).

## Tabla de Contenidos

- [Características](#características)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Uso](#uso)
- [Endpoints](#endpoints)
- [Autenticación](#autenticación)

## Características

- Gestión de tópicos: creación, consulta, actualización y eliminación.
- Autenticación basada en JWT.
- Validación de usuarios.
- Integración con base de datos MySQL.
- Configuración a través de variables de entorno.

## Tecnologías Utilizadas

- Java 17
- Spring Boot 3.3.1
- Spring Security
- Spring Data JPA
- Flyway
- JWT (JSON Web Token)
- MySQL
- Maven

## Instalación

Para ejecutar este proyecto localmente, sigue los siguientes pasos:

1. Clona el repositorio:
   ```
   git clone https://github.com/CarmenArayaRodriguez/foro-hub-desafio-oracle-one.git
   ```

2. Configura las variables de entorno. Puedes utilizar un archivo .env en el directorio raíz del proyecto con las siguientes variables:

```
.env
DB_URL=jdbc:mysql://localhost:3306/forohub
DB_USERNAME=root
DB_PASSWORD=tu_contraseña
JWT_SECRET=tu_secreto_jwt
JWT_EXPIRATION=86400000
```

3. Ejecuta el proyecto con Maven:

```
./mvnw spring-boot:run
```

## Configuración

### Base de Datos
Este proyecto utiliza MySQL como base de datos. Asegúrate de tener una instancia de MySQL en ejecución y crea una base de datos llamada **forohub**.

### Migraciones
Las migraciones de la base de datos están manejadas por Flyway. Las migraciones se ejecutarán automáticamente al iniciar la aplicación.

## Uso

### Endpoints
Los principales endpoints de la API son:

- POST /login: Iniciar sesión y obtener un token JWT.
- GET /topicos: Listar todos los tópicos.
- GET /topicos/{id}: Obtener detalles de un tópico específico.
- POST /topicos: Crear un nuevo tópico.
- PUT /topicos/{id}: Actualizar un tópico existente.
- DELETE /topicos/{id}: Eliminar un tópico.

### Ejemplo de Uso

#### Iniciar Sesión

Para iniciar sesión y obtener un token JWT, envía una solicitud POST a `/login` con el siguiente cuerpo JSON:

```
{
  "username": "tu_usuario",
  "password": "tu_contraseña"
}
```

#### Crear un Nuevo Tópico

Para crear un nuevo tópico, envía una solicitud POST a `/topicos` con el siguiente cuerpo JSON y el token JWT en el encabezado de autorización:

```
{
  "titulo": "Nuevo Título",
  "mensaje": "Descripción del tópico",
  "autor": "nombre_del_autor",
  "curso": "nombre_del_curso"
}
```

#### Actualizar un Tópico
Para actualizar un tópico existente, envía una solicitud PUT a /topicos/{id} donde {id} es el ID del tópico a actualizar. Incluye el siguiente cuerpo JSON y el token JWT en el encabezado:

```
{
"titulo": "Título Actualizado",
"mensaje": "Descripción actualizada del tópico",
"autor": "nombre_del_autor",
"curso": "nombre_del_curso"
}
```

#### Eliminar un Tópico
Para eliminar un tópico, envía una solicitud DELETE a /topicos/{id} donde {id} es el ID del tópico. Asegúrate de incluir el token JWT en el encabezado de autorización.

## Autenticación
Para acceder a los endpoints protegidos, primero debes obtener un token JWT a través del endpoint de inicio de sesión (POST /login). Luego, incluye el token en el encabezado de autorización en cada solicitud:

```
Authorization: Bearer <tu_token_jwt>
```
