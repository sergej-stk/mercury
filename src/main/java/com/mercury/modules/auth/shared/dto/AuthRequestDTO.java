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
    name = "AuthRequest",
    title = "Auth Request",
    description =
        "Represents the request data required to obtain an access token."
            + " This may include credentials such as username and password, or a refresh token,"
            + " depending on the grant type used.")
public class AuthRequestDTO {

  @Schema(
      title = "Grant Type",
      description =
          "The type of OAuth 2.0 grant. Common values are 'password' for authentication"
              + " with username and password, or 'refresh_token' for renewing an access token.",
      example = "password",
      requiredMode = Schema.RequiredMode.REQUIRED,
      accessMode = Schema.AccessMode.WRITE_ONLY,
      allowableValues = {"password", "refresh_token"})
  @JsonProperty("grant_type")
  private String grantType;

  @Schema(
      title = "Username",
      description =
          "The username of the user requesting an access token. Required if the grant type"
              + " is 'password'.",
      example = "john.doe",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      accessMode = Schema.AccessMode.WRITE_ONLY)
  @JsonProperty("username")
  private String username;

  @Schema(
      title = "Password",
      description = "The user's password. Required if the grant type is 'password'.",
      example = "Str0ngP@sswOrd!",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      format = "password",
      accessMode = Schema.AccessMode.WRITE_ONLY)
  @JsonProperty("password")
  private String password;

  @Schema(
      title = "Refresh Token",
      description = "The refresh token used to obtain a new access token.",
      example = "sample-refresh-token-xyz789",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      accessMode = Schema.AccessMode.WRITE_ONLY)
  @JsonProperty("refresh_token")
  private String refreshToken;

  @Schema(
      title = "Client ID",
      description =
          "The unique identifier of the client requesting the token. May be required for"
              + " certain grant types or server configurations.",
      example = "myApiClientId",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      accessMode = Schema.AccessMode.WRITE_ONLY)
  @JsonProperty("client_id")
  private String clientId;
}
