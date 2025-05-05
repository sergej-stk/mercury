package com.mercury.modules.users.shared.utils;

import com.mercury.modules.users.dbo.entities.User;
import com.mercury.modules.users.shared.dto.UserDTO;

public class UserUtils {
    public static UserDTO convertUserToUserDTO(final User user) {
        return new UserDTO()
                .setId(user.getPublicId())
                .setDisplayId(user.getDisplayId())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail());
    }
}
