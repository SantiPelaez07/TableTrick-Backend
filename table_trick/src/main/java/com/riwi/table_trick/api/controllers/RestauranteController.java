package com.riwi.table_trick.api.controllers;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.riwi.table_trick.api.dto.request.RestauranteRequest;
import com.riwi.table_trick.api.dto.response.RestauranteResponse;
import com.riwi.table_trick.infrastructure.abstract_services.IRestauranteService;
import com.riwi.table_trick.util.enums.SortType;

import lombok.AllArgsConstructor;

@Controller
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

       if (Objects.isNull(sortType)) sortType = sortType.NONE;

        return ResponseEntity.ok(this.restauranteService.getAll(page, size, sortType));
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
