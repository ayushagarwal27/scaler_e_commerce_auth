package org.ayush.e_commerce_auth.services;

import org.ayush.e_commerce_auth.dtos.AuthRequestLoginDTO;
import org.ayush.e_commerce_auth.dtos.AuthRequestValidateDto;

public interface AuthService {
    String login(AuthRequestLoginDTO authRequestLoginDTO);

    String validate(AuthRequestValidateDto authRequestValidateDto);
}
