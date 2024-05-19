package org.ayush.e_commerce_auth.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestLoginDTO {
    private String email;
    private String password;
}
