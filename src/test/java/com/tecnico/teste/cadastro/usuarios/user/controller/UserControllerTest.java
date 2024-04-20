package com.tecnico.teste.cadastro.usuarios.user.controller;

import com.tecnico.teste.cadastro.usuarios.user.dto.UserDTO;
import com.tecnico.teste.cadastro.usuarios.user.dto.UserUpdateDTO;
import com.tecnico.teste.cadastro.usuarios.user.mocks.dto.UserDTOMock;
import com.tecnico.teste.cadastro.usuarios.user.mocks.dto.UserUpdateDTOMock;
import com.tecnico.teste.cadastro.usuarios.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    void should_findAll() {
        List<UserDTO> users = List.of(UserDTOMock.builder().fromsave().build());
        doReturn(users).when(userService).findAll();

        final ResponseEntity<List<UserDTO>> response = userController.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        for (int i = 0; i < users.size(); i++) {
            assertEquals(users.get(i).getId(), response.getBody().get(i).getId());
            assertEquals(users.get(i).getName(), response.getBody().get(i).getName());
            assertEquals(users.get(i).getEmail(), response.getBody().get(i).getEmail());
        }

        verify(userService, times(1)).findAll();
    }

    @Test
    void should_findById() {
        UserDTO user = UserDTOMock.builder().fromsave().build();
        doReturn(user).when(userService).findById(anyLong());

        final ResponseEntity<UserDTO> response = userController.findById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user.getId(), response.getBody().getId());
        assertEquals(user.getName(), response.getBody().getName());
        assertEquals(user.getEmail(), response.getBody().getEmail());

        verify(userService, times(1)).findById(anyLong());

    }

    @Test
    void should_save() {
        UserDTO dto = UserDTOMock.builder().toSave().build();
        UserDTO user = UserDTOMock.builder().fromsave().build();
        doReturn(user).when(userService).save(any());

        final ResponseEntity<UserDTO> response = userController.save(dto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        assertEquals(user.getId(), response.getBody().getId());
        assertEquals(user.getName(), response.getBody().getName());
        assertEquals(user.getEmail(), response.getBody().getEmail());

        verify(userService, times(1)).save(any());
    }

    @Test
    void should_update() {

        UserDTO update = UserDTOMock.builder().withName("Teste Update").build();
        doReturn(update).when(userService).update(any(), anyLong());

        final ResponseEntity<UserDTO> response = userController.update(update.getId(), update);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        assertEquals(update.getId(), response.getBody().getId());
        assertEquals(update.getName(), response.getBody().getName());
        assertEquals(update.getEmail(), response.getBody().getEmail());

        verify(userService, times(1)).update(any(), anyLong());
    }

    @Test
    void should_merge() {
        UserUpdateDTO merge = UserUpdateDTOMock.builder().withName("Teste Merge").build();
        UserDTO dto = UserDTOMock.builder().withName("Teste Merge").build();
        doReturn(dto).when(userService).merge(any(), anyLong());

        final ResponseEntity<UserDTO> response = userController.merge(merge.getId(), merge);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        assertEquals(merge.getId(), response.getBody().getId());
        assertEquals(merge.getName(), response.getBody().getName());

        verify(userService, times(1)).merge(any(), anyLong());
    }

    @Test
    void should_delete() {
        UserDTO dto = UserDTOMock.builder().fromsave().build();
        doNothing().when(userService).delete(any());

        final ResponseEntity<HttpStatus> response = userController.delete(dto);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(userService, times(1)).delete(any());
    }

    @Test
    void should_deleteById() {
        doNothing().when(userService).deleteById(anyLong());
        final ResponseEntity<HttpStatus> response = userController.deleteById(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(userService, times(1)).deleteById(anyLong());
    }
}