package com.alura.forohub.dto;

// Importa la entidad Course desde el paquete model.
// Se utiliza para convertir la entidad en un DTO de respuesta.
import com.alura.forohub.model.Course;

/**
 * DTO (Data Transfer Object) utilizado para enviar
 * información de un curso como respuesta al cliente.
 *
 * Este DTO:
 * - Expone solo los datos necesarios.
 * - Evita exponer directamente la entidad (buena práctica).
 * - Mejora el desacoplamiento entre la capa de dominio y la API.
 *
 * Se define como "record" porque:
 * - Es inmutable.
 * - Reduce código boilerplate.
 * - Es ideal para respuestas REST.
 */
public record CourseResponseDTO(

        /**
         * Identificador único del curso.
         */
        Long id,

        /**
         * Nombre del curso.
         */
        String name,

        /**
         * Categoría a la que pertenece el curso.
         */
        String category

) {

    /**
     * Constructor adicional que permite convertir
     * directamente una entidad Course en un CourseResponseDTO.
     *
     * Esto facilita el mapeo en el Service o Controller:
     *
     * Ejemplo:
     * return new CourseResponseDTO(course);
     *
     * @param course Entidad Course proveniente de la base de datos.
     */
    public CourseResponseDTO(Course course) {
        this(
                course.getId(),
                course.getName(),
                course.getCategory()
        );
    }
}
