package com.innowise.airline.service;

import com.innowise.airline.dto.request.UserRequestDto;
import com.innowise.airline.dto.response.UserResponseDto;
import com.innowise.airline.mapper.UserMapper;
import com.innowise.airline.model.Role;
import com.innowise.airline.model.User;
import com.innowise.airline.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto create(UserRequestDto userRequestDto) {
        User user = UserMapper.mapUserRequestDtoToUser(userRequestDto);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserMapper.mapUserToUserResponseDto(userRepository.save(user));
    }

}
