package com.riwi.table_trick.infrastructure.services;

import com.riwi.table_trick.api.dto.request.ReservaRequest;
import com.riwi.table_trick.api.dto.response.ReservaResponse;
import com.riwi.table_trick.infrastructure.abstract_services.IReservaService;
import com.riwi.table_trick.util.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservaService implements IReservaService {


    @Override
    public void delete(Long id) {
    }

    @Override
    public ReservaResponse update(ReservaRequest request, String id) {
        return null;
    }

    @Override
    public ReservaResponse create(ReservaRequest request) {
        return null;
    }

    @Override
    public Page<ReservaResponse> getAll(int page, int size, SortType sort) {
        return null;
    }

    @Override
    public ReservaResponse getById(Long id) {
        return null;
    }
}
