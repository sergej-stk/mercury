package com.mercury.modules.users.shared.validation.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PasswordStrength {
    String message() default "Password does not meet complexity requirements";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
