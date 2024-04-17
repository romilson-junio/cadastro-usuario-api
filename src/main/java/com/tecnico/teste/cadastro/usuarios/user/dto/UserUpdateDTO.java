package com.tecnico.teste.cadastro.usuarios.user.dto;

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
public class UserUpdateDTO {

    private Long id;

    @NotBlank(message = "O nome é um campo obrigatório")
    @Length(min = 3, max = 50, message = "O nome deve ter no mínimo 3 caracteres e no máximo 50")
    private String name;
}
