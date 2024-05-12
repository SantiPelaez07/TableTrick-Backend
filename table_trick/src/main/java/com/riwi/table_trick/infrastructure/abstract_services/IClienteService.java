package com.riwi.table_trick.infrastructure.abstract_services;

import com.riwi.table_trick.api.dto.request.ClienteRequest;
import com.riwi.table_trick.api.dto.response.ClienteResponse;

import java.util.List;

public interface IClienteService extends CrudService<ClienteRequest, ClienteResponse, String>{
    public ClienteResponse getById(String id);
    public List<ClienteResponse> getByName(String name);
    public final String FIELD_BY_SORT = "nombre";
}
