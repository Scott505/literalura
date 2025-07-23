# 📚 Literalura

## 🚀 Descripción

**Literalura** es una aplicación desarrollada en Java con Spring Boot que se conecta a la API pública de Gutendex para consultar, registrar y explorar libros de dominio público. 
Permite guardar los libros favoritos en una base de datos, filtrarlos por autor, año o idioma, y ver estadísticas básicas.

---

## 🚀 Funcionalidades

- 🔍 Buscar libros por título (vía Gutendex API)
- 💾 Registrar libros en base de datos
- 📚 Listar libros registrados en la BD
- ✍️ Listar autores únicos registrados en la BD
- 🧓 Filtrar autores vivos en un año específico
- 🌐 Filtrar libros por idioma

---

## 🛠 Tecnologías utilizadas

- **Java 24**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL**
- **Gutendex API** [https://gutendex.com/](https://gutendex.com/)
- **Maven**

---

  ## 🧰 Requisitos

- Java instalado (versión 17 o superior)
- PostgreSQL
- Maven

---

## ⚙️ Configuración

1. **Cloná el proyecto:**

```bash
   git clone https://github.com/Scott505/literalura.git
```

2. **Asegurate de tener configurado PostgreSQL y creá una base de datos**
   Por ejemplo:

  ```sql
   CREATE DATABASE literalura;
  ```

3. **Modificá el archivo `application.properties` con los datos de conexión:**

```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/literalura  
   spring.datasource.username=tu_usuario  
   spring.datasource.password=tu_contraseña
```

4. **Ejecutá la aplicación:**
   
---

## 🧪 Uso

Una vez iniciado, el programa funciona en modo consola con un menú interactivo donde podrás:

- Buscar libros por título
- Guardarlos en la base de datos
- Listar todos los libros registrados
- Consultar autores y filtros por idioma o año
- Ver estadísticas sobre los libros registrados

---

## 💡 Sobre la API Gutendex

Gutendex es una API REST gratuita que permite acceder a una gran cantidad de libros del Proyecto Gutenberg.  
Documentación: [https://gutendex.com/](https://gutendex.com/)

---

## 🙌 Créditos

Desarrollado por [Scott505](https://github.com/Scott505) como parte del programa **Oracle Next Education (ONE)** - Back-End Java - **Alura Latam**.
