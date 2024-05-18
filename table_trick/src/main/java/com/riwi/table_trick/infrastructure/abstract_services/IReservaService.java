package com.riwi.table_trick.infrastructure.abstract_services;


import com.riwi.table_trick.api.dto.request.ReservaRequest;
import com.riwi.table_trick.api.dto.response.ReservaResponse;

public interface IReservaService extends CrudService <ReservaRequest, ReservaResponse, String>{
    public ReservaResponse getById(String id);
    public final String FIELD_BY_SORT = "nombre";
}
