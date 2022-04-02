package com.innowise.airline.service;

import com.innowise.airline.dto.request.UserRequestDto;
import com.innowise.airline.dto.response.UserResponseDto;
import com.innowise.airline.model.User;

import java.util.List;

public interface UserService {

    UserResponseDto create(UserRequestDto user);

    UserResponseDto getById(Long id);

    UserResponseDto getByEmail(String email);

    List<UserResponseDto> getAll();

    UserResponseDto updateById(UserRequestDto user, Long id);

    boolean deleteById(Long id);

}
