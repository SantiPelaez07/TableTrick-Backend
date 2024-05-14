package com.riwi.table_trick.domain.entities;

import java.time.LocalDate;
import java.util.List;

import com.riwi.table_trick.util.enums.TipoCuenta;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    @Column(length = 40, nullable = false)
    private String nombrePropietario;
    @Column(length = 50, nullable = false)
    private String apellidoPropietario;
    @Column(length = 50, nullable = false)
    private String nombreComercial;
    @Column(length = 10, nullable = false)
    private String nit;
    @Column(length = 100, nullable = false)
    private String email;
    @Column(length = 100, nullable = false)
    private String contraseña;
    @Column(nullable = false)
    private LocalDate fecha_registro;
    @Column(length = 180, nullable = false)
    private String ubicacion;
    @Column(nullable = false)
    private int capacidad_maxima;
    @Column(nullable = false)
    private String especialidad;
    @Column(nullable = false)
    private TipoCuenta tipoCuenta;

    /*
     * Relaciones
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Reserva> reserva;
}
