package com.alura.forohub.service;

import com.alura.forohub.dto.TopicRequestDTO;
import com.alura.forohub.dto.TopicResponseDTO;
import com.alura.forohub.dto.TopicToUpdate;
import com.alura.forohub.exceptions.DuplicateException;
import com.alura.forohub.model.Course;
import com.alura.forohub.model.Topic;
import com.alura.forohub.model.User;
import com.alura.forohub.repository.CourseRepository;
import com.alura.forohub.repository.TopicRepository;
import com.alura.forohub.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public TopicService(TopicRepository topicRepository,
                        CourseRepository courseRepository,
                        UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    // ========================
    // Registrar tópico
    // ========================
    @Transactional
    public TopicResponseDTO register(TopicRequestDTO data) {

        if (topicRepository.existsByTitleAndMessage(data.title(), data.message())) {
            throw new DuplicateException("Ya existe un tópico con el mismo título y mensaje");
        }

        Course course = courseRepository.findById(data.courseId())
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));

        User user = userRepository.findById(data.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        Topic topic = new Topic(
                null,
                data.title(),
                data.message(),
                LocalDateTime.now(),
                true,
                course,
                user,
                new java.util.ArrayList<>()
        );

        topicRepository.save(topic);

        return new TopicResponseDTO(topic);
    }

    // ========================
    // Listar todos
    // ========================
    @Transactional(readOnly = true)
    public Page<TopicResponseDTO> getAll(Pageable pagination) {
        return topicRepository.findAll(pagination)
                .map(TopicResponseDTO::new);
    }

    // ========================
    // Obtener por ID
    // ========================
    @Transactional(readOnly = true)
    public TopicResponseDTO get(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        return new TopicResponseDTO(topic);
    }

    // ========================
    // Eliminar
    // ========================
    @Transactional
    public void delete(Long id) {
        if (!topicRepository.existsById(id)) {
            throw new EntityNotFoundException("Tópico no encontrado");
        }
        topicRepository.deleteById(id);
    }

    // ========================
    // Actualizar
    // ========================
    @Transactional
    public TopicResponseDTO update(Long id, TopicToUpdate topicData) {

        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        topic.updateTopic(topicData);

        return new TopicResponseDTO(topic);
    }
}


