package com.innowise.airline.mapper;

import com.innowise.airline.dto.request.UserRequest;
import com.innowise.airline.dto.response.UserDto;
import com.innowise.airline.model.User;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapUserRequestToUser(UserRequest userRequest);

    UserDto mapUserToUserDto(User user);

    default Page<UserDto> mapPageUserToPageUserDto(Page<User> users) {
        return users.map(this::mapUserToUserDto);
    }

}
