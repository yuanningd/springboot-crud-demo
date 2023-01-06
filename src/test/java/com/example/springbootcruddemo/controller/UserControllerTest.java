package com.example.springbootcruddemo.controller;

import com.example.springbootcruddemo.constant.PropertyType;
import com.example.springbootcruddemo.dto.property.PropertyPostDto;
import com.example.springbootcruddemo.dto.user.UserPatchDto;
import com.example.springbootcruddemo.dto.user.UserPostDto;
import com.example.springbootcruddemo.service.PropertyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** This test is an integration test **/
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserController userController;

    @Autowired
    private PropertyService propertyService;

    private Long mockUserId;

    @BeforeEach
    void setUp() {
        mockUserId = userController.create(new UserPostDto("steven", "a@gmail.com", "passwordpassword8")).getId();
        propertyService.create(new PropertyPostDto(mockUserId, 55, PropertyType.HOUSE));
        propertyService.create(new PropertyPostDto(mockUserId, 55, PropertyType.HOUSE));
    }

    @Test
    void shouldReturn201AndUserDtoWhenUserIsCreated() throws Exception {
        UserPostDto user = new UserPostDto("b", "b@gmail.com", "passwordpassword8");

        mockMvc.perform(post("/users")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("b@gmail.com"))
        ;
    }

    @Test
    void shouldReturn200AndUserDtoWhenUserIsUpdated() throws Exception {
        UserPatchDto userPatchDto = new UserPatchDto("a");
        mockMvc.perform(patch("/users/" + mockUserId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userPatchDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("a"))
        ;
    }

    @Test
    void shouldReturn200AndUserDtoWhenGetUserDto() throws Exception {
        mockMvc.perform(get("/users/" + mockUserId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockUserId.toString()));
    }

    @Test
    void shouldReturn20AndUserDtoWhenGetUserDto() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    // Todo: Add delete method test
}
