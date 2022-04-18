package com.innowise.airline.service;

import com.innowise.airline.auth.jwt.JwtProvider;
import com.innowise.airline.dto.request.AuthRequest;
import com.innowise.airline.dto.response.AuthDto;
import com.innowise.airline.model.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    //private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    public AuthDto login(@NonNull AuthRequest authRequest) {
//        TODO: не стесняйся разграничивать блоки кода пустыми строками, где необходимо. Хотя бы отдели if
        User user = userService.getByEmail(authRequest.getEmail());
        if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            String accessToken = jwtProvider.generateAccessToken(user);
            String refreshToken = jwtProvider.generateRefreshToken(user);
            //  refreshStorage.put(user.getEmail(), refreshToken);
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
