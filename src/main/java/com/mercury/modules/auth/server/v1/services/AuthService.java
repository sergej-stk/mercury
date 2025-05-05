package com.mercury.modules.auth.server.v1.services;

import com.mercury.modules.users.server.v1.services.UserService;
import com.mercury.modules.users.shared.dto.CreateUserDTO;
import com.mercury.modules.users.shared.dto.UserDTO;
import com.mercury.modules.users.shared.utils.UserUtils;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;

    public ResponseEntity<UserDTO> registerUser(@Valid CreateUserDTO createUserDTO) {
        var user = userService.createUser(createUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserUtils.convertUserToUserDTO(user));
    }
}
