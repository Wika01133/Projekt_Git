package com.example.demo.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testUpdateUser() {
        // Arrange
        Long userId = 1L;
        User existingUser = new User("existingUser", "password");
        User updatedUserData = new User("updatedUser", "newPassword");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUserData);

        // Act
        User updatedUser = userService.updateUser(userId, updatedUserData);

        // Assert
        assertNotNull(updatedUser);
        assertEquals(updatedUserData.getUsername(), updatedUser.getUsername());
        assertEquals(updatedUserData.getPassword(), updatedUser.getPassword());
    }
}
