package com.alura.forohub.infra.security;

import com.alura.forohub.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de cargar los datos del usuario
 * durante el proceso de autenticación.
 *
 * Spring Security utiliza esta clase automáticamente.
 */
@Service
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Carga un usuario por su email (username).
     *
     * @param username email del usuario
     * @return UserDetails
     * @throws UsernameNotFoundException si el usuario no existe
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return userRepository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Usuario no encontrado con el email: " + username
                        )
                );
    }
}
