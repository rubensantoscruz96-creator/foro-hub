package com.alura.forohub.service;

import com.alura.forohub.dto.CourseRequestDTO;
import com.alura.forohub.dto.CourseResponseDTO;
import com.alura.forohub.exceptions.DuplicateException;
import com.alura.forohub.model.Course;
import com.alura.forohub.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio encargado de la lógica de negocio
 * relacionada con la entidad Course.
 */
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Registra un nuevo curso en el sistema.
     * Valida que no exista un curso con el mismo nombre.
     */
    @Transactional
    public CourseResponseDTO register(CourseRequestDTO data) {

        // Validación de duplicado
        if (courseRepository.existsByName(data.name())) {
            throw new DuplicateException("El curso ya existe");
        }

        // Creación de la entidad
        Course course = new Course(
                null,
                data.name(),
                data.category()
        );

        // Guardado en base de datos
        courseRepository.save(course);

        return new CourseResponseDTO(course);
    }

    /**
     * Obtiene todos los cursos con paginación.
     */
    @Transactional(readOnly = true)
    public Page<CourseResponseDTO> getAll(Pageable pagination) {
        return courseRepository
                .findAll(pagination)
                .map(CourseResponseDTO::new);
    }
}
