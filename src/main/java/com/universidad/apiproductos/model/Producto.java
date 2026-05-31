package com.universidad.apiproductos.model;

/**
 * Modelo de dominio que representa un Producto.
 * Jackson lo serializa automáticamente a/desde JSON
 * gracias a los getters y setters públicos.
 *
 * Ejemplo de JSON generado:
 * {
 *   "id": 1,
 *   "nombre": "Laptop",
 *   "descripcion": "Laptop 15 pulgadas 16GB RAM",
 *   "precio": 1299.99
 * }
 */
public class Producto {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;

    // Constructor vacío requerido por Jackson para deserializar JSON del body
    public Producto() {}

    public Producto(Long id, String nombre, String descripcion, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    // --- Getters y Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    @Override
    public String toString() {
        return "Producto{id=" + id + ", nombre='" + nombre + "', precio=" + precio + "}";
    }
}
