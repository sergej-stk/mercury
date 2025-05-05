package com.mercury.modules.users.server.v1.services;

import com.mercury.modules.users.dbo.entities.User;
import com.mercury.modules.users.dbo.repositories.UserRepository;
import com.mercury.modules.users.shared.dto.CreateUserDTO;
import com.mercury.modules.users.shared.exceptions.EmailAlreadyExistsException;
import com.mercury.modules.users.shared.exceptions.UsernameAlreadyExistsException;
import com.mercury.modules.users.shared.validation.configurations.DisplayIdProperties;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final SecureRandom random = new SecureRandom();
    private final DisplayIdProperties displayIdProperties;

    /**
     * Generates a random string segment based on configured charset and length.
     * This is used as part of the unique display ID.

     * @return A random string conforming to displayIdProperties.
     */
    private String generateRandomIdPart() {
        final String charset = displayIdProperties.getCharset();
        final int length = displayIdProperties.getLength();

        return IntStream.range(0, length)
                .map(i -> random.nextInt(charset.length()))
                .mapToObj(randomIndex -> String.valueOf(charset.charAt(randomIndex)))
                .collect(Collectors.joining());
    }

    /**
     * Generates a unique display ID by combining a prefix and a random part.
     * Retries generation until a unique ID (not present in the database) is found.
     *
     * @return A unique display ID string (e.g., "USR-XYZ789").
     */
    private String generateUniqueDisplayId() {
        final String prefix = displayIdProperties.getPrefix();
        String displayId;
        int attempts = 0;

        log.debug("Attempting to generate unique display ID with prefix '{}'", prefix);
        do {
            attempts++;
            if (attempts > 1) {
                log.warn("Display ID collision detected. Retrying generation (attempt {})...", attempts);
            }
            String randomPart = generateRandomIdPart();
            displayId = prefix + randomPart;
            log.trace("Attempt {}: Generated potential display ID: {}", attempts, displayId);
        } while (userRepository.existsByDisplayId(displayId));

        log.info("Successfully generated unique display ID '{}' after {} attempt(s).", displayId, attempts);
        return displayId;
    }

    /**
     * Validates that the given username does not already exist in the repository.
     *
     * @param username The username to validate.
     * @throws RuntimeException (or ideally a specific exception like UsernameAlreadyExistsException) if the username is already taken.
     */
    private void validateUsernameUniqueness(String username) {
        log.debug("Validating username uniqueness for: {}", username);
        if(userRepository.existsByUsername(username))
        {
            log.error("Validation failed: Username '{}' already exists.", username);
          throw new UsernameAlreadyExistsException();
        }
        log.debug("Username '{}' is unique.", username);
    }

    /**
     * Validates that the given email does not already exist in the repository.
     *
     * @param email The email address to validate.
     * @throws RuntimeException (or ideally a specific exception like EmailAlreadyExistsException) if the email is already registered.
     */
    private void validateEmailUniqueness (String email) {
        log.debug("Validating email uniqueness for user.");
        if(userRepository.existsByEmail(email))
        {
            log.error("Validation failed: Email already exists for the provided user.");
      throw new EmailAlreadyExistsException();
        }
        log.debug("Email is unique for the provided user.");
    }

    /**
     * Creates and persists a new user after validating username/email uniqueness.
     * Generates a unique display ID and encodes the password before saving.
     * This operation is transactional.
     *
     * @param createUserDTO DTO containing the new user's details (username, email, password).
     * @return The persisted {@link User} entity with generated ID and display ID.
     * @throws RuntimeException If the username or email is already taken.
     */
    @Transactional
    public User createUser(CreateUserDTO createUserDTO) {
        log.info("Attempting to create user with username: {}", createUserDTO.getUsername());
        validateUsernameUniqueness(createUserDTO.getUsername());
        validateEmailUniqueness (createUserDTO.getEmail());
        log.debug("Username and email are unique for {}. Proceeding with user creation.", createUserDTO.getUsername());

        var result = new User()
                .setUsername(createUserDTO.getUsername())
                .setDisplayId(generateUniqueDisplayId())
                .setEmail(createUserDTO.getEmail())
                .setPassword(passwordEncoder.encode(createUserDTO.getPassword()));

        log.debug("Saving new user: username={}, displayId={}",
                result.getUsername(), result.getDisplayId());
        result = userRepository.save(result);

        log.info("Successfully created and saved user: username={}, displayId={}, id={}",
                result.getUsername(), result.getDisplayId(), result.getId());

        return result;
    }
}
