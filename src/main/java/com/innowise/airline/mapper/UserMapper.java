package com.innowise.airline.mapper;

import com.innowise.airline.dto.request.UserRequestDto;
import com.innowise.airline.dto.response.UserResponseDto;
import com.innowise.airline.model.User;

//TODO: Зачем создавать Builder, а потом его не использовать?)
public class UserMapper {

    public static User mapUserRequestDtoToUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setName(userRequestDto.getName());
        user.setDateOfBirth(userRequestDto.getDateOfBirth());
        return user;
    }

    public static UserResponseDto mapUserToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }

}
