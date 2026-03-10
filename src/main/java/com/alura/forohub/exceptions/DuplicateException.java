package com.alura.forohub.exceptions;

/**
 * Excepción personalizada utilizada cuando se intenta
 * registrar o crear un recurso que ya existe en el sistema.
 *
 * Ejemplo: email duplicado, curso duplicado, tópico repetido, etc.
 */
public class DuplicateException extends RuntimeException {

    /**
     * Constructor que recibe el mensaje del error.
     *
     * @param message descripción del conflicto de duplicidad
     */
    public DuplicateException(String message) {
        super(message);
    }

    /**
     * Constructor que permite encapsular otra excepción.
     *
     * @param message descripción del error
     * @param cause excepción original
     */
    public DuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
