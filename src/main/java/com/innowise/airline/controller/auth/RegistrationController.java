package com.innowise.airline.controller.auth;

import com.innowise.airline.dto.request.UserRequestDto;
import com.innowise.airline.dto.response.UserResponseDto;
import com.innowise.airline.mapper.UserMapper;
import com.innowise.airline.model.User;
import com.innowise.airline.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reg")
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody @Valid UserRequestDto userRequestDto) {
        User user = UserMapper.mapUserRequestDtoToUser(userRequestDto);
        return new ResponseEntity<>(UserMapper.mapUserToUserResponseDto(registrationService.create(user)), HttpStatus.OK);
    }

}
