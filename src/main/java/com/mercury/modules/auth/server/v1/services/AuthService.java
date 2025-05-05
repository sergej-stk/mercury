package com.mercury.modules.auth.server.v1.services;

import com.mercury.modules.users.shared.dto.CreateUserDTO;
import com.mercury.modules.users.shared.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  public ResponseEntity<UserDTO> registerUser(@Valid CreateUserDTO createUserDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(new UserDTO());
  }
}
