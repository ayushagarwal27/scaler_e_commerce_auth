package org.ayush.e_commerce_auth.services;

import org.ayush.e_commerce_auth.dtos.AuthRequestValidateDto;
import org.ayush.e_commerce_auth.dtos.LoginRequestDto;
import org.ayush.e_commerce_auth.dtos.UserDto;
import org.ayush.e_commerce_auth.exceptions.UserAlreadyExistsException;

public interface AuthService {
    String login(LoginRequestDto authRequestLoginDTO);

    UserDto signUp(String email, String password) throws UserAlreadyExistsException;

    String validate(AuthRequestValidateDto authRequestValidateDto);
}
