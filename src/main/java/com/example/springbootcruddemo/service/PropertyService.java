package com.example.springbootcruddemo.service;

import com.example.springbootcruddemo.dto.property.PropertyGetDto;
import com.example.springbootcruddemo.dto.property.PropertyPostDto;
import com.example.springbootcruddemo.mapper.PropertyMapper;
import com.example.springbootcruddemo.model.Property;
import com.example.springbootcruddemo.repository.PropertyRepository;
import org.springframework.stereotype.Service;

@Service
public record PropertyService(PropertyRepository propertyRepository, PropertyMapper propertyMapper, UserService userService) {
    public PropertyGetDto create(PropertyPostDto propertyPostDto) {
        Property property = propertyMapper.propertyPostDtoToProperty(propertyPostDto);
        property.setUser(userService.find(propertyPostDto.getUserId()));

        return propertyMapper.propertyToPropertyGetDto(propertyRepository.save(property));
    }
}
