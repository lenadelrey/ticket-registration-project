package com.innowise.airline.controller;

import com.innowise.airline.dto.request.UserRequestDto;
import com.innowise.airline.dto.response.UserResponseDto;
import com.innowise.airline.mapper.UserMapper;
import com.innowise.airline.model.User;
import com.innowise.airline.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(UserMapper.mapUserToUserResponseDto(userService.getById(id)), HttpStatus.OK);
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<UserResponseDto> getByEmail(@PathVariable String email) {
        return new ResponseEntity<>(UserMapper.mapUserToUserResponseDto(userService.getByEmail(email)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        return new ResponseEntity<>(userService.getAll()
                .stream()
                .map(UserMapper::mapUserToUserResponseDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDto> update(@Valid @RequestBody UserRequestDto userRequestDto, @PathVariable Long id) {
        User user = UserMapper.mapUserRequestDtoToUser(userRequestDto);
        return new ResponseEntity<>(UserMapper.mapUserToUserResponseDto(userService.updateById(user, id)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(userService.deleteById(id), HttpStatus.OK);
    }

}
