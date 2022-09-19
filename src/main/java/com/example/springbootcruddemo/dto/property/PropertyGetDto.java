package com.example.springbootcruddemo.dto.property;

import com.example.springbootcruddemo.constant.PropertyType;
import com.example.springbootcruddemo.dto.user.UserGetDto;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class PropertyGetDto {
    private long id;
    private Integer landSize;
    private PropertyType type;
    private OffsetDateTime createdTime;
    private OffsetDateTime updatedTime;
    private UserGetDto user;
}
