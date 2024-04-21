package com.tecnico.teste.cadastro.usuarios.user.mocks.dto;

import com.tecnico.teste.cadastro.usuarios.user.dto.UserUpdateDTO;

public class UserUpdateMockDTO {

    private static UserUpdateMockDTO builder;

    private static UserUpdateDTO bean;

    public static UserUpdateMockDTO builder() {
        builder = new UserUpdateMockDTO();
        bean = UserUpdateDTO.builder().build();
        return builder;
    }

    public UserUpdateMockDTO dto() {
        bean.setId(1L);
        bean.setName("Teste");
        return builder;
    }

    public UserUpdateMockDTO withName(String name) {
        bean.setId(1L);
        bean.setName(name);
        return builder;
    }

    public UserUpdateDTO build() {
        return bean;
    }

}
