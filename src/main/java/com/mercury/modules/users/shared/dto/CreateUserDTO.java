package com.mercury.modules.users.shared.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercury.modules.users.shared.validation.constraints.PasswordStrength;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "CreateUserRequest",
        title = "User Creation Request",
        description = "Data required to create a new user account.")
public class CreateUserDTO {
    @Schema(
            title = "Username",
            description =
                    "The desired unique username for the new user. Must be between 3 and 50"
                            + " characters.",
            example = "new.user",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 3,
            maxLength = 50)
    @JsonProperty("username")
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @Schema(
            title = "Email Address",
            description =
                    "The desired unique email address for the new user. Must be a valid email"
                            + " format and max 100 characters.",
            example = "new.user@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED,
            format = "email",
            maxLength = 100)
    @JsonProperty("email")
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be a valid format")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @Schema(
            title = "Password",
            description =
                    "The desired password for the new user. Must meet complexity requirements"
                        + " (e.g., min 8 characters, contain uppercase, lowercase, digit, special"
                        + " character).",
            example = "S3cureP@ssw0rd!",
            requiredMode = Schema.RequiredMode.REQUIRED,
            format = "password",
            minLength = 8,
            accessMode = Schema.AccessMode.WRITE_ONLY)
    @JsonProperty("password")
    @NotBlank(message = "Password is required")
    @PasswordStrength
    private String password;
}
