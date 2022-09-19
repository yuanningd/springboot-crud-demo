package com.example.springbootcruddemo.dto.property;

import com.example.springbootcruddemo.constant.PropertyType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PropertyPostDto {
    @NotNull
    private long userId;
    private Integer landSize;
    private PropertyType type;
}
