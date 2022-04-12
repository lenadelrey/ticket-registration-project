package com.innowise.airline.controller.auth;

import com.innowise.airline.dto.request.UserRequest;
import com.innowise.airline.dto.response.UserDto;
import com.innowise.airline.mapper.UserMapper;
import com.innowise.airline.model.User;
import com.innowise.airline.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reg")
public class RegistrationController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserDto> registration(@RequestBody @Valid UserRequest userRequest) {
        User user = userMapper.mapUserRequestToUser(userRequest);
        return new ResponseEntity<>(userMapper.mapUserToUserDto(userService.create(user)), HttpStatus.CREATED);
    }

}
