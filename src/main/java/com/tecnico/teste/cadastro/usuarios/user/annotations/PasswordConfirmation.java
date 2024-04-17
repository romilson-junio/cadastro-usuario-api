package com.tecnico.teste.cadastro.usuarios.user.annotations;

import com.tecnico.teste.cadastro.usuarios.user.annotations.validator.PasswordConfirmationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = {PasswordConfirmationValidator.class})
public @interface PasswordConfirmation {

    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

