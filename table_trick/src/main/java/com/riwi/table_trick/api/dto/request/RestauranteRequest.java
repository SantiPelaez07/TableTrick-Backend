package com.riwi.table_trick.api.dto.request;

import java.time.LocalDate;

import com.riwi.table_trick.util.enums.TipoCuenta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteRequest {
    @NotBlank(message = "El nombre del propietario de la compañía es requerido")
    private String nombreAutor;
    @NotBlank(message = "El apellido del propietario de la compañía es requerido")
    private String apellidoAutor;
    @NotBlank(message = "El nombre comercial de la compañía es requerido")
    private String nombreComercial;
    @NotBlank(message = "El nit de la compañía es requerido")
    private String nit;
    @NotBlank(message = "El Email de la compañía es requerido")
    @Email
    private String email;
    @NotBlank(message = "La contraseña de la compañía es requerido")
    private String contraseña;
    @NotBlank(message = "La fecha del regístro de la compañía es requerido")
    @FutureOrPresent
    private LocalDate fecha_registro;
    @NotBlank(message = "La ubicación del establecimiento comercial es requerido")
    private String Ubicacion;
    @NotBlank(message = "La capacidad máxima del establecimiento comercial es requerido")
    @Positive
    private int capacidad_maxima;
    @NotBlank(message = "La especialidad del establecimiento comercial es requerido")
    private String especialidad;
    @NotBlank(message = "El tipo de cuenta de la compañía es requerido")
    private TipoCuenta tipoCuenta;
}
