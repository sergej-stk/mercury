package com.mercury.modules.users.dbo.repositories;

import com.mercury.modules.users.dbo.entities.User;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, UUID> {
  boolean existsByDisplayId(String displayId);

  boolean existsByEmail(String email);

  boolean existsByUsername(String username);
}
