package com.riwi.table_trick.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.table_trick.domain.entities.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, String>{
    public List<Restaurante> findByNombreComercial(String nombreComercial);
}
