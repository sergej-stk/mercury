package com.mercury.modules.auth.shared.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    name = "AuthResponse",
    title = "Auth Response",
    description = "Represents the response data returned after a successful access token request.")
public class AuthResponseDTO {

  @Schema(
      title = "Access Token",
      description = "The issued access token used to access protected resources.",
      example = "sample-access-token-abc123",
      requiredMode = Schema.RequiredMode.REQUIRED,
      accessMode = Schema.AccessMode.READ_ONLY)
  @JsonProperty("access_token")
  private String accessToken;

  @Schema(
      title = "Refresh Token",
      description = "The refresh token used to obtain new access tokens.",
      example = "sample-refresh-token-xyz789",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      accessMode = Schema.AccessMode.READ_ONLY)
  @JsonProperty("refresh_token")
  private String refreshToken;

  @Schema(
      title = "Token Type",
      description = "The type of the issued token. Typically 'Bearer'.",
      example = "Bearer",
      requiredMode = Schema.RequiredMode.REQUIRED,
      accessMode = Schema.AccessMode.READ_ONLY)
  @JsonProperty("token_type")
  private String tokenType;

  @Schema(
      title = "Expires In",
      description = "The lifetime of the access token in seconds.",
      example = "3600",
      requiredMode = Schema.RequiredMode.REQUIRED,
      accessMode = Schema.AccessMode.READ_ONLY)
  @JsonProperty("expires_in")
  private Long expiresIn;
}
