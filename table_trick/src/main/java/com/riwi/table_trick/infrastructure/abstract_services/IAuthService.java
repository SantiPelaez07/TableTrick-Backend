package com.riwi.table_trick.infrastructure.abstract_services;

import com.riwi.table_trick.api.dto.request.RegisterRequest;
import com.riwi.table_trick.api.dto.response.AuthResponse;

public interface IAuthService {
    public AuthResponse register(RegisterRequest registerRequest);
}
