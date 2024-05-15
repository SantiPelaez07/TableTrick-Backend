package com.riwi.table_trick.infrastructure.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.table_trick.api.dto.request.RestauranteRequest;
import com.riwi.table_trick.api.dto.response.RestauranteResponse;
import com.riwi.table_trick.domain.entities.Restaurante;
import com.riwi.table_trick.domain.repositories.RestauranteRepository;
import com.riwi.table_trick.infrastructure.abstract_services.IRestauranteService;
import com.riwi.table_trick.util.enums.SortType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RestauranteService implements IRestauranteService{

    @Autowired
    private final RestauranteRepository repository;

    @Override
    public RestauranteResponse getById(String id) {
        return entityToResponse(this.find(id));
    }

    @Override
    public RestauranteResponse create(RestauranteRequest request) {
        Restaurante restaurante = requestToEntity(request);
        return entityToResponse(this.repository.save(restaurante));
    }

    @Override
    public void delete(String id) {
        this.repository.deleteById(id);
    }

    @Override
    public Page<RestauranteResponse> getAll(int page, int size, SortType sort) {
        if (page < 0) page = 0;
        PageRequest pagination = null;
        switch (sort) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }
        return this.repository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public RestauranteResponse update(RestauranteRequest request, String id) {
        Restaurante restaurante = this.find(id);
        restaurante = this.requestToEntity(request);
        restaurante.setId(id);
        return entityToResponse(this.repository.save(restaurante));
    }
    
    public Restaurante requestToEntity(RestauranteRequest request){
        return Restaurante.builder()
        .nombrePropietario(request.getNombreAutor())
        .apellidoPropietario(request.getApellidoAutor())
        .nit(request.getNit())
        .email(request.getEmail())
        .contraseña(request.getContraseña())
        .fecha_registro(request.getFechaRegistro())
        .ubicacion(request.getUbicacion())
        .capacidad_maxima(request.getCapacidadMaxima())
        .especialidad(request.getEspecialidad())
        .tipoCuenta(request.getTipoCuenta()).build();
    
    }

    public RestauranteResponse entityToResponse(Restaurante restaurante){
        return RestauranteResponse.builder()
        .nit(restaurante.getNit())
        .email(restaurante.getEmail())
        .contraseña(restaurante.getContraseña())
        .ubicacion(restaurante.getUbicacion())
        .especialidad(restaurante.getEspecialidad())
        .tipoCuenta(restaurante.getTipoCuenta()).build();
    }

    public Restaurante find(String id){
        return this.repository.findById(id).orElseThrow();
    }

}
