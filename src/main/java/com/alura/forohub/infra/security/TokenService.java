package com.alura.forohub.infra.security;

import com.alura.forohub.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * Servicio encargado de generar y validar tokens JWT.
 */
@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    @Value("${api.security.issuer:forohub}")
    private String issuer;

    /**
     * Genera un token JWT para un usuario autenticado.
     */
    public String generateToken(User user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al generar el token JWT", exception);
        }
    }

    /**
     * Retorna el subject (email) del token si es válido.
     */
    public String getSubject(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido o expirado", exception);
        }
    }

    /**
     * Genera fecha de expiración del token (2 horas).
     */
    private Instant generateExpirationDate() {
        return Instant.now().plusSeconds(7200); // 2 horas
    }
}
