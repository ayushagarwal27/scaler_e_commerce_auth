package org.ayush.e_commerce_auth.services;

import org.ayush.e_commerce_auth.dtos.LoginResponseDto;
import org.ayush.e_commerce_auth.dtos.UserDto;
import org.ayush.e_commerce_auth.exceptions.IncorrectTokenException;
import org.ayush.e_commerce_auth.exceptions.PasswordIncorrectException;
import org.ayush.e_commerce_auth.exceptions.UserAlreadyExistsException;
import org.ayush.e_commerce_auth.exceptions.UserDoesNotExitsException;
import org.ayush.e_commerce_auth.models.SessionStatus;

public interface AuthService {
    LoginResponseDto login(String email, String password) throws UserDoesNotExitsException, PasswordIncorrectException;

    UserDto signUp(String email, String password) throws UserAlreadyExistsException;

    SessionStatus validate(String token) throws IncorrectTokenException;

    void logout(String token) throws IncorrectTokenException;
}
