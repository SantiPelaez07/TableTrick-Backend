package com.riwi.table_trick.domain.entities;

import java.time.LocalDate;
import java.time.LocalTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "reserva")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private LocalTime hora;
    @Column(nullable = false)
    private LocalDate fecha;
    @Column(length = 50, nullable = false)
    private String tipo;
    @Column(nullable = false)
    private int cantidadPersonas;
    @Column( length = 500, nullable = false)
    private String descripcion;
    /*
     * Relaciones
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurante", referencedColumnName = "id")
    private Restaurante restaurante;
}
