package org.ayush.e_commerce_auth.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private UserDto userDto;
    private String token;
}
