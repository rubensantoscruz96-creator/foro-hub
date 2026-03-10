package com.alura.forohub.repository;

import com.alura.forohub.model.Topic;
import com.alura.forohub.model.Course;
import com.alura.forohub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la entidad Topic.
 * 
 * Extiende JpaRepository para obtener CRUD,
 * paginación y ordenamiento automáticamente.
 */
public interface TopicRepository extends JpaRepository<Topic, Long> {

    /**
     * Verifica si ya existe un tópico con el mismo título y mensaje.
     * Se usa para evitar duplicados.
     */
    boolean existsByTitleAndMessage(String title, String message);

    /**
     * Obtiene todos los tópicos creados por un usuario.
     */
    List<Topic> findByUser(User user);

    /**
     * Obtiene todos los tópicos asociados a un curso.
     */
    List<Topic> findByCourse(Course course);

    /**
     * Busca un tópico por título.
     */
    Optional<Topic> findByTitle(String title);

    /**
     * Cuenta la cantidad de tópicos creados por un usuario.
     */
    long countByUser(User user);
}
