package com.tecnico.teste.cadastro.usuarios.user.service;

import com.tecnico.teste.cadastro.usuarios.user.dto.UserDTO;
import com.tecnico.teste.cadastro.usuarios.user.dto.UserUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDTO save(UserDTO dto);
    UserDTO update(UserDTO dto, Long id);

    UserDTO merge(UserUpdateDTO dto, Long id);

    void deleteById(Long id);

    void delete(UserDTO dto);

    List<UserDTO> findAll();

    UserDTO findById(Long id);
}
