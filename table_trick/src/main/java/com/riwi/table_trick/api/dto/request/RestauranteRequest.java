package com.riwi.table_trick.api.dto.request;

import java.time.LocalDate;

import com.riwi.table_trick.util.enums.TipoCuenta;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    private String nombrePropietario;

    @NotBlank(message = "El apellido del propietario de la compañía es requerido")
    private String apellidoPropietario;

    @NotBlank(message = "El nombre comercial de la compañía es requerido")
    @Size(min = 5, max = 30)
    private String nombreComercial;

    @NotBlank(message = "El nit de la compañía es requerido")
    private String nit;

    @NotBlank(message = "El Email de la compañía es requerido")
    @Email(message = "El formato del correo electrónico no es válido")
    private String email;

    @NotBlank(message = "La fecha del registro de la compañía es requerida")
    @FutureOrPresent(message = "La fecha del registro debe ser en el presente o en el futuro")
    private LocalDate fechaRegistro;

    @NotBlank(message = "La ubicación del establecimiento comercial es requerida")
    private String ubicacion;

    @Positive(message = "La capacidad máxima del establecimiento comercial debe ser un número positivo")
    private int capacidadMaxima;

    @NotBlank(message = "La especialidad del establecimiento comercial es requerida")
    private String especialidad;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "El tipo de cuenta de la compañía es requerido")
    private TipoCuenta tipoCuenta;
}
