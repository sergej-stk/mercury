package com.mercury.modules.users.dbo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(
    name = "users",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = "publicId"),
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email"),
    })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(columnDefinition = "UUID", updatable = false, nullable = false)
  private UUID id;

  @NotBlank
  @Size(min = 8, max = 20)
  @Column(nullable = false, unique = true, length = 20)
  @NaturalId
  private String publicId;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(nullable = false)
  private LocalDateTime modifiedAt;

  @NotBlank
  @Size(min = 3, max = 50)
  @Column(nullable = false, unique = true, length = 50)
  private String username;

  @NotBlank
  @Email
  @Size(max = 100)
  @Column(nullable = false, unique = true, length = 100)
  private String email;

  @NotBlank
  @Column(nullable = false)
  private String password;
}
