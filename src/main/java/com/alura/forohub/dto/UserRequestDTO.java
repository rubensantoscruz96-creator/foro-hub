package com.alura.forohub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO utilizado para la creación de un nuevo usuario.
 *
 * Contiene las validaciones necesarias para garantizar
 * integridad y seguridad en los datos recibidos.
 */
public record UserRequestDTO(

        /**
         * Nombre del usuario.
         * No puede estar vacío y debe tener entre 2 y 100 caracteres.
         */
        @NotBlank(message = "El nombre no debe estar vacío")
        @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
        String name,

        /**
         * Email del usuario.
         * Debe tener formato válido y no puede estar vacío.
         */
        @NotBlank(message = "El email no debe estar vacío")
        @Email(message = "Debe ingresar un email válido")
        String email,

        /**
         * Contraseña del usuario.
         * No puede estar vacía y debe tener al menos 8 caracteres.
         * (La contraseña debe encriptarse antes de guardarse en BD).
         */
        @NotBlank(message = "El password no debe estar vacío")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        String password

) {
}
