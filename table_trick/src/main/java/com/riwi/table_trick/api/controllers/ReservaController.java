package com.riwi.table_trick.api.controllers;

import java.util.Objects;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.riwi.table_trick.api.dto.request.ReservaRequest;
import com.riwi.table_trick.api.dto.response.ReservaResponse;
import com.riwi.table_trick.infrastructure.abstract_services.IReservaService;
import com.riwi.table_trick.util.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/reserva")
@AllArgsConstructor
@Tag(name = "Reserva")
public class ReservaController {

    private final IReservaService reservaService;

    @PostMapping
    @Operation(
            summary = "Crear una reserva",
            description = "El usuario proporciona datos para la creación de una reserva",
            tags = {"Creación"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Se deben proporcionar datos requeridos para la creación de la reserva",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReservaRequest.class)
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Creación satisfactoria",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReservaResponse.class)
                    )
            )
    )
    public ResponseEntity<ReservaResponse> crearReserva(@Validated @RequestBody ReservaRequest reserva){
        return ResponseEntity.ok(this.reservaService.create(reserva));
    }

    @GetMapping
    @Operation(
            summary = "Obtener todas las reservas",
            description = "Obtiene una lista paginada de todas las reservas, con opciones de tamaño de página y tipo de ordenación",
            tags = {"Consulta"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de reservas obtenida satisfactoriamente",
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
    public ResponseEntity<Page<ReservaResponse>> getReservas(
            @RequestHeader(required = false) SortType sortType,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "8") int size) {

        if (Objects.isNull(sortType)) sortType = SortType.NONE;

        return ResponseEntity.ok(this.reservaService.getAll(page - 1, size, sortType));
    }

    @GetMapping(path = "/{id}")
    @Operation(
            summary = "Obtener reserva por ID",
            description = "Obtiene los detalles de una reserva específica mediante su ID",
            tags = {"Consulta"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Reserva encontrada",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ReservaResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Reserva no encontrada",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<ReservaResponse> consultaId(@Validated @PathVariable String id){
        return ResponseEntity.ok(this.reservaService.getById(id));
    }

    @GetMapping("/consulta")
    @Operation(
            summary = "Buscar reservas por nombre de cliente",
            description = "Obtiene una lista paginada de reservas cuyo nombre del cliente coincide con el criterio de búsqueda",
            tags = {"Consulta"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Reservas encontradas",
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
    public ResponseEntity<Page<ReservaResponse>> obtenerReservasPorNombreCliente(
            @RequestParam(value = "nombreCliente") String nombreCliente,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "8") int size) {
        Page<ReservaResponse> reservas = this.reservaService.obtenerReservasPorNombreCliente(nombreCliente, page - 1, size);
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    @Operation(
            summary = "Actualizar reserva",
            description = "Actualiza los datos de una reserva específica mediante su ID",
            tags = {"Actualización"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Se deben proporcionar los datos requeridos para la actualización de la reserva",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReservaRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Actualización satisfactoria",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ReservaResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Reserva no encontrada",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos inválidos para la actualización",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<ReservaResponse> actualizar(@RequestBody ReservaRequest reservaRequest, @PathVariable String id){
        return ResponseEntity.ok(this.reservaService.update(reservaRequest, id));
    }

    @DeleteMapping(path = "/{id}")
    @Operation(
            summary = "Eliminar reserva",
            description = "Elimina una reserva específica mediante su ID",
            tags = {"Eliminación"},
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Eliminación satisfactoria",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Reserva no encontrada",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<ReservaResponse> eliminar(@PathVariable String id){
        this.reservaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
