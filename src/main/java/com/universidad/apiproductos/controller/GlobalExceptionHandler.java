package com.universidad.apiproductos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Manejador global de excepciones para la API REST.
 *
 * @RestControllerAdvice intercepta cualquier excepción no capturada
 * en los controladores y devuelve una respuesta JSON estructurada,
 * en lugar del HTML de error por defecto de Spring Boot (Whitelabel).
 *
 * Ejemplo de respuesta al llamar GET /api/productos/999:
 * HTTP 404 Not Found
 * {
 *   "error": "Producto no encontrado: 999",
 *   "timestamp": "2026-05-30T10:15:30"
 * }
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Captura RuntimeException (incluye la que lanza el controlador
     * cuando no encuentra un producto por ID).
     * Retorna 404 Not Found con un body JSON descriptivo.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntime(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "error", ex.getMessage(),
                        "timestamp", LocalDateTime.now().toString()
                ));
    }

    /**
     * Captura cualquier otra excepción no prevista.
     * Retorna 500 Internal Server Error con un mensaje genérico.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneral(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "error", "Error interno del servidor",
                        "detalle", ex.getMessage(),
                        "timestamp", LocalDateTime.now().toString()
                ));
    }
}
