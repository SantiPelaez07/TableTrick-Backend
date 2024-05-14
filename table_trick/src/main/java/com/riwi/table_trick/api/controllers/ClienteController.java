package com.riwi.table_trick.api.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity create(@Validated @RequestBody ClienteRequest request){
        return ResponseEntity.ok(this.clienteService.create(request));
    }

    @GetMapping
    public ResponseEntity<Page<ClienteResponse>> getAll(@RequestParam(defaultValue = "1") int page, 
            @RequestParam(defaultValue = "5") int size, @RequestHeader(required = false) SortType sortType){

                if(Objects.isNull(sortType)) sortType = SortType.NONE;

                return ResponseEntity.ok(this.clienteService.getAll(page - 1, size, sortType));

            }

    @PutMapping
    public ResponseEntity update(@RequestBody ClienteRequest request, @PathVariable String id){
        return ResponseEntity.ok(this.clienteService.update(request, id));
    }
    
    @DeleteMapping
    public ResponseEntity delete(@PathVariable String id){
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getById(@PathVariable String id){
        return ResponseEntity.ok(this.clienteService.getById(id));
    }
}
