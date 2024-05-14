package com.riwi.table_trick.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {
    @NotBlank(message = "El nombre del cliente es requerido")
    private String nombre;
    @NotBlank(message = "El apellido del cliente es requerido")
    private String apellido;
    @Email
    @NotBlank(message = "El email del cliente es requerido")
    private String email;
    @NotBlank(message = "La contraseña del cliente es requerido")
    private String contraseña;
    @NotBlank(message = "El teléfono del cliente es requerido")
    @Size(
            min = 10,
            max = 20,
            message = "El número de teléfono debe tener entre 10 y 20 caracteres"
    )
    private String telefono;
}
