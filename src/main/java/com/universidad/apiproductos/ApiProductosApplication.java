package com.universidad.apiproductos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la API REST de Productos.
 * Spring Boot autoconfigura el servidor Tomcat embebido,
 * Jackson para JSON y el escaneo de componentes.
 */
@SpringBootApplication
public class ApiProductosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiProductosApplication.class, args);
    }
}
