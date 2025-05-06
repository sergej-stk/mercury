package com.mercury.modules.auth.server.v1.services;

import com.mercury.modules.auth.shared.dto.AuthRequestDTO;
import com.mercury.modules.auth.shared.dto.AuthResponseDTO;
import com.mercury.modules.auth.shared.dto.RefreshRequestDTO;
import com.mercury.modules.auth.shared.dto.RefreshResponseDTO;
import com.mercury.modules.users.server.v1.services.UserService;
import com.mercury.modules.users.shared.dto.CreateUserDTO;
import com.mercury.modules.users.shared.dto.UserDTO;
import com.mercury.modules.users.shared.utils.UserUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
  private final UserService userService;

  /**
   * Registers a new user based on the provided user data. It delegates the user creation to the
   * {@link UserService} and returns the created user's data within a {@link ResponseEntity}.
   *
   * @param createUserDTO Data Transfer Object containing the details for the new user. Must be
   *     valid.
   * @return A new created {@link UserDTO}
   */
  public UserDTO registerUser(@Valid CreateUserDTO createUserDTO) {
    var user = userService.createUser(createUserDTO);
    return UserUtils.convertUserToUserDTO(user);
  }

  public RefreshResponseDTO refreshToken(@Valid RefreshRequestDTO refreshRequestDTO) {
    return new RefreshResponseDTO();
  }

  public AuthResponseDTO loginUser(@Valid AuthRequestDTO authRequestDTO) {
    return new AuthResponseDTO();
  }
}
