package com.alura.forohub.service;

import com.alura.forohub.dto.RespRequestDTO;
import com.alura.forohub.dto.RespResponseDTO;
import com.alura.forohub.model.Response;
import com.alura.forohub.model.Topic;
import com.alura.forohub.model.User;
import com.alura.forohub.repository.ResponseRepository;
import com.alura.forohub.repository.TopicRepository;
import com.alura.forohub.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Servicio encargado de la lógica de negocio
 * relacionada con las respuestas de un tópico.
 */
@Service
public class ResponseService {

    private final ResponseRepository responseRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    public ResponseService(ResponseRepository responseRepository,
                           TopicRepository topicRepository,
                           UserRepository userRepository) {
        this.responseRepository = responseRepository;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    /**
     * Registra una nueva respuesta en un tópico.
     */
    @Transactional
    public RespResponseDTO register(RespRequestDTO data) {

        // Buscar tópico
        Topic topic = topicRepository.findById(data.topicId())
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        // Buscar usuario
        User user = userRepository.findById(data.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        // Validación opcional: evitar responder tópicos cerrados
        if (Boolean.FALSE.equals(topic.getStatus())) {
            throw new IllegalStateException("No se puede responder a un tópico cerrado");
        }

        // Crear respuesta
        Response response = new Response(
                null,
                data.message(),
                topic,
                LocalDateTime.now(),
                user
        );

        responseRepository.save(response);

        return new RespResponseDTO(response);
    }
}
