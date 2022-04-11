package com.innowise.airline.controller;

import com.innowise.airline.dto.request.UserRequest;
import com.innowise.airline.dto.response.UserDto;
import com.innowise.airline.mapper.UserMapper;
import com.innowise.airline.model.User;
import com.innowise.airline.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/getById/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(userMapper.mapUserToUserDto(userService.getById(id).orElseThrow()), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> getAll(@PageableDefault Pageable pageable) {
        return new ResponseEntity<>(userMapper.mapPageUserToPageUserDto(userService.getAll(pageable)), HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<UserDto> update(@Valid @RequestBody UserRequest userRequest, @PathVariable Long id) {
        User user = userMapper.mapUserRequestToUser(userRequest);
        return new ResponseEntity<>(userMapper.mapUserToUserDto(userService.updateById(user, id).orElseThrow()), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
