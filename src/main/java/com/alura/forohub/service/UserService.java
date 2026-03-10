package com.alura.forohub.service;

import com.alura.forohub.dto.UserRequestDTO;
import com.alura.forohub.dto.UserResponseDTO;
import com.alura.forohub.exceptions.DuplicateException;
import com.alura.forohub.model.User;
import com.alura.forohub.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponseDTO register(UserRequestDTO data) {

        // Normalizar email
        String email = data.email().toLowerCase().trim();

        // Validar duplicado
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateException("El email ya está registrado");
        }

        // Encriptar contraseña
        String passwordEncrypted = passwordEncoder.encode(data.password());

        // Crear usuario
        User user = new User(
                null,
                data.name(),
                email,
                passwordEncrypted
        );

        userRepository.save(user);

        return new UserResponseDTO(user);
    }
}
