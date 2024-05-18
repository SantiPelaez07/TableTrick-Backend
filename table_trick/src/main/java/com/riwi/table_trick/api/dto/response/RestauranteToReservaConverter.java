package com.riwi.table_trick.api.dto.response;


import com.riwi.table_trick.util.enums.TipoCuenta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteToReservaConverter {
    private String id;
    private String nombreComercial;
    private String nit;
    private String email;
    private LocalDate fecha_registro;
    private String ubicacion;
    private int capacidad_maxima;
    private String especialidad;
    private TipoCuenta tipoCuenta;
}
