package com.innowise.airline.service;

import com.innowise.airline.dto.request.UserRequestDto;
import com.innowise.airline.dto.response.UserResponseDto;
import com.innowise.airline.exception.IsNotExistException;
import com.innowise.airline.mapper.UserMapper;
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

    @Override
    public User getById(Long id) {
        if (userRepository.existsById(id)) {
            return userRepository.getById(id);
        }
        return null;
    }

    @Override
    public User getByEmail(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new IsNotExistException("user is not exist", "getByEmail");
        }
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findUsersByDeletedFalse();
    }

    @Transactional
    @Override
    public User updateById(User user, Long id) {
        if (!userRepository.existsById(id)) {
            throw new IsNotExistException("user is not exist", "update");
        }
        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
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
