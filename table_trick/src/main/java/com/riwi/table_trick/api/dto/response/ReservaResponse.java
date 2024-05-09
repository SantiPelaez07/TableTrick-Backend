package com.riwi.table_trick.api.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservaResponse {
    private Long id;
    private LocalTime hora;
    private LocalDate fecha;
    private String tipo;
    private int cantidadPersonas;
    private String descripcion;
    private ClienteResponse cliente;
    private RestauranteResponse restaurante;
}
