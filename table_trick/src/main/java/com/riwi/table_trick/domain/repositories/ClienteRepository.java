package com.riwi.table_trick.domain.repositories;

import java.util.List;

import com.riwi.table_trick.api.dto.response.ClienteResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.table_trick.domain.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    List<Cliente> findByNombre(String nombre);
}
