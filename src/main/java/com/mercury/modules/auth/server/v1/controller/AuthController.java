package com.mercury.modules.auth.server.v1.controller;

import com.mercury.core.exceptions.ErrorDetailsDTO;
import com.mercury.modules.auth.server.v1.services.AuthService;
import com.mercury.modules.auth.shared.dto.AuthRequestDTO;
import com.mercury.modules.auth.shared.dto.AuthResponseDTO;
import com.mercury.modules.auth.shared.dto.RefreshRequestDTO;
import com.mercury.modules.auth.shared.dto.RefreshResponseDTO;
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
import org.springframework.http.HttpStatus;
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
      description = "Creates a new user account based on the provided details in the request body.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "User successfully registered",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data provided (e.g., validation errors)",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetailsDTO.class))),
        @ApiResponse(
            responseCode = "409",
            description = "User already registered (email or username already exists)",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetailsDTO.class)))
      })
  @PostMapping(
      value = "/register",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(createUserDTO));
  }

  @Operation(
      summary = "Login user",
      description =
          "Authenticates a user with their credentials (password grant type) and returns access and"
              + " refresh tokens.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Authentication successful, tokens generated",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AuthResponseDTO.class))),
        @ApiResponse(
            responseCode = "400",
            description =
                "Invalid input data provided (e.g., missing credentials or invalid grant type)",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetailsDTO.class))),
        @ApiResponse(
            responseCode = "403",
            description =
                "Authentication failed (invalid username or password, or client authentication"
                    + " failed)",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetailsDTO.class)))
      })
  @PostMapping(
      value = "/oauth2/login",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthResponseDTO> loginUser(
      @Valid @RequestBody AuthRequestDTO authRequestDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authService.loginUser(authRequestDTO));
  }

  @Operation(
      summary = "Refresh access token",
      description =
          "Generates a new access token using a valid refresh token (refresh_token grant type).")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Access token refreshed successfully",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = RefreshResponseDTO.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request (e.g., missing refresh token or invalid grant type)",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetailsDTO.class))),
        @ApiResponse(
            responseCode = "403",
            description =
                "Invalid, expired, or revoked refresh token, or client authentication failed",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetailsDTO.class)))
      })
  @PostMapping(
      value = "/oauth2/refresh",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RefreshResponseDTO> refreshToken(
      @Valid @RequestBody RefreshRequestDTO refreshRequestDTO) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(authService.refreshToken(refreshRequestDTO));
  }
}
