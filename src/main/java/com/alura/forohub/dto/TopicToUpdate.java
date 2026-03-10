package com.alura.forohub.dto;

import jakarta.validation.constraints.Size;

/**
 * DTO utilizado para actualizar parcialmente un Topic.
 *
 * Todos los campos son opcionales. Solo se actualizarán
 * aquellos que vengan con valor distinto de null.
 */
public record TopicToUpdate(

        /**
         * Nuevo título del tópico.
         * Debe tener entre 5 y 100 caracteres si se envía.
         */
        @Size(min = 5, max = 100, message = "El título debe tener entre 5 y 100 caracteres")
        String title,

        /**
         * Nuevo mensaje del tópico.
         * Debe tener al menos 10 caracteres si se envía.
         */
        @Size(min = 10, message = "El mensaje debe tener al menos 10 caracteres")
        String message,

        /**
         * Estado del tópico (ej: abierto/cerrado).
         */
        Boolean status

) {
}
