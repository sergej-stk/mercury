package com.mercury.modules.auth.server.v1.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercury.core.exceptions.GlobalExceptionHandler;
import com.mercury.modules.auth.server.v1.services.AuthService;
import com.mercury.modules.users.shared.dto.CreateUserDTO;
import com.mercury.modules.users.shared.dto.UserDTO;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(AuthController.class)
@Import({ErrorMvcAutoConfiguration.class, GlobalExceptionHandler.class})
class AuthControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Autowired private AuthService authService;

  @BeforeEach
  void setUp() {
    reset(authService);
  }

  @TestConfiguration
  static class AuthControllerTestConfiguration {

    @Bean
    AuthService authService() {
      return Mockito.mock(AuthService.class);
    }

    @Bean
    @Order(1)
    SecurityFilterChain testSecurityFilterChain(HttpSecurity http) throws Exception {
      http.securityMatcher("/v1/auth/**")
          .authorizeHttpRequests(auth -> auth.requestMatchers("/v1/auth/register").permitAll())
          .csrf(AbstractHttpConfigurer::disable);
      return http.build();
    }
  }

  @Test
  @DisplayName("POST /register - Should return 201 Created and UserDTO with valid data")
  void registerUser_withValidData_shouldReturnCreatedAndUserDTO() throws Exception {
    var id = UUID.randomUUID();
    var inputDTO =
        new CreateUserDTO()
            .setUsername("testuser")
            .setEmail("test@example.com")
            .setPassword("Str0ngP@ssw0rd");

    var expectedUser = new UserDTO().setId(id).setUsername("testuser").setEmail("test@example.com");

    when(authService.registerUser(any(CreateUserDTO.class)))
        .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(expectedUser));

    ResultActions result =
        mockMvc.perform(
            post("/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)));

    result
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(id.toString()))
        .andExpect(jsonPath("$.username").value(expectedUser.getUsername()))
        .andExpect(jsonPath("$.email").value(expectedUser.getEmail()));

    verify(authService, times(1)).registerUser(any(CreateUserDTO.class));
  }

  @Test
  @DisplayName("POST /register - Should return 400 Bad Request with invalid data")
  void registerUser_withInvalidData_shouldReturnBadRequest() throws Exception {
    var invalidInputDTO = new CreateUserDTO().setUsername("test").setEmail("not-an-email");

    ResultActions result =
        mockMvc.perform(
            post("/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidInputDTO)));

    result.andExpect(status().isBadRequest());

    verify(authService, never()).registerUser(any(CreateUserDTO.class));
  }

  @Test
  @DisplayName("POST /register - Should return 409 Conflict when the user already exists")
  void registerUser_whenUserAlreadyExists_shouldReturnConflict() throws Exception {
    var inputDTO =
        new CreateUserDTO()
            .setUsername("existinguser")
            .setEmail("existing@example.com")
            .setPassword("Str0ngP@ssw0rd");

    when(authService.registerUser(any(CreateUserDTO.class)))
        .thenReturn(ResponseEntity.status(HttpStatus.CONFLICT).<UserDTO>body(null));

    ResultActions result =
        mockMvc.perform(
            post("/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)));

    result.andExpect(status().isConflict());

    verify(authService, times(1)).registerUser(any(CreateUserDTO.class));
  }
}
