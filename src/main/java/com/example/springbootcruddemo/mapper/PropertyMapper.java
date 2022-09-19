package com.example.springbootcruddemo.mapper;

import com.example.springbootcruddemo.dto.property.PropertyGetDto;
import com.example.springbootcruddemo.dto.property.PropertyPostDto;
import com.example.springbootcruddemo.model.Property;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class})
public interface PropertyMapper {
    PropertyGetDto propertyToPropertyGetDto(Property property);

    Property propertyPostDtoToProperty(PropertyPostDto propertyPostDto);
}
