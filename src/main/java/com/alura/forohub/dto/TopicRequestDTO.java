package com.alura.forohub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO (Data Transfer Object) utilizado para recibir los datos
 * necesarios para crear un nuevo Topic en la API.
 *
 * Este DTO:
 * - Es inmutable gracias al uso de "record".
 * - Reduce código repetitivo (no necesita getters, constructor, equals, hashCode, toString).
 * - Permite validar los datos de entrada con anotaciones de Jakarta Validation.
 */
public record TopicRequestDTO(

        /**
         * Título del topic.
         *
         * @NotBlank asegura que:
         * - No sea null
         * - No sea una cadena vacía
         * - No contenga solo espacios en blanco
         *
         * Mensaje personalizado si la validación falla.
         */
        @NotBlank(message = "El título no debe estar vacío")
        String title,

        /**
         * Mensaje principal del topic.
         *
         * También obligatorio y no puede estar vacío.
         */
        @NotBlank(message = "El mensaje no debe estar vacío")
        String message,

        /**
         * ID del curso al que pertenece el topic.
         *
         * @NotNull asegura que el valor no sea null.
         */
        @NotNull(message = "Se debe especificar un curso")
        Long courseId,

        /**
         * ID del usuario que crea el topic.
         *
         * @NotNull asegura que el valor no sea null.
         */
        @NotNull(message = "Se debe especificar un usuario")
        Long userId

) {
    /*
     * Los records generan automáticamente:
     * - Constructor público
     * - Métodos de acceso: title(), message(), courseId(), userId()
     * - equals(), hashCode(), toString()
     *
     * Este DTO no necesita lógica adicional.
     */
}
