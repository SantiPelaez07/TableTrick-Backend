package com.riwi.table_trick.infrastructure.abstract_services;

import com.riwi.table_trick.api.dto.request.RestauranteRequest;
import com.riwi.table_trick.api.dto.response.RestauranteResponse;

public interface IRestauranteService extends CrudService <RestauranteRequest, RestauranteResponse, String>{
    public RestauranteResponse getById(String id);
}
