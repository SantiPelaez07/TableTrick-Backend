package com.riwi.table_trick.infrastructure.abstract_services;

import com.riwi.table_trick.api.dto.request.LoginRequest;
import com.riwi.table_trick.api.dto.request.RegisterRequest;
import com.riwi.table_trick.api.dto.response.AuthResponse;

public interface IAuthService {
    public AuthResponse login (LoginRequest loginRequest);
    public AuthResponse register(RegisterRequest registerRequest);
}
