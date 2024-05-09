package com.riwi.table_trick.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riwi.table_trick.domain.entities.Reserva;
import java.time.LocalDate;


public interface ReservaRepository extends JpaRepository <Reserva, Long>{
    public Reserva findByFecha(LocalDate fecha);
}
