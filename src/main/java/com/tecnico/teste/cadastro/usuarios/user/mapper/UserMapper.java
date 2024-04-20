package com.tecnico.teste.cadastro.usuarios.user.mapper;

import com.tecnico.teste.cadastro.usuarios.user.dto.UserDTO;
import com.tecnico.teste.cadastro.usuarios.user.dto.UserUpdateDTO;
import com.tecnico.teste.cadastro.usuarios.user.entity.User;

public class UserMapper {

    public static User entity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static UserDTO dto(User entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .build();
    }

    public static void merge(User entity, UserUpdateDTO dto) {
        entity.setName(dto.getName());
    }

    public static void update(User entity, UserDTO dto) {
        entity.setName(dto.getName());
    }
}
