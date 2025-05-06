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
    name = "RefreshRequest",
    title = "Refresh Request",
    description =
        "Represents the data required to request a new access token using a refresh token.")
public class RefreshRequestDTO {

  @Schema(
      title = "Grant Type",
      description =
          "The type of OAuth 2.0 grant being used. This must be 'refresh_token' for token renewal"
              + " requests.",
      example = "refresh_token",
      requiredMode = Schema.RequiredMode.REQUIRED,
      accessMode = Schema.AccessMode.WRITE_ONLY,
      allowableValues = {"refresh_token"})
  @JsonProperty("grant_type")
  private String grantType;

  @Schema(
      title = "Refresh Token",
      description = "The previously issued refresh token used to obtain a new access token.",
      example = "sample-refresh-token-12345",
      requiredMode = Schema.RequiredMode.REQUIRED,
      accessMode = Schema.AccessMode.WRITE_ONLY)
  @JsonProperty("refresh_token")
  private String refreshToken;

  @Schema(
      title = "Client ID",
      description =
          "The unique identifier of the client requesting the token renewal. This may be required"
              + " depending on the server configuration.",
      example = "my-api-client-id",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      accessMode = Schema.AccessMode.WRITE_ONLY)
  @JsonProperty("client_id")
  private String clientId;
}
