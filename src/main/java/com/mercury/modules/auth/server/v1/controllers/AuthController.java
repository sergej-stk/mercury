package com.mercury.modules.auth.server.v1.controllers;

import com.mercury.modules.users.shared.dto.UserDTO;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/v1")
@Tag(name = "Authentication")
public class AuthController {
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser() {
        return ResponseEntity.ok(new UserDTO());
    }
}
