package com.riwi.table_trick.api.controllers;

import java.util.Objects;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.riwi.table_trick.api.dto.request.RestauranteRequest;
import com.riwi.table_trick.api.dto.response.RestauranteResponse;
import com.riwi.table_trick.infrastructure.abstract_services.IRestauranteService;
import com.riwi.table_trick.util.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/restaurante")
@AllArgsConstructor
@Tag(name = "Restaurante")
public class RestauranteController {

    private final IRestauranteService restauranteService;

    @PostMapping
    @Operation(
            summary = "Crear un restaurante",
            description = "El usuario proporciona datos para la creación de un restaurante",
            tags = {"Creación"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Se deben proporcionar datos requeridos para la creación del restaurante",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RestauranteRequest.class)
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Creación satisfactoria",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RestauranteResponse.class)
                    )
            )
    )
    public ResponseEntity<RestauranteResponse> create(@RequestBody RestauranteRequest request){
        return ResponseEntity.ok(this.restauranteService.create(request));
    }

    @GetMapping
    @Operation(
            summary = "Obtener todos los restaurantes",
            description = "Obtiene una lista paginada de todos los restaurantes, con opciones de tamaño de página y tipo de ordenación",
            tags = {"Consulta"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de restaurantes obtenida satisfactoriamente",
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
    public ResponseEntity<Page<RestauranteResponse>> getAll(
            @RequestHeader(required = false) SortType sortType,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "8") int size) {

        if (Objects.isNull(sortType)) sortType = SortType.NONE;

        return ResponseEntity.ok(this.restauranteService.getAll(page - 1, size, sortType));
    }

    @GetMapping(path = "/{id}")
    @Operation(
            summary = "Obtener restaurante por ID",
            description = "Obtiene los detalles de un restaurante específico mediante su ID",
            tags = {"Consulta"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Restaurante encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RestauranteResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Restaurante no encontrado",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<RestauranteResponse> getById(@PathVariable String id){
        return ResponseEntity.ok(this.restauranteService.getById(id));
    }

    @PutMapping(path = "/{id}")
    @Operation(
            summary = "Actualizar restaurante",
            description = "Actualiza los datos de un restaurante específico mediante su ID",
            tags = {"Actualización"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Se deben proporcionar los datos requeridos para la actualización del restaurante",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RestauranteRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Actualización satisfactoria",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RestauranteResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Restaurante no encontrado",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos inválidos para la actualización",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<RestauranteResponse> update(@RequestBody RestauranteRequest request, @PathVariable String id){
        return ResponseEntity.ok(this.restauranteService.update(request, id));
    }

    @DeleteMapping(path = "/{id}")
    @Operation(
            summary = "Eliminar restaurante",
            description = "Elimina un restaurante específico mediante su ID",
            tags = {"Eliminación"},
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Eliminación satisfactoria",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Restaurante no encontrado",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<RestauranteResponse> delete(@PathVariable String id){
        this.restauranteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
