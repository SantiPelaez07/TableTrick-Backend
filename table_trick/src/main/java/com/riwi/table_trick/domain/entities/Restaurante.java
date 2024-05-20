package com.riwi.table_trick.domain.entities;

import java.time.LocalDate;
import java.util.List;

import com.riwi.table_trick.util.enums.TipoCuenta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity (name = "restaurante")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 40, nullable = true)
    private String nombrePropietario;
    @Column(length = 50, nullable = true)
    private String apellidoPropietario;
    @Column(length = 100, nullable = true)
    private String nombreComercial;
    @Column(length = 50, nullable = true)
    private String nit;
    @Column(length = 100, nullable = true)
    private String email;
    @Column(nullable = false)
    private LocalDate fecha_registro;
    @Column(length = 200, nullable = true)
    private String ubicacion;
    @Column(nullable = true)
    private int capacidad_maxima;
    @Column(nullable = true)
    private String especialidad;
    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private TipoCuenta tipoCuenta;

    /*
     * Relaciones
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "restaurante", orphanRemoval = false)
    private List<Reserva> reserva;
}
