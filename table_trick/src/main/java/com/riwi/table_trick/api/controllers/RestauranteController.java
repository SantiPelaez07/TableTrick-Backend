package com.riwi.table_trick.api.controllers;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.riwi.table_trick.api.dto.request.RestauranteRequest;
import com.riwi.table_trick.api.dto.response.RestauranteResponse;
import com.riwi.table_trick.infrastructure.abstract_services.IRestauranteService;
import com.riwi.table_trick.util.enums.SortType;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/restaurante")
@AllArgsConstructor
public class RestauranteController {

    private final IRestauranteService restauranteService;

    @PostMapping
    public ResponseEntity<RestauranteResponse> create(@RequestBody RestauranteRequest request){
        return ResponseEntity.ok(this.restauranteService.create(request));
    }

    @GetMapping
    public ResponseEntity<Page<RestauranteResponse>> getAll(
            @RequestHeader(required = false) SortType sortType,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "8") int size) {

       if (Objects.isNull(sortType)) sortType = SortType.NONE;

        return ResponseEntity.ok(this.restauranteService.getAll(page - 1, size, sortType));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RestauranteResponse> getById(@PathVariable String id){
        return ResponseEntity.ok(this.restauranteService.getById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<RestauranteResponse> update(@RequestBody RestauranteRequest request, @PathVariable String id){
        return ResponseEntity.ok(this.restauranteService.update(request, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RestauranteResponse> delete(@PathVariable String id){
        this.restauranteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
