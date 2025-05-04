package com.mercury.modules.users.shared.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "User",
        title = "User",
        description = "Represents a user with identifiers, core profile information (username, email), and system metadata. Suitable for API responses displaying user details.")
public class UserDTO {
    @Schema(
            title = "ID",
            description = "A stable, unique identifier (UUID) used for referencing the user in public APIs or between systems. Not intended for direct display to end-users.",
            example = "a1b2c3d4-e5f6-7890-1234-567890abcdef",
            requiredMode = Schema.RequiredMode.REQUIRED,
            format = "uuid",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @JsonProperty("id")
    private UUID id;

    @Schema(
            title = "User Display ID",
            description = "A user-friendly, human-readable, unique identifier intended for display to end-users or for easy reference. Format: String, 6-25 characters.",
            example = "USR-GHT7Y2",
            requiredMode = Schema.RequiredMode.REQUIRED,
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @JsonProperty("displayId")
    private String displayId;

    @Schema(
            title = "Username",
            description = "The user's unique username, used for login and identification within the application. Length: 3-50 characters.",
            example = "john.doe",
            requiredMode = Schema.RequiredMode.REQUIRED,
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @JsonProperty("username")
    private String username;

    @Schema(
            title = "Email Address",
            description = "The user's unique email address, used for communication and potentially login or password recovery. Max length: 100 characters.",
            example = "john.doe@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED,
            format = "email",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    @JsonProperty("email")
    private String email;
}