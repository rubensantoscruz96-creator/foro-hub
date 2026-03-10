package com.alura.forohub.dto;

import com.alura.forohub.model.Topic;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * DTO (Data Transfer Object) utilizado para devolver la información
 * de un Topic hacia el cliente (Response).
 *
 * Este record representa los datos que serán expuestos en la API,
 * evitando exponer directamente la entidad Topic.
 */
public record TopicResponseDTO(

        // Identificador único del tópico
        Long id,

        // Título del tópico
        String title,

        // Mensaje o contenido principal del tópico
        String message,

        // Fecha y hora de creación
        LocalDateTime creationDate,

        // Nombre del curso asociado
        String course,

        // Nombre del usuario que creó el tópico
        String user,

        // Lista de respuestas asociadas al tópico
        List<RespResponseDTO> responses

) {

    /**
     * Constructor que convierte una entidad Topic en un TopicResponseDTO.
     *
     * @param topic Entidad Topic proveniente de la base de datos
     */
    public TopicResponseDTO(Topic topic) {

        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),

                // Validación para evitar NullPointerException si el curso es null
                Objects.nonNull(topic.getCourse()) 
                        ? topic.getCourse().getName() 
                        : null,

                // Validación para evitar NullPointerException si el usuario es null
                Objects.nonNull(topic.getUser()) 
                        ? topic.getUser().getName() 
                        : null,

                // Conversión segura de la lista de respuestas
                topic.getResponses() != null
                        ? topic.getResponses()
                                .stream()
                                .map(RespResponseDTO::new)
                                .toList()
                        : List.of()
        );
    }
}
