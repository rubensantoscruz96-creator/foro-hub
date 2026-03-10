package com.alura.forohub.controller;

// Importación de los DTOs
import com.alura.forohub.dto.TopicRequestDTO;
import com.alura.forohub.dto.TopicResponseDTO;
import com.alura.forohub.dto.TopicToUpdate;

// Servicio donde se encuentra la lógica de negocio
import com.alura.forohub.service.TopicService;

// Validaciones automáticas
import jakarta.validation.Valid;

// Clases para paginación
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

// Construcción de respuestas HTTP
import org.springframework.http.ResponseEntity;

// Anotaciones REST
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Controlador REST encargado de gestionar los Topics.
 * 
 * Se encarga únicamente de:
 *  - Recibir peticiones HTTP
 *  - Validar datos
 *  - Delegar la lógica al TopicService
 *  - Construir la respuesta HTTP adecuada
 */
@RestController // Indica que es un controlador REST
@RequestMapping("/topics") // Ruta base para todos los endpoints
public class TopicController {

    // Inyección del servicio
    private final TopicService topicService;

    /**
     * Constructor para inyección de dependencias.
     */
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    /**
     * Obtener un Topic por su ID.
     *
     * Método: GET
     * URL: /topics/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(topicService.get(id));
    }

    /**
     * Obtener todos los Topics con paginación.
     *
     * Método: GET
     * URL: /topics
     *
     * Parámetros opcionales:
     * ?page=0&size=10&sort=creationDate,asc
     */
    @GetMapping
    public ResponseEntity<Page<TopicResponseDTO>> getAll(
            @PageableDefault(
                    size = 10,
                    sort = "creationDate",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        return ResponseEntity.ok(topicService.getAll(pageable));
    }

    /**
     * Crear un nuevo Topic.
     *
     * Método: POST
     * URL: /topics
     *
     * Devuelve 201 CREATED con header Location.
     */
    @PostMapping
    public ResponseEntity<TopicResponseDTO> create(
            @RequestBody @Valid TopicRequestDTO request,
            UriComponentsBuilder uriBuilder) {

        TopicResponseDTO response = topicService.create(request);

        // Construcción de la URI del nuevo recurso
        URI location = uriBuilder
                .path("/topics/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    /**
     * Actualizar un Topic existente.
     *
     * Método: PUT
     * URL: /topics/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid TopicToUpdate request) {

        return ResponseEntity.ok(topicService.update(id, request));
    }

    /**
     * Eliminar un Topic por su ID.
     *
     * Método: DELETE
     * URL: /topics/{id}
     *
     * Devuelve 204 NO CONTENT si se elimina correctamente.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        topicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
}
