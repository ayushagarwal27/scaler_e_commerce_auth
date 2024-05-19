package org.ayush.e_commerce_auth.controllers;

import org.ayush.e_commerce_auth.dtos.AuthRequestLoginDTO;
import org.ayush.e_commerce_auth.dtos.AuthRequestValidateDto;
import org.ayush.e_commerce_auth.services.AuthService;
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
    public String login(@RequestBody AuthRequestLoginDTO authRequestLoginDTO) {
        return authService.login(authRequestLoginDTO);
    }

    @PostMapping("/validate")
    public String validate(@RequestBody AuthRequestValidateDto authRequestValidateDto) {
        return authService.validate(authRequestValidateDto);
    }
}
