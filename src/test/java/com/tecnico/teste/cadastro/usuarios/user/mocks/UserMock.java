package com.tecnico.teste.cadastro.usuarios.user.mocks;

import com.tecnico.teste.cadastro.usuarios.user.entity.User;

public class UserMock {

    private static UserMock builder;

    private static User bean;

    public static UserMock builder() {
        builder = new UserMock();
        bean = new User();
        return builder;
    }

    public User nonSave() {
        bean.setId(null);
        bean.setName("Teste");
        bean.setEmail("teste@gmail.com");
        bean.setPassword("123456");
        return bean;
    }

    public User save() {
        bean.setId(1L);
        bean.setName("Teste");
        bean.setEmail("teste@gmail.com");
        bean.setPassword("123456");
        return bean;
    }

}
