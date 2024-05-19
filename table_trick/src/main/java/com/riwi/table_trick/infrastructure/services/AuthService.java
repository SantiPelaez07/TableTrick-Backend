package com.riwi.table_trick.infrastructure.services;

import com.riwi.table_trick.api.dto.request.LoginRequest;
import com.riwi.table_trick.api.dto.request.RegisterRequest;
import com.riwi.table_trick.api.dto.response.AuthResponse;
import com.riwi.table_trick.domain.entities.RestaurantUser;
import com.riwi.table_trick.domain.repositories.UserRepository;
import com.riwi.table_trick.infrastructure.abstract_services.IAuthService;
import com.riwi.table_trick.util.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        // Implementación del login (pendiente)
        return null;
    }

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        RestaurantUser restaurantUser =  RestaurantUser.builder()
                .user(registerRequest.getNombre())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.EMPRESA)
                .build();

        userRepository.save(restaurantUser);

        return AuthResponse.builder()
                .token(jwtService.getToken(restaurantUser))
                .mensaje("Creado correctamente")
                .build();
    }
}
