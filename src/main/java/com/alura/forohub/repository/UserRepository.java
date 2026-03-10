package com.alura.forohub.repository;

import com.alura.forohub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio para la entidad User.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Busca un usuario por su email.
     * Se utiliza en el proceso de autenticación.
     */
    Optional<User> findByEmail(String email);

    /**
     * Verifica si existe un usuario con el email indicado.
     */
    boolean existsByEmail(String email);
}
