package com.alura.forohub.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa un tópico dentro del foro.
 */
@Entity
@Table(name = "topics")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 2000)
    private String message;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(
            mappedBy = "topic",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Response> responses = new ArrayList<>();

    /**
     * Constructor de negocio.
     */
    public Topic(String title, String message, Course course, User user) {
        this.title = title;
        this.message = message;
        this.course = course;
        this.user = user;
        this.creationDate = LocalDateTime.now();
        this.status = true; // abierto por defecto
    }

    /**
     * Método controlado para actualizar datos.
     */
    public void updateTopic(String title, String message, Boolean status) {

        if (title != null && !title.isBlank()) {
            this.title = title;
        }

        if (message != null && !message.isBlank()) {
            this.message = message;
        }

        if (status != null) {
            this.status = status;
        }
    }

    /**
     * Método para agregar respuesta manteniendo coherencia bidireccional.
     */
    public void addResponse(Response response) {
        responses.add(response);
    }
}
