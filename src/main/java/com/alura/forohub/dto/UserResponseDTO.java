package com.alura.forohub.dto;

import com.alura.forohub.model.User;
import java.util.Objects;

/**
 * DTO utilizado para enviar información del usuario al cliente.
 *
 * ⚠ No incluye la contraseña por razones de seguridad.
 * Solo expone datos públicos.
 */
public record UserResponseDTO(

        // Identificador único del usuario
        Long id,

        // Nombre del usuario
        String name,

        // Email del usuario
        String email

) {

    /**
     * Constructor que convierte una entidad User en UserResponseDTO.
     *
     * @param user Entidad User proveniente de la base de datos
     */
    public UserResponseDTO(User user) {
        this(
                Objects.requireNonNull(user, "El usuario no puede ser null").getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
