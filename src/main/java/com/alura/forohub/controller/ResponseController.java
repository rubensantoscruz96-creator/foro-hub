package com.alura.forohub.controller;

// Importación de los DTOs (objetos que transportan datos entre cliente y servidor)
import com.alura.forohub.dto.RespRequestDTO;
import com.alura.forohub.dto.RespResponseDTO;

// Servicio donde vive la lógica de negocio
import com.alura.forohub.service.ResponseService;

// Permite validar automáticamente el cuerpo de la petición
import jakarta.validation.Valid;

// Construcción de respuestas HTTP
import org.springframework.http.ResponseEntity;

// Anotaciones REST
import org.springframework.web.bind.annotation.*;

// Para construir la URI del recurso creado
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Controlador REST encargado de gestionar las respuestas (responses)
 * dentro del sistema.
 *
 * Recibe las peticiones HTTP y delega la lógica al ResponseService.
 */
@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/responses") // Ruta base para todos los endpoints de este controlador
public class ResponseController {

    // Inyección de dependencia del servicio
    private final ResponseService responseService;

    /**
     * Constructor para inyección de dependencias.
     * Spring inyecta automáticamente el ResponseService.
     */
    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    /**
     * Endpoint para crear una nueva respuesta.
     *
     * Método: POST
     * URL: /responses
     *
     * @param request Datos enviados desde el cliente en formato JSON.
     * @param uriBuilder Permite construir la URI del recurso creado.
     * @return 201 Created con el recurso creado y header Location.
     */
    @PostMapping
    public ResponseEntity<RespResponseDTO> create(
            
            // Convierte el JSON en un objeto Java
            // @Valid activa las validaciones definidas en el DTO
            @RequestBody @Valid RespRequestDTO request,
            
            UriComponentsBuilder uriBuilder) {

        // Se delega la creación al servicio
        RespResponseDTO response = responseService.create(request);

        // Se construye la URL del nuevo recurso creado
        URI location = uriBuilder
                .path("/responses/{id}") // Ruta del recurso individual
                .buildAndExpand(response.id()) // Se reemplaza {id} por el id real
                .toUri();

        // Devuelve 201 CREATED con el recurso en el body
        // y la ubicación del nuevo recurso en el header Location
        return ResponseEntity.created(location).body(response);
    }
}
