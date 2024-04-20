package com.tecnico.teste.cadastro.usuarios.user.mocks.dto;

import com.tecnico.teste.cadastro.usuarios.user.dto.UserUpdateDTO;

public class UserUpdateDTOMock {

    private static UserUpdateDTOMock builder;

    private static UserUpdateDTO bean;

    public static UserUpdateDTOMock builder() {
        builder = new UserUpdateDTOMock();
        bean = UserUpdateDTO.builder().build();
        return builder;
    }

    public UserUpdateDTOMock dto() {
        bean.setId(1L);
        bean.setName("Teste");
        return builder;
    }

    public UserUpdateDTOMock withName(String name) {
        bean.setId(1L);
        bean.setName(name);
        return builder;
    }

    public UserUpdateDTO build() {
        return bean;
    }

}
