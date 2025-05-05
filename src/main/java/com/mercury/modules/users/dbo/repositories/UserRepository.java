package com.mercury.modules.users.dbo.repositories;

import com.mercury.modules.users.dbo.entities.User;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    boolean existsByDisplayId(String displayId);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
