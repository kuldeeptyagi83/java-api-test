package com.example.live.service;

import com.example.live.model.User;
import com.example.live.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// register the Mockito extension, which enables the use of Mockito annotations
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Spy
    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());

        // Use the ArgumentCaptor to capture the argument passed to the findById method
        verify(userRepository).findById(idCaptor.capture());
        // Verify that the argument passed to the findById method is 1L
        assertEquals(1L, idCaptor.getValue());

    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("John Doe");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Jane Doe");

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setName("John Doe");

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    public void testCreateUserWithSpy() {
        User user = new User();
        user.setName("John Doe");

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());

        verify(userRepository).save(user);

        // This verification will fail if userService is not a spy
        verify(userService, times(1)).createUser(user);
    }

    @Test
    public void testUpdateUser() {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("John Doe");

        User updatedUser = new User();
        updatedUser.setName("Jane Doe");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User result = userService.updateUser(1L, updatedUser);

        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
    }

    @Test
    public void testUpdateUserWithDoReturn() {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("John Doe");

        User updatedUser = new User();
        updatedUser.setName("Jane Doe");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        doReturn(existingUser).when(userRepository).save(existingUser);

        User result = userService.updateUser(1L, updatedUser);

        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        // do not call actual deleteById method but return void
        doNothing().when(userRepository).deleteById(1L);

        String result = userService.deleteUser(1L);

        assertEquals("User deleted successfully", result);
    }

    @Test
    public void testDeleteUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        String result = userService.deleteUser(1L);

        assertEquals("User not found", result);
    }

    @Test
    public void testDeleteUserThrowsException() {
        doThrow(new RuntimeException("User not found")).when(userRepository).deleteById(1L);

        String result = userService.deleteUser(1L);

        assertEquals("User not found", result);
    }
}