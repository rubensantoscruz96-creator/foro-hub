package com.alura.forohub.dto;

// Importa la anotación para validar que el campo no sea nulo,
// no esté vacío y no contenga solo espacios en blanco.
import jakarta.validation.constraints.NotBlank;

/**
 * DTO (Data Transfer Object) utilizado para recibir los datos
 * necesarios para crear o actualizar un curso.
 *
 * Se define como "record" porque:
 * - Es inmutable.
 * - Reduce código repetitivo (no necesita getters, constructor, etc.).
 * - Es ideal para transportar datos entre capas (Controller -> Service).
 */
public record CourseRequestDTO(

        /**
         * Nombre del curso.
         *
         * @NotBlank asegura que:
         * - No sea null
         * - No sea una cadena vacía ("")
         * - No contenga solo espacios en blanco
         *
         * Si la validación falla, se mostrará el mensaje personalizado.
         */
        @NotBlank(message = "El nombre no debe estar vacío")
        String name,

        /**
         * Categoría del curso.
         *
         * También es obligatoria y no puede estar vacía.
         */
        @NotBlank(message = "La categoría no debe estar vacía")
        String category

) {
    /*
     * Los records generan automáticamente:
     * - Constructor público
     * - Métodos de acceso (name() y category())
     * - equals()
     * - hashCode()
     * - toString()
     *
     * No es necesario agregar código adicional
     * a menos que se requiera lógica personalizada.
     */
}
