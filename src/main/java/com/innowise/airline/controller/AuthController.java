package com.innowise.airline.controller;

import com.innowise.airline.dto.request.AuthRequest;
import com.innowise.airline.dto.request.UserRequest;
import com.innowise.airline.dto.response.AuthDto;
import com.innowise.airline.dto.response.UserDto;
import com.innowise.airline.mapper.UserMapper;
import com.innowise.airline.model.User;
import com.innowise.airline.service.AuthService;
import com.innowise.airline.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<AuthDto> login(@RequestBody @Valid AuthRequest authRequest) {
        AuthDto token = authService.login(authRequest);

        return ResponseEntity.ok(token);
    }

    @PutMapping
    public ResponseEntity<UserDto> registration(@RequestBody @Valid UserRequest userRequest) {
        User user = userMapper.mapUserRequestToUser(userRequest);

        return new ResponseEntity<>(userMapper.mapUserToUserDto(userService.create(user)), HttpStatus.CREATED);
    }
}