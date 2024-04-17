package com.tecnico.teste.cadastro.usuarios.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tecnico.teste.cadastro.usuarios.user.annotations.PasswordConfirmation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@PasswordConfirmation
public class UserDTO {

    private Long id;

    @NotBlank(message = "O nome é um campo obrigatório!")
    @Length(min = 3, max = 50, message = "O nome deve ter no mínimo 3 caracteres e no máximo 50!")
    private String name;

    @NotBlank(message = "A senha é um campo obrigatório!")
    @Length(min = 6, max = 20, message = "A senha deve ter no mínimo 6 caracteres e no máximo 20!")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    @NotBlank(message = "A confirmação de senha é um campo obrigatório!")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String passwordConfirmation;

    @NotBlank(message = "O email é um campo obrigatório!")
    @Email(message = "Informe um email válido!")
    private String email;
}
