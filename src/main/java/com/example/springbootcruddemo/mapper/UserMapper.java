package com.example.springbootcruddemo.mapper;

import com.example.springbootcruddemo.dto.user.UserGetDto;
import com.example.springbootcruddemo.dto.user.UserPostDto;
import com.example.springbootcruddemo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User userPostDtoToUser(UserPostDto userPostDto);

    UserGetDto userToUserGetDto(User user);
}
