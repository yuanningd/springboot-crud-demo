package com.example.springbootcruddemo.service;

import com.example.springbootcruddemo.dto.user.UserGetDto;
import com.example.springbootcruddemo.dto.user.UserPostDto;
import com.example.springbootcruddemo.exception.ResourceNotFoundException;
import com.example.springbootcruddemo.mapper.UserMapper;
import com.example.springbootcruddemo.model.User;
import com.example.springbootcruddemo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/** This is a unit test
 * Comments in this class is used to help you understand how to write a unit test, you do not need these in your real project
 * **/
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    // generate fake dependency class
    @Mock
    private UserRepository userRepository;

    // generate fake dependency class
    @Mock
    private UserMapper userMapper;

    /** Here is the same as
     * private UserService = new UserService(userRepository, userMapper);
     * **/
    @InjectMocks
    private UserService userService;

    private final UserPostDto mockUserPostDto = new UserPostDto("s", "s@gmail.com", "password");
    private final User mockUser = User.builder().name("s").email("s@gmail.com").password("password").build();
    private final UserGetDto mockUserGetDto = new UserGetDto(1L, "s", "s@gmail.com", OffsetDateTime.now(), OffsetDateTime.now());

    @Test
    void shouldSaveNewUserInUserRepoWhenCreateUser() {
        //mock interactions with dependency classes
        when(userMapper.userPostDtoToUser(mockUserPostDto)).thenReturn(mockUser);

        //call the method you'd like to test
        userService.create(mockUserPostDto);

        //assertions
        verify(userRepository).save(mockUser);
    }

    @Test
    void shouldGetUserDtoWhenGetUser() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(userMapper.userToUserGetDto(mockUser)).thenReturn(mockUserGetDto);

        UserGetDto userGetDto = userService.get(userId);

        assertEquals(userGetDto, mockUserGetDto);
    }

    // Todo: add delete method test
    // Todo: add update method test

    @Test
    void shouldThrowResourceNotFoundExceptionWhenUserIsNotFound() {
        when(userRepository.findById(1L)).thenThrow(new ResourceNotFoundException());

        assertThrows(ResourceNotFoundException.class, () -> userService.find(1L));
    }

}
