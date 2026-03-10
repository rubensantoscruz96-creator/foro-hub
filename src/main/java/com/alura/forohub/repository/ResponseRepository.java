package com.alura.forohub.repository;

import com.alura.forohub.model.Response;
import com.alura.forohub.model.Topic;
import com.alura.forohub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio para la entidad Response.
 * 
 * Proporciona operaciones CRUD básicas gracias a JpaRepository
 * y métodos derivados para consultas personalizadas.
 */
public interface ResponseRepository extends JpaRepository<Response, Long> {

    /**
     * Obtiene todas las respuestas asociadas a un Topic específico.
     *
     * @param topic tópico al que pertenecen las respuestas
     * @return lista de respuestas
     */
    List<Response> findByTopic(Topic topic);

    /**
     * Obtiene todas las respuestas realizadas por un usuario específico.
     *
     * @param user usuario autor de las respuestas
     * @return lista de respuestas
     */
    List<Response> findByUser(User user);

    /**
     * Cuenta cuántas respuestas tiene un tópico.
     *
     * @param topic tópico a evaluar
     * @return número de respuestas
     */
    long countByTopic(Topic topic);
}
