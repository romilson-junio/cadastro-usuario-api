package com.tecnico.teste.cadastro.usuarios.user.mocks.dto;

import com.tecnico.teste.cadastro.usuarios.user.dto.UserDTO;

public class UserMockDTO {

    private static UserMockDTO builder;

    private static UserDTO bean;

    public static UserMockDTO builder() {
        builder = new UserMockDTO();
        bean = UserDTO.builder().build();
        return builder;
    }

    public UserMockDTO toSave() {
        bean.setId(null);
        bean.setName("Teste");
        bean.setEmail("teste@gmail.com");
        bean.setPassword("123456");
        bean.setPasswordConfirmation("123456");
        return builder;
    }

    public UserMockDTO fromSave() {
        bean.setId(1L);
        bean.setName("Teste");
        bean.setEmail("teste@gmail.com");
        bean.setPassword("123456");
        return builder;
    }

    public UserMockDTO withName(String name) {
        bean.setId(1L);
        bean.setName(name);
        bean.setEmail("teste@gmail.com");
        bean.setPassword("123456");
        return builder;
    }

    public UserDTO build() {
        return bean;
    }

}
