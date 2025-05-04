package com.mercury.modules.auth.server.v1.controllers;

import com.mercury.modules.users.shared.dto.CreateUserDTO;
import com.mercury.modules.users.shared.dto.UserDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/v1/auth")
@Tag(name = "Authentication")
public class AuthController {
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        return ResponseEntity.ok(new UserDTO());
    }
}
