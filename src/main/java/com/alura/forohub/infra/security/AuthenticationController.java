package com.alura.forohub.infra.security;

import com.alura.forohub.model.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador encargado del proceso de autenticación.
 * Recibe credenciales y devuelve un JWT si son válidas.
 */
@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    /**
     * Autentica un usuario y genera un token JWT.
     *
     * @param user datos de autenticación (email y password)
     * @return JWT generado
     */
    @PostMapping
    public ResponseEntity<DataJWTToken> authenticateUser(
            @RequestBody @Valid UserAuthentication user) {

        // Token de autenticación con credenciales
        Authentication authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        user.email(),
                        user.password()
                );

        // Autenticación mediante AuthenticationManager
        Authentication authentication =
                authenticationManager.authenticate(authenticationToken);

        // Obtener usuario autenticado
        User authenticatedUser = (User) authentication.getPrincipal();

        // Generar JWT
        String jwtToken = tokenService.generateToken(authenticatedUser);

        return ResponseEntity.ok(new DataJWTToken(jwtToken));
    }

    /**
     * DTO de respuesta que contiene el token JWT.
     */
    public record DataJWTToken(String jwtToken) {
    }
}
