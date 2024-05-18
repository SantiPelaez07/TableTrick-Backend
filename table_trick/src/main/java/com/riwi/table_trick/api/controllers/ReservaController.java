package com.riwi.table_trick.api.controllers;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.riwi.table_trick.api.dto.request.ReservaRequest;
import com.riwi.table_trick.api.dto.response.ReservaResponse;
import com.riwi.table_trick.infrastructure.abstract_services.IReservaService;
import com.riwi.table_trick.util.enums.SortType;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(path = "/reserva")
@AllArgsConstructor
public class ReservaController {

    private final IReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaResponse> crearReserva(@Validated @RequestBody ReservaRequest reserva){
        return ResponseEntity.ok(this.reservaService.create(reserva));
    }

    @GetMapping
    public ResponseEntity<Page<ReservaResponse>> getReservas(
        @RequestHeader(required = false) SortType sortType,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "8") int size) {

       if (Objects.isNull(sortType)) sortType = SortType.NONE;

        return ResponseEntity.ok(this.reservaService.getAll(page - 1, size, sortType));
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<ReservaResponse> consultaId(@Validated @PathVariable String id){
        return ResponseEntity.ok(this.reservaService.getById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ReservaResponse> actualizar(@RequestBody ReservaRequest reservaRequest, @PathVariable String id){
        return ResponseEntity.ok(this.reservaService.update(reservaRequest, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ReservaResponse> eliminar(@PathVariable String id){
        this.reservaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
