<div align="center">
#   Plataforma Cultural de Nariño — Backend 

**Plataforma web de difusión y preservación cultural del departamento de Nariño**

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-Atlas-47A248?style=for-the-badge&logo=mongodb&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-Auth-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![Render](https://img.shields.io/badge/Deploy-Render-46E3B7?style=for-the-badge&logo=render&logoColor=white)

*Proyecto Final — Diseño de Software · 4to Semestre · Ingeniería de Software*

</div>
---

##  Descripción

Es una API REST desarrollada en **Java + Spring Boot** que expone los recursos culturales del departamento de Nariño, Colombia. Permite a visitantes explorar artículos culturales y eventos, y a administradores gestionar el contenido de la plataforma.

La API implementa autenticación con **JWT**, autorización por roles, paginación y búsqueda por texto y categoría.
 
---

## Arquitectura

El proyecto sigue una **arquitectura en capas estricta**:

```
Controller  →  Service (Interface)  →  ServiceImpl  →  Repository  →  Model
```

- La conversión entre entidades y DTOs se realiza directamente dentro de cada `ServiceImpl`, sin el uso de mappers externos.
- Los repositorios usan `MongoRepository` con queries personalizadas mediante `@Query`.
- La herencia de `Usuario` (`Administrador` y `Visitante`) se gestiona con discriminador `@JsonTypeInfo` en una sola colección MongoDB.
---

## Stack tecnológico

| Capa               | Tecnología                           |
|--------------------|--------------------------------------|
| Lenguaje           | Java 21                              |
| Framework          | Spring Boot 3.5.12                   |
| Base de datos      | MongoDB Atlas                        |
| Autenticación      | JWT — jjwt 0.12.6                    |
| Seguridad          | Spring Security                      |
| Validación         | Jakarta Validation (Bean Validation) |
| Despliegue         | Render (Web Service)                 |
 
---

## Estructura del proyecto

```
src/main/java/com/culturana/
├── CulturanaApplication.java
│
├── config/
│   └── SecurityConfig.java               # CORS, reglas de acceso, BCrypt
│
├── controller/
│   ├── AutenticacionController.java      # /api/auth, /api/usuarios, /api/perfil
│   ├── ArticuloController.java           # /api/articulos
│   ├── ComentarioController.java         # /api/comentarios
│   └── EventoController.java             # /api/eventos
│
├── dto/
│   
│   ├── RegistroRequest.java
│   ├── LoginRequest.java
│   ├── CambiarRolRequest.java
│   ├── ArticuloRequest.java
│   ├── ComentarioRequest.java
│   └── EventoRequest.java
│   
│   ├── AuthResponse.java
│   ├── MensajeResponse.java
│   ├── PerfilResponse.java
│   ├── UsuarioResumen.java
│   ├── ArticuloResponse.java
│   ├── ArticuloResumen.java
│   ├── ComentarioResponse.java
│   └── EventoResponse.java
│
├── exception/
│   ├── GlobalExceptionHandler.java       # Manejo global de errores
│   ├── BusinessException.java
│   ├── ResourceNotFoundException.java
│   └── UnauthorizedException.java
│
├── model/
│   ├── Usuario.java                      # Clase base (colección: usuarios)
│   ├── Administrador.java                # Extiende Usuario
│   ├── Visitante.java                    # Extiende Usuario
│   ├── ArticuloCultural.java             # Colección: articulos
│   ├── EventoCultural.java               # Colección: eventos
│   ├── Comentario.java                   # Colección: comentarios
│   ├── Notificacion.java                 # Colección: notificaciones
│   ├── Rol.java                          # Enum: ADMINISTRADOR, VISITANTE
│   └── CategoriaEnum.java                # Enum: GASTRONOMIA, DANZAS, MUSICA, TURISMO
│
├── repository/
│   ├── UsuarioRepository.java
│   ├── ArticuloRepository.java
│   ├── ComentarioRepository.java
│   └── EventoRepository.java
│
├── security/
│   ├── JwtService.java                   # Generación y validación de tokens
│   └── JwtAuthenticationFilter.java      # Filtro HTTP por petición
│
└── service/
    ├── AutenticacionService.java         # Interface
    ├── ArticuloService.java              # Interface
    ├── ComentarioService.java            # Interface
    ├── EventoService.java                # Interface
    └── impl/
        ├── AutenticacionServiceImpl.java
        ├── ArticuloServiceImpl.java
        ├── ComentarioServiceImpl.java
        └── EventoServiceImpl.java
```
 
---

## Ejecución local

### Requisitos previos

- Java 17+
- Maven 3.8+
- Cuenta en [MongoDB Atlas](https://www.mongodb.com/atlas) (gratuita)
### 1. Clonar o descomprimir el proyecto

```bash
cd culturana-backend
```

### 3. Compilar y ejecutar

```bash
mvn clean package -DskipTests
mvn spring-boot:run
```

La API queda disponible en: `http://localhost:8080`

## Endpoints de la API

### Autenticación (público)

| Método | Ruta                    | Descripción |
|--------|-------------------------|-------------|
| `POST` | `/api/v1/auth/registro` | Registrar nuevo usuario — retorna `PerfilResponse` |
| `POST` | `/api/v1/auth/login`    | Iniciar sesión — retorna `AuthResponse` con JWT |

**Ejemplo de registro:**
```json
POST /api/auth/registro
{
  "nombre": "María García",
  "correo": "maria@example.com",
  "contrasena": "123456"
}
```

**Ejemplo de login:**
```json
POST /api/auth/login
{
  "correo": "maria@example.com",
  "contrasena": "123456"
}
```
 
---

###  Usuarios (requiere rol ADMINISTRADOR)

| Método | Ruta                            | Descripción |
|--------|---------------------------------|-------------|
| `GET` | `/api/v1/usuarios`              | Listar todos los usuarios |
| `GET` | `/api/v1/perfil/{userId}`       | Ver perfil de un usuario |
| `DELETE` | `/api/v1/usuarios/{userId}`     | Eliminar usuario |
| `PUT` | `/api/v1/usuarios/{userId}/rol` | Cambiar rol del usuario |
 
---

### Artículos culturales

| Método | Ruta                     | Acceso | Descripción |
|--------|--------------------------|--------|-------------|
| `GET` | `/api/v1/articulos`      | Público | Listar con filtros y paginación |
| `GET` | `/api/v1/articulos/{id}` | Público | Ver detalle completo |
| `POST` | `/api/v1/articulos`      | ADMIN | Crear nuevo artículo |
| `PUT` | `/api/v1/articulos/{id}` | ADMIN | Editar artículo existente |
| `DELETE` | `/api/v1/articulos/{id}` | ADMIN | Eliminar artículo |

**Parámetros de búsqueda y paginación:**
```
GET /api/v1/articulos?categoria=GASTRONOMIA&q=carnaval&page=0&size=10
```

| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `categoria` | `CategoriaEnum` | Filtro por categoría (opcional) |
| `q` | `String` | Búsqueda en título y descripción (opcional) |
| `page` | `int` | Número de página (default: 0) |
| `size` | `int` | Tamaño de página (default: 10) |

**Categorías disponibles:** `GASTRONOMIA` · `DANZAS` · `MUSICA` · `TURISMO`
 
---

### Comentarios

| Método | Ruta                                        | Acceso | Descripción |
|--------|---------------------------------------------|--------|-------------|
| `GET` | `/api/v1/comentarios/articulo/{articuloId}` | Público | Listar comentarios de un artículo |
| `POST` | `/api/v1/comentarios`                       | Autenticado | Agregar comentario |
| `DELETE` | `/api/v1/comentarios/{comentarioId}`        | Autenticado | Eliminar (propio o ADMIN) |
 
---

### Eventos culturales

| Método | Ruta                   | Acceso | Descripción |
|--------|------------------------|--------|-------------|
| `GET` | `/api/v1/eventos`      | Público | Listar eventos futuros con paginación |
| `GET` | `/api/v1/eventos/{id}` | Público | Ver detalle del evento |
| `POST` | `/api/v1/eventos`      | ADMIN | Crear evento |
| `DELETE` | `/api/v1/eventos/{id}` | ADMIN | Eliminar evento |
 
---

## Autenticación con JWT

Después del login, incluye el token en el encabezado de cada petición protegida:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

El token incluye el `id`, `nombre` y `rol` del usuario. Expira en **24 horas** (configurable con `jwt.expiration`).
 
---

## Roles del sistema

| Rol | Permisos |
|-----|----------|
| `VISITANTE` | Ver artículos, eventos y comentarios. Agregar y eliminar sus propios comentarios. |
| `ADMINISTRADOR` | Todo lo anterior + crear/editar/eliminar artículos y eventos, gestionar usuarios y eliminar cualquier comentario. |
 
---

## Colecciones MongoDB

| Colección | Clase Java | Notas |
|-----------|------------|-------|
| `usuarios` | `Usuario` | Usa `_class` para distinguir `Administrador` y `Visitante` |
| `articulos` | `ArticuloCultural` | Indexado por categoría y texto |
| `eventos` | `EventoCultural` | Query de eventos futuros por fecha |
| `comentarios` | `Comentario` | Referencia a `articuloId` y `autorId` |
| `notificaciones` | `Notificacion` | Referencia a `destinatarioId` |
 


##  Manejo de errores

Todos los errores retornan una respuesta uniforme:

```json
{
  "mensaje": "Descripción del error"
}
```

| Código HTTP | Situación |
|-------------|-----------|
| `400` | Datos de entrada inválidos o error de negocio |
| `401` | Credenciales incorrectas |
| `403` | Sin permisos para realizar la acción |
| `404` | Recurso no encontrado |
| `500` | Error interno del servidor |
 
