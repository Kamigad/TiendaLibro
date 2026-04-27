# 📖 Tienda Libro

API REST desarrollada con **Spring Boot** que simula el backend de una tienda de libros. Permite gestionar el catálogo completo de libros con operaciones de listado, búsqueda, creación, modificación y eliminación.

---

## ✨ Funcionalidades

- 📋 **Listar** todos los libros disponibles
- 🔍 **Buscar** un libro por su ID
- ➕ **Agregar** nuevos libros al catálogo
- ✏️ **Modificar** la información de un libro existente
- 🗑️ **Eliminar** libros del catálogo

Cada libro contiene la siguiente información: ID, nombre, autor, precio y existencias.

---

## 🚀 Tecnologías utilizadas

| Tecnología | Versión |
|---|---|
| Java | 26 |
| Spring Boot | 4.0.5 |
| Spring Data JPA | - |
| MySQL | - |
| Lombok | - |
| Maven | - |

---

## 📋 Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- [Java JDK 26+](https://www.oracle.com/java/technologies/downloads/)
- [Maven](https://maven.apache.org/download.cgi)
- [MySQL](https://www.mysql.com/downloads/)

---

## ⚙️ Configuración

### 1. Clona el repositorio

```bash
git clone https://github.com/Kamigad/TiendaLibro.git
cd TiendaLibro
```

### 2. Crea la base de datos

```sql
CREATE DATABASE tienda_libros;
```

### 3. Configura las credenciales

Edita el archivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tienda_libros
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## ▶️ Ejecución

### Con Maven Wrapper

```bash
# Linux / Mac
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

### Con Maven instalado

```bash
mvn spring-boot:run
```

La aplicación se ejecutará en `http://localhost:8080`.

---

## 🔗 Endpoints de la API

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/libros` | Listar todos los libros |
| `GET` | `/libros/{id}` | Buscar libro por ID |
| `POST` | `/libros` | Agregar un nuevo libro |
| `PUT` | `/libros/{id}` | Modificar un libro existente |
| `DELETE` | `/libros/{id}` | Eliminar un libro |

### Ejemplo de body para POST / PUT

```json
{
  "nombre": "Cien Años de Soledad",
  "autor": "Gabriel García Márquez",
  "precio": 35000.00,
  "existencias": 50
}
```

### Ejemplo de respuesta

```json
{
  "id": 1,
  "nombre": "Cien Años de Soledad",
  "autor": "Gabriel García Márquez",
  "precio": 35000.00,
  "existencias": 50
}
```

---

## 🗂️ Estructura del proyecto

```
TiendaLibro/
├── src/
│   ├── main/
│   │   ├── java/gm/tienda_libros/
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   └── service/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

---

## 👤 Autor

**Kamigad**  
[GitHub](https://github.com/Kamigad)

---

## 📄 Licencia

Este proyecto es de uso libre con fines educativos.
