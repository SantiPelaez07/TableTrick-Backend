package com.riwi.table_trick.api.controllers;


import com.riwi.table_trick.api.dto.request.LoginRequest;
import com.riwi.table_trick.api.dto.request.RegisterRequest;
import com.riwi.table_trick.api.dto.response.AuthResponse;
import com.riwi.table_trick.infrastructure.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticación")
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/login")
    @Operation(
            summary = "Login para usuario",
            description = "El usuario se autentica y se le retornará un token con los detalles especificos",
            tags = {"Autenticación"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Se debe proporcionar usuario y contraseña",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginRequest.class)
                    )
            ),

            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Autenticación satisfactoria",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class)
                    )
            )
    )
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @Operation(
            summary = "Registro para usuario",
            description = "El usuario se registra y se le retornará un token con los detalles especificos",
            tags = {"Autenticación"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Se debe proporcionar usuario, email y contraseña",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RegisterRequest.class)
                    )
            ),

            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Registro satisfactorio",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class)
                    )
            )
    )
    @PostMapping(path = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }


}
