package com.alura.forohub.repository;

import com.alura.forohub.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio para la entidad Course.
 * 
 * Extiende JpaRepository para heredar operaciones CRUD básicas,
 * paginación y ordenamiento automáticamente.
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

    /**
     * Verifica si ya existe un curso con el nombre indicado.
     * 
     * Se usa para evitar duplicados en la base de datos.
     *
     * @param name nombre del curso
     * @return true si existe, false si no
     */
    boolean existsByName(String name);

    /**
     * Busca un curso por su nombre.
     * 
     * Devuelve Optional para evitar NullPointerException
     * y manejar correctamente el caso cuando no existe.
     *
     * @param name nombre del curso
     * @return Optional<Course>
     */
    Optional<Course> findByName(String name);
}
