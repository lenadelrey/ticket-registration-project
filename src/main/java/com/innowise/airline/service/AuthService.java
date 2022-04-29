package com.innowise.airline.service;

import com.innowise.airline.auth.jwt.JwtProvider;
import com.innowise.airline.dto.request.AuthRequest;
import com.innowise.airline.dto.response.AuthDto;
import com.innowise.airline.email.EmailSender;
import com.innowise.airline.model.Role;
import com.innowise.airline.model.User;
import com.innowise.airline.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.message.AuthException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;

    @Cacheable(value = "users", key = "#user.id")
    @Transactional
    public User register(User user) {
        user.setRole(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        emailSender.send(user.getEmail(), "Please, confirm your email for registration");

        return userRepository.save(user);
    }

    @SneakyThrows
    public AuthDto login(@NonNull AuthRequest authRequest) {
        User user = userService.getByEmail(authRequest.getEmail());

        if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            String accessToken = jwtProvider.generateAccessToken(user);
            String refreshToken = jwtProvider.generateRefreshToken(user);
            return new AuthDto(accessToken, refreshToken);
        } else {
            throw new AuthException("Неправильный пароль");
        }
    }

//    public AuthDto getAccessToken(@NonNull String refreshToken) {
//        if (jwtProvider.validateRefreshToken(refreshToken)) {
//            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
//            String login = claims.getSubject();
//            String saveRefreshToken = refreshStorage.get(login);
//            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
//                User user = userService.getByEmail(login);
//                String accessToken = jwtProvider.generateAccessToken(user);
//                return new AuthDto(accessToken, null);
//            }
//        }
//        return new AuthDto(null, null);
//    }

//    @SneakyThrows
//    public AuthDto refresh(@NonNull String refreshToken) {
//        if (jwtProvider.validateRefreshToken(refreshToken)) {
//            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
//            String login = claims.getSubject();
//            String saveRefreshToken = refreshStorage.get(login);
//            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
//                User user = userService.getByEmail(login);
//                String accessToken = jwtProvider.generateAccessToken(user);
//                String newRefreshToken = jwtProvider.generateRefreshToken(user);
//                refreshStorage.put(user.getEmail(), newRefreshToken);
//                return new AuthDto(accessToken, newRefreshToken);
//            }
//        }
//        throw new AuthException("Invalid jwt token");
//    }
}
