package com.mercury.modules.users.shared.validation.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * Implements the validation logic for the @PasswordStrength annotation. Checks for common password
 * complexity requirements: - Minimum length (8 characters) - At least one uppercase letter - At
 * least one lowercase letter - At least one digit - At least one special character from a defined
 * set.
 */
public class PasswordStrengthValidator implements ConstraintValidator<PasswordStrength, String> {
    private static final int MIN_LENGTH = 8;
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile("[a-z]");
    private static final Pattern DIGIT_PATTERN = Pattern.compile("[0-9]");
    private static final Pattern SPECIAL_CHAR_PATTERN =
            Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]");

    @Override
    public void initialize(PasswordStrength constraintAnnotation) {}

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.isEmpty()) {
            return true;
        }

        if (password.length() < MIN_LENGTH) {
            return false;
        }

        if (!UPPERCASE_PATTERN.matcher(password).find()) {
            return false;
        }

        if (!LOWERCASE_PATTERN.matcher(password).find()) {
            return false;
        }

        if (!DIGIT_PATTERN.matcher(password).find()) {
            return false;
        }

        if (!SPECIAL_CHAR_PATTERN.matcher(password).find()) {
            return false;
        }

        return true;
    }
}
