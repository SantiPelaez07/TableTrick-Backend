package com.riwi.table_trick.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.table_trick.domain.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    public Page<Cliente> findByNombreStartingWithIgnoreCase(String nombre, Pageable pageable);
}
