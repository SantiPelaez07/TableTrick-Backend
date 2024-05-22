package com.riwi.table_trick.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.riwi.table_trick.domain.entities.Reserva;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;


public interface ReservaRepository extends JpaRepository <Reserva, String> {
    /*@Query-method*/
    public Reserva findByFecha(LocalDate fecha);
    /*@Query-method*/
    @Query("SELECT r FROM reserva r JOIN r.cliente c WHERE LOWER(c.nombre) LIKE LOWER(concat('%', :nombre, '%'))")
    Page<Reserva> findByClienteNombreIgnoreCaseContaining(@Param("nombre") String nombre, Pageable pageable);
}
