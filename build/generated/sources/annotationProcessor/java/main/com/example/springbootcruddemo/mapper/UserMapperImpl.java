package com.example.springbootcruddemo.mapper;

import com.example.springbootcruddemo.dto.user.UserGetDto;
import com.example.springbootcruddemo.dto.user.UserPostDto;
import com.example.springbootcruddemo.model.User;
import java.time.OffsetDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-19T13:32:09+1000",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.4 (Azul Systems, Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userPostDtoToUser(UserPostDto userPostDto) {
        if ( userPostDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( userPostDto.getEmail() );
        user.name( userPostDto.getName() );
        user.password( userPostDto.getPassword() );

        return user.build();
    }

    @Override
    public UserGetDto userToUserGetDto(User user) {
        if ( user == null ) {
            return null;
        }

        long id = 0L;
        String name = null;
        String email = null;
        OffsetDateTime createdTime = null;
        OffsetDateTime updatedTime = null;

        if ( user.getId() != null ) {
            id = user.getId();
        }
        name = user.getName();
        email = user.getEmail();
        createdTime = user.getCreatedTime();
        updatedTime = user.getUpdatedTime();

        UserGetDto userGetDto = new UserGetDto( id, name, email, createdTime, updatedTime );

        return userGetDto;
    }
}
