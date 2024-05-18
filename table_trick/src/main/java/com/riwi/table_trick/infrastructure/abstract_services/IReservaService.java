package com.riwi.table_trick.infrastructure.abstract_services;


import com.riwi.table_trick.api.dto.request.ReservaRequest;
import com.riwi.table_trick.api.dto.response.ReservaResponse;
import org.springframework.data.domain.Page;

public interface IReservaService extends CrudService <ReservaRequest, ReservaResponse, String>{
    Page<ReservaResponse> obtenerReservasPorNombreCliente(String nombreCliente, int page, int size);
    public ReservaResponse getById(String id);
    public final String FIELD_BY_SORT = "nombre";
}
