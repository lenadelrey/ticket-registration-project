package com.innowise.airline.service;

import com.innowise.airline.exception.IsNotExistException;
import com.innowise.airline.model.User;
import com.innowise.airline.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> getById(Long id) {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new IsNotExistException("getById")));

    }

    public Optional<User> getByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email).orElseThrow(() -> new IsNotExistException("getByEmail")));
    }

    public Page<User> getAll(Pageable pageable) {
        return userRepository.findUsersByDeletedFalse(pageable);
    }

    @Transactional
    public Optional<User> updateById(User user, Long id) {
        // Boolean update = Optional.of(userRepository.existsById(id)).orElseThrow(() -> new IsNotExistException("update"));
        if (!userRepository.existsById(id)) {
            throw new IsNotExistException("delete");
        }

        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return Optional.of(userRepository.save(user));
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.findById(id).ifPresent(user -> user.setDeleted(true));
    }
}
