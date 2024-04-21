package com.tecnico.teste.cadastro.usuarios.user.annotations.validator;

import com.tecnico.teste.cadastro.usuarios.user.annotations.PasswordConfirmation;
import com.tecnico.teste.cadastro.usuarios.user.dto.UserDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PasswordConfirmationValidator implements ConstraintValidator<PasswordConfirmation, UserDTO>  {

    @Override
    public boolean isValid(UserDTO dto, ConstraintValidatorContext context) {
        List<FieldError> errors = new ArrayList<>();

        if( !dto.getPassword().equals(dto.getPasswordConfirmation()) ) {
            errors.add(new FieldError(
                    "User", "password", "A senha e a confirmação de senha não são iguais!"));
        }

        for (FieldError e: errors) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getDefaultMessage())
                    .addPropertyNode(e.getField())
                    .addConstraintViolation();
        }
        return errors.isEmpty();
    }
}
