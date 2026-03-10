package com.alura.forohub.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entidad que representa una respuesta a un tópico.
 */
@Entity
@Table(name = "responses")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Contenido de la respuesta.
     */
    @Column(nullable = false, length = 1000)
    private String message;

    /**
     * Tópico al que pertenece la respuesta.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    /**
     * Fecha de creación de la respuesta.
     */
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    /**
     * Usuario que creó la respuesta.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Constructor de negocio.
     */
    public Response(String message, Topic topic, User user) {
        this.message = message;
        this.topic = topic;
        this.user = user;
        this.creationDate = LocalDateTime.now();
    }

    /**
     * Permite actualizar el mensaje de forma controlada.
     */
    public void updateMessage(String message) {
        if (message != null && !message.isBlank()) {
            this.message = message;
        }
    }
}
