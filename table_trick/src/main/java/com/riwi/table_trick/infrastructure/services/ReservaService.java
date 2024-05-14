package com.riwi.table_trick.infrastructure.services;

import com.riwi.table_trick.api.dto.request.ReservaRequest;
import com.riwi.table_trick.api.dto.response.ClienteResponseToReserva;
import com.riwi.table_trick.api.dto.response.ReservaResponse;
import com.riwi.table_trick.api.dto.response.RestauranteResponseToReserva;
import com.riwi.table_trick.domain.entities.Cliente;
import com.riwi.table_trick.domain.entities.Reserva;
import com.riwi.table_trick.domain.entities.Restaurante;
import com.riwi.table_trick.domain.repositories.ClienteRepository;
import com.riwi.table_trick.domain.repositories.ReservaRepository;
import com.riwi.table_trick.domain.repositories.RestauranteRepository;
import com.riwi.table_trick.infrastructure.abstract_services.IReservaService;
import com.riwi.table_trick.util.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    private final ClienteRepository clienteRepository;
    @Autowired
    private final RestauranteRepository restauranteRepository;

    @Override
    public void delete(String id) {
    }

    @Override
    public ReservaResponse update(ReservaRequest request, String id) {
        /*Necesito crear un response*/
        /*La request me trae el id de las relaciones especificas de la reserva*/
        /*Necesitamos el cliente y el restaurante*/
        Cliente cliente = this.clienteRepository.findById(request.getCliente_id()).orElseThrow(null);
        Restaurante restaurante = this.restauranteRepository.findById(request.getRestaurante_id()).orElseThrow(null);


        Reserva reserva = this.requestToEntity(request);
        reserva.setCliente(cliente);
        reserva.setRestaurante(restaurante);
        //Le agregamos el id, es la diferencia prinicipal con el crear
        reserva.setId(id);
        return this.entityToResponse(this.reservaRepository.save(reserva));
    }

    @Override
    public ReservaResponse create(ReservaRequest reservaRequest) {
        Cliente cliente = this.clienteRepository.findById(reservaRequest.getCliente_id()).orElseThrow(null);
        Restaurante restaurante = this.restauranteRepository.findById(reservaRequest.getRestaurante_id()).orElseThrow(null);


        Reserva reserva =  this.requestToEntity(reservaRequest);

        reserva.setCliente(cliente);
        reserva.setRestaurante(restaurante);

        return this.entityToResponse(this.reservaRepository.save(reserva));
    }

    @Override
    public Page<ReservaResponse> getAll(int page, int size, SortType sort) {

        PageRequest pagination = null;

        switch (sort){
            case NONE ->  pagination = PageRequest.of(page, size);
            //Modificar por la dirección que deseamos
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC));
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC));
        }


        return this.reservaRepository.findAll(pagination).map(reserva -> this.entityToResponse(reserva));
    }

    @Override
    public ReservaResponse getById(String id) {
        return null;
    }



    //Analizar el porqué no se hace el findByID Desde acá
    private Reserva requestToEntity(ReservaRequest reservaRequest){
        return Reserva.builder()
                .hora(reservaRequest.getHora())
                .fecha(reservaRequest.getFecha())
                .tipo(reservaRequest.getTipo())
                .cantidadPersonas(reservaRequest.getCantidadPersonas())
                .descripcion(reservaRequest.getDescripcion())
                .build();
    }


    private ReservaResponse entityToResponse(Reserva reserva){
         ClienteResponseToReserva cliente  = new ClienteResponseToReserva();
         BeanUtils.copyProperties(reserva.getCliente(), cliente);

         RestauranteResponseToReserva restaurante = new RestauranteResponseToReserva();
         BeanUtils.copyProperties(reserva.getRestaurante(), restaurante);



        return ReservaResponse.builder()
                .id(reserva.getId())
                .hora(reserva.getHora())
                .fecha(reserva.getFecha())
                .descripcion(reserva.getDescripcion())
                .tipo(reserva.getTipo())
                .cliente(cliente)
                .restaurante(restaurante)
                .build();
    }







}
