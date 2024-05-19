package com.riwi.table_trick.api.dto.request;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message ="El nombre es obligatorio")
    @Size(min = 8, max = 150, message = "El usuario debe tener entre 8 y 150 caracteres")
    private String nombre;


    @NotBlank(message ="El email es obligatorio")
    @Size(min = 8, max = 150, message = "El usuario debe tener entre 8 y 150 caracteres")
    @Pattern(regexp = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$", message = "El email no es válido")
    private String email;

    @NotBlank(message ="El password es obligatorio")
    @Size(min = 8, max = 150, message = "El usuario debe tener entre 8 y 150 caracteres")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "La contraseña no cumple con los requisitos mínimos")
    private String password;
}



