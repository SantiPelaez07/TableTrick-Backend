package com.riwi.table_trick.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.table_trick.api.dto.request.ClienteRequest;
import com.riwi.table_trick.api.dto.response.ClienteResponse;
import com.riwi.table_trick.domain.entities.Cliente;
import com.riwi.table_trick.domain.repositories.ClienteRepository;
import com.riwi.table_trick.infrastructure.abstract_services.IClienteService;
import com.riwi.table_trick.util.enums.SortType;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ClienteService implements IClienteService {

    @Autowired
    private final ClienteRepository clienteRepository;

    @Override
    public ClienteResponse getById(String id) {
        return entityToResponse(this.find(id));
    }

    @Override
    public Page<ClienteResponse> getByName(String name, int page, int size) {
        // Crear objeto Pageable para la paginación
        Pageable pageable = PageRequest.of(page, size);
        // Buscar por nombre en el repositorio y obtener una página de resultados
        Page<Cliente> clientePage = clienteRepository.findByNombreStartingWithIgnoreCase(name, pageable);
        // Mapear la página de entidades a una página de respuestas
        Page<ClienteResponse> clienteResponsePage = clientePage.map(this::entityToResponse);
        return clienteResponsePage;
    }


    @Override
    public   ClienteResponse create(ClienteRequest request) {
        Cliente cliente = this.requestToEntity(request);
        return entityToResponse(this.clienteRepository.save(cliente));
    }

    @Override
    public void delete(String id) {
        this.clienteRepository.delete(this.find(id));
    }

    @Override
    public Page<ClienteResponse> getAll(int page, int size, SortType sort) {
        if(page < 0) page = 0;

        PageRequest pagination = null;
        switch (sort) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }
        return this.clienteRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public ClienteResponse update(ClienteRequest request, String id) {
        Cliente cliente = this.find(id);
        cliente = this.requestToEntity(request);
        cliente.setId(id);
        return this.entityToResponse(this.clienteRepository.save(cliente));
    }

    private Cliente requestToEntity(ClienteRequest request){
        return Cliente.builder()
        .nombre(request.getNombre())
        .apellido(request.getApellido())
        .email(request.getEmail())
        .telefono(request.getTelefono()).build();
    }

    private ClienteResponse entityToResponse(Cliente entity){
        return ClienteResponse.builder()
                .id(entity.getId())
        .nombre(entity.getNombre())
        .apellido(entity.getApellido())
        .email(entity.getEmail())
        .telefono(entity.getTelefono()).build();
    }

    private Cliente find(String id){
        return this.clienteRepository.findById(id).orElseThrow();
    }
}
