package com.universidad.apiproductos.service;

import com.universidad.apiproductos.model.Producto;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Capa de servicio que simula un repositorio en memoria.
 * Usa LinkedHashMap para mantener el orden de inserción.
 *
 * @Service registra esta clase como bean singleton de Spring:
 * una sola instancia es compartida por todos los controladores.
 */
@Service
public class ProductoService {

    // Almacén principal: id → producto
    private final Map<Long, Producto> productos = new LinkedHashMap<>();

    // Contador autoincremental de IDs
    private Long contadorId = 1L;

    /**
     * Carga tres productos de ejemplo al iniciar la aplicación.
     */
    public ProductoService() {
        guardar(new Producto(null, "Laptop",  "Laptop 15 pulgadas 16GB RAM",  1299.99));
        guardar(new Producto(null, "Mouse",   "Mouse inalámbrico ergonómico",    29.99));
        guardar(new Producto(null, "Teclado", "Teclado mecánico TKL",            89.99));
    }

    /** Retorna todos los productos como lista. */
    public List<Producto> obtenerTodos() {
        return new ArrayList<>(productos.values());
    }

    /**
     * Busca un producto por ID.
     * Retorna Optional.empty() si no existe → el controlador responde 404.
     */
    public Optional<Producto> buscarPorId(Long id) {
        return Optional.ofNullable(productos.get(id));
    }

    /**
     * INSERT si id == null, UPDATE si ya tiene id.
     * Retorna el producto con el ID asignado.
     */
    public Producto guardar(Producto producto) {
        if (producto.getId() == null) {
            producto.setId(contadorId++);
        }
        productos.put(producto.getId(), producto);
        return producto;
    }

    /**
     * Elimina el producto con el ID indicado.
     * Operación idempotente: no lanza excepción si no existe.
     */
    public void eliminar(Long id) {
        productos.remove(id);
    }
}
