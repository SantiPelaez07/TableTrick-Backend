package com.riwi.table_trick.infrastructure.abstract_services;

import com.riwi.table_trick.api.dto.request.ReservaRequest;
import com.riwi.table_trick.api.dto.response.ClienteResponse;
import com.riwi.table_trick.api.dto.response.ReservaResponse;

public interface IReservaService extends CrudService <ReservaRequest, ReservaResponse, Long>{
    public ReservaResponse getById(Long id);

}
