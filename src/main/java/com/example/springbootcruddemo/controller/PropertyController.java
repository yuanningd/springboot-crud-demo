package com.example.springbootcruddemo.controller;

import com.example.springbootcruddemo.dto.property.PropertyGetDto;
import com.example.springbootcruddemo.dto.property.PropertyPostDto;
import com.example.springbootcruddemo.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("properties")
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyService propertyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PropertyGetDto create(@RequestBody PropertyPostDto propertyPostDto) {
        return propertyService.create(propertyPostDto);
    }
}
