package org.ayush.e_commerce_auth.services;

import org.ayush.e_commerce_auth.dtos.AuthRequestValidateDto;
import org.ayush.e_commerce_auth.dtos.LoginRequestDto;
import org.ayush.e_commerce_auth.dtos.UserDto;
import org.ayush.e_commerce_auth.exceptions.UserAlreadyExistsException;
import org.ayush.e_commerce_auth.models.User;
import org.ayush.e_commerce_auth.repositories.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class SingleAuthService implements AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public SingleAuthService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

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
    public String login(LoginRequestDto authRequestLoginDTO) {
//        if (Objects.equals(authRequestLoginDTO.getEmail(), email) && Objects.equals(authRequestLoginDTO.getPassword(), password)) {
//            return getSaltString();
//        }
        return "Can't log in";
    }

    @Override
    public UserDto signUp(String email, String password) throws UserAlreadyExistsException {
        Optional<User> userExists = userRepo.findByEmail(email);
        if (!userExists.isEmpty()) {
            throw new UserAlreadyExistsException("User with email: " + email + " already exists");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        User savedUser = userRepo.save(user);

        return UserDto.from(savedUser);
    }

    @Override
    public String validate(AuthRequestValidateDto authRequestValidateDto) {
        return "Success!";
    }
}
