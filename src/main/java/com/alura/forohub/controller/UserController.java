package com.alura.forohub.controller;

import com.alura.forohub.dto.UserRequestDTO;
import com.alura.forohub.dto.UserResponseDTO;
import com.alura.forohub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/users") // Ruta base para todos los endpoints de usuarios
public class UserController {

    private final UserService userService;

    // Inyección de dependencias por constructor (buena práctica)
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint para registrar un nuevo usuario.
     *
     * @param user DTO que contiene los datos del usuario a registrar.
     *             Se valida automáticamente gracias a @Valid.
     * @return ResponseEntity con:
     *         - Código HTTP 201 (Created)
     *         - El usuario creado en el body
     *         - Header Location con la URI del nuevo recurso
     */
    @PostMapping
    public ResponseEntity<UserResponseDTO> register(
            @RequestBody @Valid UserRequestDTO user) {

        // Llama al servicio para registrar el usuario
        UserResponseDTO response = userService.register(user);

        // Construye la URI del nuevo recurso creado (/users/{id})
        URI location = URI.create("/users/" + response.id());

        // Devuelve 201 Created con el recurso creado
        return ResponseEntity.created(location).body(response);
    }
}
