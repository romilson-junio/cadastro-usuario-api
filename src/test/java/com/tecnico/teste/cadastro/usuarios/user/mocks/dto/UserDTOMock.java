package com.tecnico.teste.cadastro.usuarios.user.mocks.dto;

import com.tecnico.teste.cadastro.usuarios.user.dto.UserDTO;
import com.tecnico.teste.cadastro.usuarios.user.entity.User;

public class UserDTOMock {

    private static UserDTOMock builder;

    private static UserDTO bean;

    public static UserDTOMock builder() {
        builder = new UserDTOMock();
        bean = UserDTO.builder().build();
        return builder;
    }

    public UserDTOMock toSave() {
        bean.setId(null);
        bean.setName("Teste");
        bean.setEmail("teste@gmail.com");
        bean.setPassword("123456");
        bean.setPasswordConfirmation("123456");
        return builder;
    }

    public UserDTOMock fromsave() {
        bean.setId(1L);
        bean.setName("Teste");
        bean.setEmail("teste@gmail.com");
        bean.setPassword("123456");
        return builder;
    }

    public UserDTOMock withName(String name) {
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
