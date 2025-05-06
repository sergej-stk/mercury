package com.mercury.modules.auth.server.v1.controllers;

import com.mercury.core.exceptions.ErrorDetailsDTO;
import com.mercury.modules.auth.server.v1.services.AuthService;
import com.mercury.modules.users.shared.dto.CreateUserDTO;
import com.mercury.modules.users.shared.dto.UserDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/v1/auth")
@Tag(name = "Authentication")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(
            summary = "Register a new user",
            description = "Creates a new user account based on the provided details in the request body."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201" ,
                    description = "User successfully registered",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data provided (e.g., validation errors)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDetailsDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "User already registered (email or username already exists)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorDetailsDTO.class)
                    )
            )
    })
    @PostMapping(
            value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        return authService.registerUser(createUserDTO);
    }
}
