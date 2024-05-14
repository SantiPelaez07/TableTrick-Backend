package com.riwi.table_trick.api.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.riwi.table_trick.util.enums.TipoCuenta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteResponse {
    private String id;
    private String nombreAutor;
    private String apellidoAutor;
    private String nombreComercial;
    private String nit;
    private String email;
    private String contraseña;
    private LocalDate fecha_registro;
    private String Ubicacion;
    private int capacidad_maxima;
    private String especialidad;
    private TipoCuenta tipoCuenta;
    private List<ReservaToClienteResponse> reserva;
}
