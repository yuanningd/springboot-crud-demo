package com.example.springbootcruddemo.mapper;

import com.example.springbootcruddemo.dto.property.PropertyGetDto;
import com.example.springbootcruddemo.dto.property.PropertyPostDto;
import com.example.springbootcruddemo.model.Property;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-19T14:06:55+1000",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.4 (Azul Systems, Inc.)"
)
@Component
public class PropertyMapperImpl implements PropertyMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PropertyGetDto propertyToPropertyGetDto(Property property) {
        if ( property == null ) {
            return null;
        }

        PropertyGetDto propertyGetDto = new PropertyGetDto();

        if ( property.getId() != null ) {
            propertyGetDto.setId( property.getId() );
        }
        propertyGetDto.setLandSize( property.getLandSize() );
        propertyGetDto.setType( property.getType() );
        propertyGetDto.setCreatedTime( property.getCreatedTime() );
        propertyGetDto.setUpdatedTime( property.getUpdatedTime() );
        propertyGetDto.setUser( userMapper.userToUserGetDto( property.getUser() ) );

        return propertyGetDto;
    }

    @Override
    public Property propertyPostDtoToProperty(PropertyPostDto propertyPostDto) {
        if ( propertyPostDto == null ) {
            return null;
        }

        Property property = new Property();

        property.setLandSize( propertyPostDto.getLandSize() );
        property.setType( propertyPostDto.getType() );

        return property;
    }
}
