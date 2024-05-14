package com.riwi.table_trick.api.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class ReservaRequest {
    @NotBlank(message = "La hora de la reserva es requerida")
    @FutureOrPresent
    private LocalTime hora;
    @NotBlank(message = "La fecha de la reserva es requerida")
    @FutureOrPresent
    private LocalDate fecha;
    @NotBlank(message = "El tipo de la reserva es requerida")
    private String tipo;
    @NotBlank(message = "La cantidad de personas que asistirán a la reserva es requerida")
    @Positive
    private int cantidadPersonas;
    @NotBlank(message = "La descripción de la reserva es requerida")
    private String descripcion;
}
