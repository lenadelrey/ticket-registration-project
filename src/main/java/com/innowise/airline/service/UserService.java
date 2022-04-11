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

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IsNotExistException("getById"));
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new IsNotExistException("getByEmail"));
    }

    public Page<User> getAll(Pageable pageable) {
        return userRepository.findUsersByDeletedFalse(pageable);
    }

    @Transactional
    public User updateById(User user, Long id) {
        userRepository.findById(id).orElseThrow(() -> new IsNotExistException("update"));

        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.findById(id).ifPresent(user -> user.setDeleted(true));
    }
}
