package com.example.springbootcruddemo.service;

import com.example.springbootcruddemo.dto.user.UserGetDto;
import com.example.springbootcruddemo.dto.user.UserPatchDto;
import com.example.springbootcruddemo.dto.user.UserPostDto;
import com.example.springbootcruddemo.exception.ResourceNotFoundException;
import com.example.springbootcruddemo.mapper.UserMapper;
import com.example.springbootcruddemo.model.User;
import com.example.springbootcruddemo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public record UserService(UserRepository userRepository, UserMapper userMapper) {

    private static final String RESOURCE = "User";

    public UserGetDto create(UserPostDto userPostDto) {
        return userMapper.userToUserGetDto(userRepository.save(userMapper.userPostDtoToUser(userPostDto)));
    }

    public UserGetDto get(Long userId) {
        return userMapper.userToUserGetDto(find(userId));
    }

    public UserGetDto update(UserPatchDto userPatchDto, Long userId) {
        User user = find(userId);
        user.setName(userPatchDto.getName());
        return userMapper.userToUserGetDto(userRepository.save(user));
    }

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    public User find(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(RESOURCE, userId));
    }
}
