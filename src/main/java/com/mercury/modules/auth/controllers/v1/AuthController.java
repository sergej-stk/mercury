package com.mercury.modules.auth.controllers.v1;

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
    public ResponseEntity<String> registerUser() {
        return ResponseEntity.ok("Registered User");
    }
}
