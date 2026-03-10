package com.alura.forohub.dto;

import com.alura.forohub.model.Response;
import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) utilizado para enviar información de una respuesta
 * al cliente en la API.
 *
 * Este DTO:
 * - Expone únicamente los datos necesarios.
 * - Evita exponer directamente la entidad Response.
 * - Facilita el desacoplamiento entre la capa de dominio y la API.
 *
 * Se define como "record" porque:
 * - Es inmutable.
 * - Genera automáticamente constructor, getters, equals, hashCode y toString.
 */
public record RespResponseDTO(

        /**
         * Identificador único de la respuesta.
         */
        Long id,

        /**
         * Contenido del mensaje de la respuesta.
         */
        String message,

        /**
         * Fecha y hora en la que se creó la respuesta.
         */
        LocalDateTime creationDate,

        /**
         * Nombre del usuario que realizó la respuesta.
         */
        String user

) {

    /**
     * Constructor adicional que permite mapear directamente
     * una entidad Response a un DTO de respuesta.
     *
     * Esto facilita la conversión en el Service o Controller:
     *
     * Ejemplo:
     * return new RespResponseDTO(response);
     *
     * @param response Entidad Response proveniente de la base de datos.
     */
    public RespResponseDTO(Response response) {
        this(
                response.getId(),
                response.getMessage(),
                response.getCreationDate(),
                response.getUser() != null ? response.getUser().getName() : "Usuario desconocido"
        );
    }
}
