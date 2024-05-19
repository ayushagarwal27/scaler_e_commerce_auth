package org.ayush.e_commerce_auth.services;

import org.ayush.e_commerce_auth.dtos.AuthRequestLoginDTO;
import org.ayush.e_commerce_auth.dtos.AuthRequestValidateDto;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Service
public class SingleAuthService implements AuthService {
    private final String email = "example.com";
    private final String password = "example";
    private final String token = "";

    private String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    @Override
    public String login(AuthRequestLoginDTO authRequestLoginDTO) {
        if (Objects.equals(authRequestLoginDTO.getEmail(), email) && Objects.equals(authRequestLoginDTO.getPassword(), password)) {
            return getSaltString();
        }
        return "Can't log in";
    }

    @Override
    public String validate(AuthRequestValidateDto authRequestValidateDto) {
        return "Success!";
    }
}
