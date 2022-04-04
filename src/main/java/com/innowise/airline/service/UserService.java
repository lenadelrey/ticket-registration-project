package com.innowise.airline.service;

import com.innowise.airline.dto.request.UserRequestDto;
import com.innowise.airline.dto.response.UserResponseDto;
import com.innowise.airline.model.User;

import java.util.List;

public interface UserService {

    User getById(Long id);

    User getByEmail(String email);

    List<User> getAll();

    User updateById(User user, Long id);

    boolean deleteById(Long id);

}
