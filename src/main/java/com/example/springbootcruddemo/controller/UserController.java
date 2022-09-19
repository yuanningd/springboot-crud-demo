package com.example.springbootcruddemo.controller;

import com.example.springbootcruddemo.dto.user.UserGetDto;
import com.example.springbootcruddemo.dto.user.UserPatchDto;
import com.example.springbootcruddemo.dto.user.UserPostDto;
import com.example.springbootcruddemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserGetDto create(@Valid @RequestBody UserPostDto userPostDto) {
        return userService.create(userPostDto);
    }

    @GetMapping("/{userId}")
    public UserGetDto get(@PathVariable Long userId) {
        return userService.get(userId);
    }

    @PatchMapping("/{userId}")
    public UserGetDto update(@Valid @RequestBody UserPatchDto userPatchDto, @PathVariable Long userId) {
        return userService.update(userPatchDto, userId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }
}
