package com.mercury.modules.users.server.v1.controller;

import com.mercury.modules.users.server.v1.services.UserService;
import com.mercury.modules.users.shared.dto.UserDTO;
import com.mercury.modules.users.shared.utils.UserUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/v1/users")
@Tag(name = "Users")
@AllArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDTO> registerUser(@PathVariable UUID id) {
    var user = userService.getUserByPublicId(id);
    return ResponseEntity.ok(UserUtils.convertUserToUserDTO(user));
  }
}
