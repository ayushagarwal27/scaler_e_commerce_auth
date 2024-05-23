package org.ayush.e_commerce_auth.advices;

import org.ayush.e_commerce_auth.dtos.ErrorResponseDto;
import org.ayush.e_commerce_auth.exceptions.PasswordIncorrectException;
import org.ayush.e_commerce_auth.exceptions.UserAlreadyExistsException;
import org.ayush.e_commerce_auth.exceptions.UserDoesNotExitsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> userAlreadyExists(Exception exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(errorResponseDto);
    }

    @ExceptionHandler(UserDoesNotExitsException.class)
    public ResponseEntity<ErrorResponseDto> userDoesNotExists(Exception exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(errorResponseDto);
    }

    @ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<ErrorResponseDto> passwordIncorrect(Exception exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(errorResponseDto);
    }
}
