package com.riwi.table_trick.api.dto.response;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {
    private String id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private List<ReservaToClienteResponse> reserva;
}
