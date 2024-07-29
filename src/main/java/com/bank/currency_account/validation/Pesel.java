package com.bank.currency_account.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PeselValidator.class)
@Target({ElementType.FIELD})
public @interface Pesel {
    String message() default "User must be at least 18 years old";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
