package org.ayush.e_commerce_auth.controllers;

import org.ayush.e_commerce_auth.dtos.*;
import org.ayush.e_commerce_auth.exceptions.IncorrectTokenException;
import org.ayush.e_commerce_auth.exceptions.PasswordIncorrectException;
import org.ayush.e_commerce_auth.exceptions.UserAlreadyExistsException;
import org.ayush.e_commerce_auth.exceptions.UserDoesNotExitsException;
import org.ayush.e_commerce_auth.models.SessionStatus;
import org.ayush.e_commerce_auth.services.AuthService;
import org.springframework.http.HttpHeaders;
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


    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) throws UserAlreadyExistsException {
        UserDto user = authService.signUp(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) throws UserDoesNotExitsException, PasswordIncorrectException {
        LoginResponseDto responseDto = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("AUTH_TOKEN", responseDto.getToken());
        return ResponseEntity.accepted().headers(httpHeaders).body(responseDto.getUserDto());
    }


    @PostMapping("/validate")
    public ResponseEntity<ValidateResponseDto> validate(@RequestBody AuthRequestValidateDto authRequestValidateDto) throws IncorrectTokenException {
        UserDto userDto = authService.validate(authRequestValidateDto.getToken());
        ValidateResponseDto validateResponseDto = new ValidateResponseDto();

        if (userDto == null) {
            validateResponseDto.setSessionStatus(SessionStatus.INVALID);
            return ResponseEntity.ok().body(validateResponseDto);
        }
        validateResponseDto.setUserDto(userDto);
        validateResponseDto.setSessionStatus(SessionStatus.ACTIVE);
        return ResponseEntity.ok().body(validateResponseDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutRequestDto) throws IncorrectTokenException {
        authService.logout(logoutRequestDto.getToken());
        return ResponseEntity.ok().build();
    }
}
