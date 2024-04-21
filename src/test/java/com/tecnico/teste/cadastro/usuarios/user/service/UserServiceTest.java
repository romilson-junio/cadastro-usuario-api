package com.tecnico.teste.cadastro.usuarios.user.service;

import com.tecnico.teste.cadastro.usuarios.handler.exception.WebApplicationException;
import com.tecnico.teste.cadastro.usuarios.user.dto.UserDTO;
import com.tecnico.teste.cadastro.usuarios.user.dto.UserUpdateDTO;
import com.tecnico.teste.cadastro.usuarios.user.entity.User;
import com.tecnico.teste.cadastro.usuarios.user.mocks.UserMock;
import com.tecnico.teste.cadastro.usuarios.user.mocks.dto.UserMockDTO;
import com.tecnico.teste.cadastro.usuarios.user.mocks.dto.UserUpdateMockDTO;
import com.tecnico.teste.cadastro.usuarios.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;



    @Test
    void should_saveUserSuccessfully() {

        User user = UserMock.builder().save();
        doReturn(user).when(userRepository).save(any());
        doReturn(Boolean.FALSE).when(userRepository).existsByEmail(anyString());

        UserDTO dto = UserMockDTO.builder().toSave().build();
        final UserDTO save = userService.save(dto);

        assertNotNull(save);
        assertNotNull(save.getId());
        assertEquals(dto.getName(), save.getName());
        assertEquals(dto.getEmail(), save.getEmail());
        assertNull(save.getPassword());

        verify(userRepository, times(1)).existsByEmail(anyString());
        verify(userRepository, times(1)).save(any());

    }

    @Test
    void should_saveUserThrow() {
        doReturn(Boolean.TRUE).when(userRepository).existsByEmail(anyString());

        UserDTO dto = UserMockDTO.builder().toSave().build();

        WebApplicationException exception = assertThrows(WebApplicationException.class, () -> userService.save(dto));

        assertEquals("Email já cadastrado!", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());

        verify(userRepository, times(1)).existsByEmail(anyString());
        verify(userRepository, times(0)).save(any());

    }

    @Test
    void should_updateUserSuccessfully() {
        User user = UserMock.builder().save();
        doReturn(Optional.of(user)).when(userRepository).findById(anyLong());
        doReturn(user).when(userRepository).save(any());

        UserDTO dto = UserMockDTO.builder().fromSave().build();
        final UserDTO update = userService.update(dto, 1L);

        assertNotNull(update);

        assertEquals(dto.getId(), update.getId());
        assertEquals(dto.getName(), update.getName());
        assertEquals(dto.getEmail(), update.getEmail());
        assertNull(update.getPassword());

        verify(userRepository, times(1)).findById(anyLong());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void should_mergeUserSuccessfully() {
        User user = UserMock.builder().save();
        doReturn(Optional.of(user)).when(userRepository).findById(anyLong());
        doReturn(user).when(userRepository).save(any());

        UserUpdateDTO dto = UserUpdateMockDTO.builder().dto().build();
        final UserDTO merge = userService.merge(dto, 1L);

        assertNotNull(merge);

        assertEquals(dto.getId(), merge.getId());
        assertEquals(dto.getName(), merge.getName());

        verify(userRepository, times(1)).findById(anyLong());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void should_deleteByIdUserSuccessfully() {

        doReturn(Boolean.TRUE).when(userRepository).existsById(anyLong());
        doNothing().when(userRepository).deleteById(anyLong());

        userService.deleteById(1L);

        verify(userRepository, times(1)).existsById(anyLong());
        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void should_deleteByIdUserThrow() {

        final Long id = 1L;
        doReturn(Boolean.FALSE).when(userRepository).existsById(anyLong());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> userService.deleteById(id));

        assertEquals(String.format("Nenhum usuário encontrado para o id: %s!", id), exception.getMessage());

        verify(userRepository, times(1)).existsById(anyLong());
        verify(userRepository, times(0)).deleteById(anyLong());
    }

    @Test
    void should_deleteUserSuccessfully() {

        UserDTO user = UserMockDTO.builder().fromSave().build();

        doReturn(Boolean.TRUE).when(userRepository).existsById(anyLong());
        doNothing().when(userRepository).delete(any());

        userService.delete(user);

        verify(userRepository, times(1)).existsById(anyLong());
        verify(userRepository, times(1)).delete(any());
    }

    @Test
    void should_deleteUserThrow() {

        UserDTO user = UserMockDTO.builder().fromSave().build();

        doReturn(Boolean.FALSE).when(userRepository).existsById(anyLong());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> userService.delete(user));

        assertEquals(String.format("Nenhum usuário encontrado para o id: %s!", user.getId()), exception.getMessage());

        verify(userRepository, times(1)).existsById(anyLong());
        verify(userRepository, times(0)).delete(any());

    }

    @Test
    void should_findAllUserSuccessfully() {

        final List<User> users = List.of(UserMock.builder().save());
        doReturn(users).when(userRepository).findAll();

        final List<UserDTO> list = userService.findAll();

        assertEquals(users.size(), list.size());
        for (int i = 0; i < users.size(); i++) {
            assertEquals(users.get(i).getId(), list.get(i).getId());
            assertEquals(users.get(i).getName(), list.get(i).getName());
            assertEquals(users.get(i).getEmail(), list.get(i).getEmail());
        }

        verify(userRepository, times(1)).findAll();

    }

    @Test
    void should_findByIdUserSuccessfully() {
        User user = UserMock.builder().save();
        doReturn(Optional.of(user)).when(userRepository).findById(anyLong());

        final UserDTO find = userService.findById(user.getId());

        assertNotNull(find);

        assertEquals(user.getId(), find.getId());
        assertEquals(user.getName(), find.getName());
        assertEquals(user.getEmail(), find.getEmail());

        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    void should_findByIdUserThrow() {
        final Long id = 1L;

        doReturn(Optional.empty()).when(userRepository).findById(anyLong());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> userService.findById(id));

        assertEquals(String.format("Nenhum usuário encontrado para o id: %s!", id), exception.getMessage());


        verify(userRepository, times(1)).findById(anyLong());
    }
}