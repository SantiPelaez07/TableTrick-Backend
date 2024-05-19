package com.riwi.table_trick.infrastructure.services;

import com.riwi.table_trick.api.dto.request.RegisterRequest;
import com.riwi.table_trick.api.dto.response.AuthResponse;
import com.riwi.table_trick.domain.entities.RestaurantUser;
import com.riwi.table_trick.domain.repositories.UserRepository;
import com.riwi.table_trick.infrastructure.abstract_services.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthService implements IAuthService {


    @Autowired
    private final UserRepository userRepository;
    @Override
    public AuthResponse register(RegisterRequest registerRequest) {


        return null;
    }
}
