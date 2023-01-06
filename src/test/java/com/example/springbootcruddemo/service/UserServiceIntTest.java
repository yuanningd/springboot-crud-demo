package com.example.springbootcruddemo.service;

import com.example.springbootcruddemo.constant.PropertyType;
import com.example.springbootcruddemo.dto.property.PropertyPostDto;
import com.example.springbootcruddemo.dto.user.UserPostDto;
import com.example.springbootcruddemo.model.Property;
import com.example.springbootcruddemo.model.User;
import com.example.springbootcruddemo.repository.PropertyRepository;
import com.example.springbootcruddemo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class UserServiceIntTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    UserService userService;
    @Autowired
    PropertyService propertyService;

    Long mockUserId;

    Long propertyId;

    @BeforeEach
    void setUp() {
        mockUserId = userService.create(new UserPostDto("steven", "a@gmail.com", "passwordpassword8")).getId();
        propertyId = propertyService.create(new PropertyPostDto(mockUserId, 55, PropertyType.HOUSE)).getId();
        propertyService.create(new PropertyPostDto(mockUserId, 55, PropertyType.HOUSE));
    }

    @Test
    void testCascade() {
        User user = User.builder().name("s").email("ss@gmail.com").password("ss").build();
        Property property = Property.builder().user(user).landSize(3).build();
        propertyRepository.save(property);
        userRepository.findByEmail("ss@gmail.com").get();
    }
}
