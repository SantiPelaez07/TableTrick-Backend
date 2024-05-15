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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservaService implements IReservaService {
    @Autowired
    private final ReservaRepository reservaRepository;


    @Override
    public void delete(String id) {
        this.reservaRepository.deleteById(id);
    }

    @Override
    public ReservaResponse update(ReservaRequest request, String id) {
        Reserva reserva = this.find(id);
        reserva = this.requestToEntity(request);
        reserva.setId(id);
        return this.entityToResponse(this.reservaRepository.save(reserva));
    }

    @Override
    public ReservaResponse create(ReservaRequest request) {
        Reserva reserva = this.requestToEntity(request);
        return entityToResponse(this.reservaRepository.save(reserva));
    }

    @Override
    public Page<ReservaResponse> getAll(int page, int size, SortType sort) {
        if (page < 0) page = 0;
        PageRequest pagination = null;
        switch (sort) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }
        return this.reservaRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public ReservaResponse getById(String id) {
        return entityToResponse(this.find(id));
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


    private ReservaResponse entityToResponse(Reserva entity){
        return ReservaResponse.builder()
        .hora(entity.getHora())
                .fecha(entity.getFecha())
                .tipo(entity.getTipo())
                .cantidadPersonas(entity.getCantidadPersonas())
                .descripcion(entity.getDescripcion()).build();
    }



    private Reserva find(String id){
        return this.reservaRepository.findById(id).orElseThrow();
    }
    
    
    // private LocalTime hora;
    // private LocalDate fecha;
    // private String tipo;
    // private int cantidadPersonas;
    // private String descripcion;
    // private ClienteResponseToReserva cliente;
    // private RestauranteToReservaConverter restaurante;;




}
