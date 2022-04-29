package com.innowise.airline.service;

import com.innowise.airline.exception.ObjectNotExistException;
import com.innowise.airline.model.User;
import com.innowise.airline.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable("users")
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotExistException("getByEmail"));
    }

    @Cacheable("users")
    public User getByEmail(String email) {
//        TODO: литералы - в константы. к слову, updateById имеет неактуальный литерал в ошибке
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotExistException("getByEmail"));
    }

    @Cacheable("users")
    public Page<User> getAll(boolean deleted, Pageable pageable) {
        return userRepository.findUsersByDeleted(deleted, pageable);
    }

    @CachePut("users")
    @Transactional
    public User update(User user, Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotExistException("update"));

        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @CacheEvict("users")
    @Transactional
    public void deleteById(Long id) {
        userRepository.findById(id)
                .ifPresent(user -> user.setDeleted(true));
    }
}
