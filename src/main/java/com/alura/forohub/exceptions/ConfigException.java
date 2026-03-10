package com.alura.forohub.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase global para el manejo centralizado de excepciones.
 * Permite devolver respuestas HTTP estructuradas y coherentes.
 */
@RestControllerAdvice
public class ConfigException {

    /**
     * Maneja errores cuando una entidad no es encontrada.
     * Devuelve 404 NOT FOUND.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> manageError404(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        "Recurso no encontrado",
                        e.getMessage()
                ));
    }

    /**
     * Maneja errores de duplicidad personalizados.
     * Devuelve 400 BAD REQUEST.
     */
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ErrorResponse> manageDuplicateError(DuplicateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "Registro duplicado",
                        e.getMessage()
                ));
    }

    /**
     * Maneja errores de validación (@Valid).
     * Devuelve lista de errores de campos.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DataError>> manageError400(MethodArgumentNotValidException e) {

        List<DataError> errors = e.getFieldErrors()
                .stream()
                .map(DataError::new)
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Maneja cualquier excepción no controlada.
     * Evita exponer detalles internos.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralError(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Error interno del servidor",
                        "Ocurrió un error inesperado"
                ));
    }

    /**
     * Record para errores de validación de campos.
     */
    public record DataError(String field, String message) {
        public DataError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    /**
     * Record estándar para respuestas de error.
     */
    public record ErrorResponse(
            int status,
            String error,
            String message,
            LocalDateTime timestamp
    ) {
        public ErrorResponse(int status, String error, String message) {
            this(status, error, message, LocalDateTime.now());
        }
    }
}
