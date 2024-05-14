package com.riwi.table_trick.api.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.*;
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

    @NotNull(message = "El id  del cliente es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor a cero")
    private String cliente_id;

    @NotNull(message = "El id  del restaurante es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor a cero")
    private String restaurante_id ;

}
