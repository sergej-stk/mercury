package com.mercury.modules.users.server.v1.controller;

import com.mercury.core.exceptions.ErrorDetailsDTO;
import com.mercury.modules.users.server.v1.services.UserService;
import com.mercury.modules.users.shared.dto.UserDTO;
import com.mercury.modules.users.shared.utils.UserUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

  @Operation(
      summary = "Get user by ID",
      description =
          "Retrieves a user's details based on the provided UUID. Returns the user if found.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "User found and returned successfully",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(
            responseCode = "404",
            description = "User with the provided ID does not exist",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorDetailsDTO.class)))
      })
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
    var user = userService.getUserByPublicId(id);
    return ResponseEntity.ok(UserUtils.convertUserToUserDTO(user));
  }
}
