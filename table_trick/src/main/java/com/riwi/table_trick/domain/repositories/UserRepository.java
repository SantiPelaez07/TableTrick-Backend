package com.riwi.table_trick.domain.repositories;

import com.riwi.table_trick.domain.entities.RestaurantUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<RestaurantUser, String> {
    public Optional<RestaurantUser> findByUser(String user);
}
