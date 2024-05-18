package com.riwi.table_trick.api.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ReservaRequest {
    @NotNull(message = "La hora de la reserva es requerida")
    @FutureOrPresent(message = "La hora de la reserva debe ser en el presente o en el futuro")
    private LocalTime hora;

    @NotNull(message = "La fecha de la reserva es requerida")
    @FutureOrPresent(message = "La fecha de la reserva debe ser en el presente o en el futuro")
    private LocalDate fecha;

    @NotBlank(message = "El tipo de la reserva es requerido")
    @Size(min = 1, max = 255, message = "El tipo de la reserva debe tener entre 1 y 255 caracteres")
    private String tipo;

    @NotNull(message = "La cantidad de personas que asistirán a la reserva es requerida")
    @Positive(message = "La cantidad de personas debe ser un número positivo")
    private Integer cantidadPersonas;

    @NotBlank(message = "La descripción de la reserva es requerida")
    @Size(max = 1000, message = "La descripción de la reserva no puede exceder los 1000 caracteres")
    private String descripcion;

    @NotBlank(message = "Se debe seleccionar un cliente paara esta reserva")
    private String idCliente;

    @NotBlank(message = "Se debe seleccionar un restaurante paara esta reserva")
    private String idRestaurante;
    
}
