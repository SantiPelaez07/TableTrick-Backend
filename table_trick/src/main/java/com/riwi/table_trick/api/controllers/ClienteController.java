package com.riwi.table_trick.api.controllers;

import java.util.Objects;

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
public class ClienteController {

    @Autowired
    private final IClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponse> create(@Validated @RequestBody ClienteRequest request){
        return ResponseEntity.ok(this.clienteService.create(request));
    }


    @GetMapping
    public ResponseEntity<Page<ClienteResponse>> getAll(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "8") int size, @RequestHeader(required = false) SortType sortType){

                if(Objects.isNull(sortType)) sortType = SortType.NONE;

                return ResponseEntity.ok(this.clienteService.getAll(page - 1, size, sortType));

            }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<ClienteResponse> getById(@PathVariable String id){
        return ResponseEntity.ok(this.clienteService.getById(id));
    }

    @GetMapping(path = "/consulta")
    public ResponseEntity<Page<ClienteResponse>> getClientesPorConsulta(
            @RequestParam("nombre") String nombre,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "8") int size) {

        // Obtener la página de clientes por nombre
        Page<ClienteResponse> clientePage = clienteService.getByName(nombre, page - 1, size);

        return ResponseEntity.ok(clientePage);
    }


    @PutMapping(path = "/id/{id}")
    public ResponseEntity<ClienteResponse> update(@RequestBody ClienteRequest request, @PathVariable String id){
        return ResponseEntity.ok(this.clienteService.update(request, id));
    }

    @DeleteMapping(path = "/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
