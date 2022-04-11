package com.innowise.airline.controller.auth;

import com.innowise.airline.dto.request.AuthRequest;
import com.innowise.airline.dto.response.AuthDto;
import com.innowise.airline.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<AuthDto> login(@RequestBody AuthRequest authRequest) {
        AuthDto token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

}