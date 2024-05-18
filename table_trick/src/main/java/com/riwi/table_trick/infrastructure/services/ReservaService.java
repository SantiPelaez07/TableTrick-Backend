package com.riwi.table_trick.infrastructure.services;

import com.riwi.table_trick.api.dto.request.ReservaRequest;
import com.riwi.table_trick.api.dto.response.ReservaResponse;
import com.riwi.table_trick.domain.entities.Reserva;
import com.riwi.table_trick.domain.repositories.ReservaRepository;
import com.riwi.table_trick.infrastructure.abstract_services.IReservaService;
import com.riwi.table_trick.util.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservaService implements IReservaService {
    @Autowired
    private final ReservaRepository reservaRepository;


    @Override
    public void delete(String id) {

    }

    @Override
    public ReservaResponse update(ReservaRequest request, String id) {
        return null;
    }

    @Override
    public ReservaResponse create(ReservaRequest request) {
        Reserva reserva = this.requestToEntity(request);
        return null;
    }

    @Override
    public Page<ReservaResponse> getAll(int page, int size, SortType sort) {
        return null;
    }

    @Override
    public ReservaResponse getById(String id) {
        return null;
    }


    private Reserva requestToEntity(ReservaRequest reservaRequest){
        return Reserva.builder()
                .hora(reservaRequest.getHora())
                .fecha(reservaRequest.getFecha())
                .tipo(reservaRequest.getTipo())
                .cantidadPersonas(reservaRequest.getCantidadPersonas())
                .descripcion(reservaRequest.getDescripcion())
                .build();
    }


    private ReservaResponse requestToResponse(){
        return null;
    }



}
