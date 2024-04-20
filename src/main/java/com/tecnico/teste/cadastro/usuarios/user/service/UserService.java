package com.tecnico.teste.cadastro.usuarios.user.service;

import com.tecnico.teste.cadastro.usuarios.handler.exception.WebApplicationException;
import com.tecnico.teste.cadastro.usuarios.user.dto.UserDTO;
import com.tecnico.teste.cadastro.usuarios.user.dto.UserUpdateDTO;
import com.tecnico.teste.cadastro.usuarios.user.entity.User;
import com.tecnico.teste.cadastro.usuarios.user.mapper.UserMapper;
import com.tecnico.teste.cadastro.usuarios.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceManager {

    private final UserRepository userRepository;

    @Override
    public UserDTO save(UserDTO dto) {
        if(userRepository.existsByEmail(dto.getEmail())) {
            throw new WebApplicationException(HttpStatus.BAD_REQUEST, "Email já cadastrado!");
        }
        User entity = UserMapper.entity(dto);
        entity = userRepository.save(entity);
        return UserMapper.dto(entity);
    }

    @Override
    public UserDTO update(UserDTO dto, Long id) {
        User user = find(id);
        UserMapper.update(user, dto);
        return UserMapper.dto(userRepository.save(user));
    }

    @Override
    public UserDTO merge(UserUpdateDTO dto, Long id) {
        User user = find(id);
        UserMapper.merge(user, dto);
        return UserMapper.dto(userRepository.save(user));
    }

    @Override
    public void deleteById(Long id) {
        if(!userRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format("Nenhum usuário encontrado para o id: %s!", id));
        }
        userRepository.deleteById(id);
    }

    @Override
    public void delete(UserDTO dto) {
        if(!userRepository.existsById(dto.getId())) {
            throw new EntityNotFoundException(String.format("Nenhum usuário encontrado para o id: %s!", dto.getId()));
        }
        userRepository.delete(UserMapper.entity(dto));
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserMapper::dto).toList();
    }

    @Override
    public UserDTO findById(Long id) {
        User user = find(id);
        return UserMapper.dto(user);
    }

    private User find(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                    new EntityNotFoundException(String.format("Nenhum usuário encontrado para o id: %s!", id)));
    }
}
