package com.alura.forohub.infra.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO utilizado para el proceso de autenticación.
 * Contiene las credenciales necesarias para el login.
 */
public record UserAuthentication(

        /**
         * Email del usuario.
         * Debe tener formato válido y no puede estar vacío.
         */
        @NotBlank(message = "El email no debe quedar vacío")
        @Email(message = "Debe ingresar un email válido")
        String email,

        /**
         * Contraseña del usuario.
         * No puede estar vacía.
         */
        @NotBlank(message = "La contraseña no debe quedar vacía")
        String password

) {
}
