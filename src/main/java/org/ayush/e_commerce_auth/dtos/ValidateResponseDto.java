package org.ayush.e_commerce_auth.dtos;

import lombok.Getter;
import lombok.Setter;
import org.ayush.e_commerce_auth.models.SessionStatus;

@Getter
@Setter
public class ValidateResponseDto {
    private UserDto userDto;
    private SessionStatus sessionStatus;
}
