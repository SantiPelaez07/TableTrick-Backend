package com.riwi.table_trick.api.controllers;

import java.util.Objects;

import com.riwi.table_trick.api.dto.request.LoginRequest;
import com.riwi.table_trick.api.dto.response.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.riwi.table_trick.api.dto.request.ClienteRequest;
import com.riwi.table_trick.api.dto.response.ClienteResponse;
import com.riwi.table_trick.infrastructure.abstract_services.IClienteService;
import com.riwi.table_trick.util.enums.SortType;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/cliente")
@AllArgsConstructor
@Tag(name = "Cliente")
public class ClienteController {

    @Autowired
    private final IClienteService clienteService;

    @PostMapping
    @Operation(
            summary = "Creación de cliente",
            description = "El usuario proporciona datos para la creación de cliente",
            tags = {"Creación"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Se debe proporcionar datos requeridos para la creación del cliente",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClienteRequest.class)
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Creación satisfactoria",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClienteResponse.class)
                    )
            )
    )
    public ResponseEntity<ClienteResponse> create(@Validated @RequestBody ClienteRequest request){
        return ResponseEntity.ok(this.clienteService.create(request));
    }

    @GetMapping
    @Operation(
            summary = "Obtener todos los clientes",
            description = "Obtiene una lista paginada de todos los clientes, con opciones de tamaño de página y tipo de ordenación",
            tags = {"Consulta"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de clientes obtenida satisfactoriamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Page.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud inválida",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<Page<ClienteResponse>> getAll(@RequestParam(defaultValue = "1") int page,
                                                        @RequestParam(defaultValue = "8") int size, @RequestHeader(required = false) SortType sortType){

        if(Objects.isNull(sortType)) sortType = SortType.NONE;

        return ResponseEntity.ok(this.clienteService.getAll(page - 1, size, sortType));
    }

    @GetMapping(path = "/id/{id}")
    @Operation(
            summary = "Obtener cliente por ID",
            description = "Obtiene los detalles de un cliente específico mediante su ID",
            tags = {"Consulta"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cliente encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ClienteResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cliente no encontrado",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<ClienteResponse> getById(@PathVariable String id){
        return ResponseEntity.ok(this.clienteService.getById(id));
    }

    @GetMapping(path = "/consulta")
    @Operation(
            summary = "Buscar clientes por nombre",
            description = "Obtiene una lista paginada de clientes cuyo nombre coincide con el criterio de búsqueda",
            tags = {"Consulta"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Clientes encontrados",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Page.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud inválida",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<Page<ClienteResponse>> getClientesPorConsulta(
            @RequestParam("nombre") String nombre,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "8") int size) {

        // Obtener la página de clientes por nombre
        Page<ClienteResponse> clientePage = clienteService.getByName(nombre, page - 1, size);

        return ResponseEntity.ok(clientePage);
    }

    @PutMapping(path = "/id/{id}")
    @Operation(
            summary = "Actualizar cliente",
            description = "Actualiza los datos de un cliente específico mediante su ID",
            tags = {"Actualización"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Se deben proporcionar los datos requeridos para la actualización del cliente",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClienteRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Actualización satisfactoria",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ClienteResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cliente no encontrado",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos inválidos para la actualización",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<ClienteResponse> update(@RequestBody ClienteRequest request, @PathVariable String id){
        return ResponseEntity.ok(this.clienteService.update(request, id));
    }

    @DeleteMapping(path = "/id/{id}")
    @Operation(
            summary = "Eliminar cliente",
            description = "Elimina un cliente específico mediante su ID",
            tags = {"Eliminación"},
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Eliminación satisfactoria",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cliente no encontrado",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<Void> delete(@PathVariable String id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
