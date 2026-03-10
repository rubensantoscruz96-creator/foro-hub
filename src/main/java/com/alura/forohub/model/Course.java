package com.alura.forohub.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa un curso dentro del sistema.
 */
@Entity
@Table(
        name = "courses",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        }
)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del curso.
     * No puede ser nulo ni repetido.
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * Categoría del curso.
     */
    @Column(nullable = false, length = 100)
    private String category;

    /**
     * Constructor de negocio (sin id).
     */
    public Course(String name, String category) {
        this.name = name;
        this.category = category;
    }

    /**
     * Método para actualizar datos del curso.
     */
    public void updateData(String name, String category) {
        if (name != null) {
            this.name = name;
        }
        if (category != null) {
            this.category = category;
        }
    }
}
