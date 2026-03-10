package com.alura.forohub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO (Data Transfer Object) utilizado para recibir datos
 * al crear una respuesta (reply) en un topic.
 *
 * Se define como "record" porque:
 * - Es inmutable.
 * - Reduce código repetitivo (constructor, getters, toString, equals, hashCode).
 * - Es ideal para transportar datos desde el Controller hacia el Service.
 */
public record RespRequestDTO(

        /**
         * Contenido del mensaje de la respuesta.
         * 
         * @NotBlank asegura que:
         * - No sea null
         * - No sea una cadena vacía
         * - No contenga solo espacios en blanco
         *
         * Mensaje de validación personalizado si la regla falla.
         */
        @NotBlank(message = "El mensaje no debe estar vacío")
        String message,

        /**
         * ID del topic al que pertenece la respuesta.
         *
         * @NotNull asegura que el valor no sea null.
         */
        @NotNull(message = "El topicId es obligatorio")
        Long topicId,

        /**
         * ID del usuario que realiza la respuesta.
         *
         * @NotNull asegura que el valor no sea null.
         */
        @NotNull(message = "El userId es obligatorio")
        Long userId

) {
    /*
     * Los records generan automáticamente:
     * - Constructor público
     * - Métodos de acceso: message(), topicId(), userId()
     * - equals(), hashCode(), toString()
     *
     * Este DTO no necesita lógica adicional.
     */
}
