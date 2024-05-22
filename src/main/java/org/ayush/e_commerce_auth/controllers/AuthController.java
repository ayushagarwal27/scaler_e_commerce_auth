package org.ayush.e_commerce_auth.controllers;

import org.ayush.e_commerce_auth.dtos.AuthRequestValidateDto;
import org.ayush.e_commerce_auth.dtos.LoginRequestDto;
import org.ayush.e_commerce_auth.dtos.SignUpRequestDto;
import org.ayush.e_commerce_auth.dtos.UserDto;
import org.ayush.e_commerce_auth.exceptions.UserAlreadyExistsException;
import org.ayush.e_commerce_auth.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    AuthService authService;

    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) throws UserAlreadyExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signUp(signUpRequestDto.getEmail(), signUpRequestDto.getPassword()));
    }


    @PostMapping("/validate")
    public String validate(@RequestBody AuthRequestValidateDto authRequestValidateDto) {
        return authService.validate(authRequestValidateDto);
    }
}
