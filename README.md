# api-productos — API REST CRUD con Spring Boot

API REST completa para la gestión de productos, desarrollada como parte del **Post-Contenido 2 de la Unidad 7 (Spring Boot Básico)** del curso de Programación Web — Universidad Francisco de Paula Santander, 2026.

---

## Descripción del Proyecto

La API expone endpoints CRUD sobre una colección de productos en memoria, usando:

- **Spring Boot** con `@RestController` (devuelve JSON, no HTML)
- **ResponseEntity** para controlar los códigos HTTP de cada respuesta
- **Jackson** (incluido en `spring-boot-starter-web`) para serialización/deserialización JSON automática
- **`@RestControllerAdvice`** para manejo global de errores con respuestas JSON estructuradas
- **Persistencia en memoria** (HashMap) sin base de datos externa

---

## Estructura del Proyecto

```
api-productos/
├── src/
│   ├── main/
│   │   ├── java/com/universidad/apiproductos/
│   │   │   ├── ApiProductosApplication.java       ← Punto de entrada
│   │   │   ├── model/
│   │   │   │   └── Producto.java                  ← POJO serializable a JSON
│   │   │   ├── service/
│   │   │   │   └── ProductoService.java           ← CRUD en memoria (HashMap)
│   │   │   └── controller/
│   │   │       ├── ProductoApiController.java     ← Endpoints REST
│   │   │       └── GlobalExceptionHandler.java    ← Errores JSON globales
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/universidad/apiproductos/
│           └── ApiProductosApplicationTests.java
├── pom.xml
└── README.md
```

---

## Prerrequisitos

| Requisito | Versión |
|-----------|---------|
| Java JDK  | 17+     |
| Maven     | 3.8+    |
| Postman   | Cualquier versión Desktop |

---

## Instrucciones de Ejecución

```bash
# Clonar el repositorio
git clone https://github.com/OctopusZeroKanagawa/Jimenez-post2-u7
cd apellido-post2-u7

# Ejecutar
./mvnw spring-boot:run
# o si tienes Maven instalado:
mvn spring-boot:run
```

La consola debe mostrar:
```
Started ApiProductosApplication in X.XXX seconds
```

---

## Endpoints de la API

| Método | URL | Código éxito | Código error | Descripción |
|--------|-----|:---:|:---:|-------------|
| GET | `/api/productos` | 200 OK | — | Lista todos los productos |
| GET | `/api/productos/{id}` | 200 OK | 404 | Busca producto por ID |
| POST | `/api/productos` | 201 Created | 400 | Crea un nuevo producto |
| PUT | `/api/productos/{id}` | 200 OK | 404 | Actualiza un producto existente |
| DELETE | `/api/productos/{id}` | 204 No Content | 404 | Elimina un producto |

---

## Ejemplos de Uso con curl

### Listar todos
```bash
curl -X GET http://localhost:8080/api/productos
```

### Buscar por ID
```bash
curl -X GET http://localhost:8080/api/productos/1
```

### Crear producto
```bash
curl -X POST http://localhost:8080/api/productos \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Monitor","descripcion":"Monitor 27 pulgadas 4K","precio":499.99}'
```

### Actualizar producto
```bash
curl -X PUT http://localhost:8080/api/productos/3 \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Monitor Curvo","descripcion":"Monitor curvo 32 pulgadas QHD","precio":699.99}'
```

### Eliminar producto
```bash
curl -X DELETE http://localhost:8080/api/productos/3
```

### Buscar ID inexistente (404)
```bash
curl -X GET http://localhost:8080/api/productos/999
# → {"error":"Producto no encontrado: 999","timestamp":"..."}
```

---

## Datos de Ejemplo (precargados)

| ID | Nombre  | Descripción                   | Precio    |
|----|---------|-------------------------------|-----------|
| 1  | Laptop  | Laptop 15 pulgadas 16GB RAM   | $1,299.99 |
| 2  | Mouse   | Mouse inalámbrico ergonómico  | $29.99    |
| 3  | Teclado | Teclado mecánico TKL          | $89.99    |

> Los datos se reinician al reiniciar la aplicación.

---

## Manejo de Errores

La API retorna errores en formato JSON estructurado (no HTML):

```json
{
  "error": "Producto no encontrado: 999",
  "timestamp": "2026-05-30T10:15:30.123"
}
```

Esto es posible gracias a `GlobalExceptionHandler` anotado con `@RestControllerAdvice`.

---

## Capturas de Pantalla Postman

> Agregar capturas aquí tras probar cada endpoint:

| Operación | Código esperado |
|-----------|----------------|
| GET /api/productos | 200 OK |
| POST /api/productos | 201 Created |
| PUT /api/productos/1 | 200 OK |
| DELETE /api/productos/1 | 204 No Content |
| GET /api/productos/999 | 404 Not Found |

---

## Autor

**Andres Felipe Jiménez Ramírez** — Ingeniería de Sistemas  
Universidad Francisco de Paula Santander · 2026
