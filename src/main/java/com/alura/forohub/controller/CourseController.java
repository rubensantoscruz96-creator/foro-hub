package com.alura.forohub.controller;

// Importaciones de los DTOs (objetos que viajan en las peticiones y respuestas)
import com.alura.forohub.dto.CourseRequestDTO;
import com.alura.forohub.dto.CourseResponseDTO;

// Importación del servicio que contiene la lógica de negocio
import com.alura.forohub.service.CourseService;

// Validación automática con anotaciones como @NotNull, @NotBlank, etc.
import jakarta.validation.Valid;

// Clases para paginación
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

// Construcción de respuestas HTTP
import org.springframework.http.ResponseEntity;

// Anotaciones REST
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Controlador REST para gestionar los cursos.
 * Se encarga de recibir las peticiones HTTP y delegar la lógica al Service.
 */
@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/courses") // Ruta base para todos los endpoints de este controlador
public class CourseController {

    // Inyección de dependencia del servicio
    private final CourseService courseService;

    /**
     * Constructor que inyecta el servicio.
     * Spring lo usa para inyección de dependencias.
     */
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Endpoint para crear un nuevo curso.
     * Método: POST
     * URL: /courses
     */
    @PostMapping
    public ResponseEntity<CourseResponseDTO> create(
            // @RequestBody: convierte el JSON recibido en un objeto Java
            // @Valid: valida automáticamente el DTO según sus anotaciones
            @RequestBody @Valid CourseRequestDTO request,
            
            // Permite construir la URI del recurso creado
            UriComponentsBuilder uriBuilder) {

        // Se delega la creación al servicio
        CourseResponseDTO response = courseService.create(request);

        // Se construye la URL del nuevo recurso creado
        URI location = uriBuilder
                .path("/courses/{id}") // Ruta del recurso individual
                .buildAndExpand(response.id()) // Se reemplaza {id} por el id real
                .toUri();

        // Devuelve 201 CREATED con el recurso creado y el header Location
        return ResponseEntity.created(location).body(response);
    }

    /**
     * Endpoint para obtener todos los cursos paginados.
     * Método: GET
     * URL: /courses
     */
    @GetMapping
    public ResponseEntity<Page<CourseResponseDTO>> getAll(
            
            // @PageableDefault define valores por defecto de paginación
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {

        // Devuelve la lista paginada de cursos
        return ResponseEntity.ok(courseService.getAll(pageable));
    }

    /**
     * Endpoint para obtener un curso por su ID.
     * Método: GET
     * URL: /courses/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getById(
            
            // Captura el id desde la URL
            @PathVariable Long id) {

        // Busca el curso en el servicio y lo devuelve
        return ResponseEntity.ok(courseService.getById(id));
    }
}
