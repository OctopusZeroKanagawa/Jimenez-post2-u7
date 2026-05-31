package com.universidad.apiproductos.controller;

import com.universidad.apiproductos.model.Producto;
import com.universidad.apiproductos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de productos.
 *
 * Diferencias clave respecto al @Controller del Post-Contenido 1:
 *
 *  @RestController = @Controller + @ResponseBody
 *    → cada método devuelve datos (JSON), no nombres de vistas.
 *
 *  ResponseEntity<T> permite controlar:
 *    → el cuerpo de la respuesta (objeto serializado a JSON)
 *    → el código de estado HTTP (200, 201, 204, 404…)
 *    → las cabeceras HTTP si fuera necesario
 *
 * Tabla de endpoints implementados:
 *  GET    /api/productos       → 200 OK   + lista JSON
 *  GET    /api/productos/{id}  → 200 OK   + objeto JSON  |  404 Not Found
 *  POST   /api/productos       → 201 Created + objeto creado
 *  PUT    /api/productos/{id}  → 200 OK   + objeto actualizado  |  404
 *  DELETE /api/productos/{id}  → 204 No Content  |  404
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoApiController {

    @Autowired
    private ProductoService servicio;

    // ----------------------------------------------------------------
    // GET /api/productos
    // Retorna la lista completa. Siempre 200 OK (lista vacía es válida).
    // ----------------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(servicio.obtenerTodos());
    }

    // ----------------------------------------------------------------
    // GET /api/productos/{id}
    // 200 OK si existe, 404 Not Found si no.
    // .map(ResponseEntity::ok) aplica ok() si el Optional tiene valor.
    // .orElse(notFound) se ejecuta si el Optional está vacío.
    // ----------------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscar(@PathVariable Long id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ----------------------------------------------------------------
    // POST /api/productos
    // @RequestBody deserializa el JSON del body al objeto Producto.
    // 201 Created es el código semánticamente correcto para creación.
    // ----------------------------------------------------------------
    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        Producto nuevo = servicio.guardar(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // ----------------------------------------------------------------
    // PUT /api/productos/{id}
    // Reemplaza completamente el producto si existe (200 OK).
    // Si el ID no existe en el mapa → 404 Not Found.
    // Se fuerza el ID del path sobre el body para evitar inconsistencias.
    // ----------------------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(
            @PathVariable Long id,
            @RequestBody Producto producto) {

        return servicio.buscarPorId(id)
                .map(existente -> {
                    producto.setId(id);  // garantiza que el ID no se cambie
                    return ResponseEntity.ok(servicio.guardar(producto));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ----------------------------------------------------------------
    // DELETE /api/productos/{id}
    // 204 No Content: operación exitosa sin cuerpo de respuesta.
    // 404 si el ID no existe (buena práctica para APIs REST).
    // ----------------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (servicio.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
