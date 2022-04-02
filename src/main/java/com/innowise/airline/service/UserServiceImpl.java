package com.innowise.airline.service;

import com.innowise.airline.dto.request.UserRequestDto;
import com.innowise.airline.dto.response.UserResponseDto;
import com.innowise.airline.exception.IsNotExistException;
import com.innowise.airline.mapper.UserMapper;
import com.innowise.airline.model.Role;
import com.innowise.airline.model.User;
import com.innowise.airline.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
        User user = UserMapper.mapUserRequestDtoToUser(userRequestDto);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserMapper.mapUserToUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto getById(Long id) {
        if (userRepository.existsById(id)) {
            return UserMapper.mapUserToUserResponseDto(userRepository.getById(id));
        }
        return null;
    }

    @Override
    public UserResponseDto getByEmail(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new IsNotExistException("user is not exist", "getByEmail");
        }
        return UserMapper.mapUserToUserResponseDto(userRepository.findByEmail(email));
    }

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findUsersByDeletedFalse()
                .stream()
                .map(UserMapper::mapUserToUserResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserResponseDto updateById(UserRequestDto userRequestDto, Long id) {
        if (!userRepository.existsById(id)) {
            throw new IsNotExistException("user is not exist", "update");
        }
        User user = UserMapper.mapUserRequestDtoToUser(userRequestDto);
        user.setId(id);
        return UserMapper.mapUserToUserResponseDto(userRepository.save(user));
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IsNotExistException("user is not exist", "delete");
        }
            User deleted = userRepository.getById(id);
            deleted.setDeleted(true);
            return true;
    }
}
