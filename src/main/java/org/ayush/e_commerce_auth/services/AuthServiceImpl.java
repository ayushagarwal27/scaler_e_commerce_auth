package org.ayush.e_commerce_auth.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.ayush.e_commerce_auth.dtos.LoginResponseDto;
import org.ayush.e_commerce_auth.dtos.UserDto;
import org.ayush.e_commerce_auth.exceptions.IncorrectTokenException;
import org.ayush.e_commerce_auth.exceptions.PasswordIncorrectException;
import org.ayush.e_commerce_auth.exceptions.UserAlreadyExistsException;
import org.ayush.e_commerce_auth.exceptions.UserDoesNotExitsException;
import org.ayush.e_commerce_auth.models.Session;
import org.ayush.e_commerce_auth.models.SessionStatus;
import org.ayush.e_commerce_auth.models.User;
import org.ayush.e_commerce_auth.repositories.SessionRepository;
import org.ayush.e_commerce_auth.repositories.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final SessionRepository sessionRepository;
    private final SecretKey key = Jwts.SIG.HS256.key().build();

    public AuthServiceImpl(UserRepo userRepo, SessionRepository sessionRepository, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.sessionRepository = sessionRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public LoginResponseDto login(String email, String password) throws UserDoesNotExitsException, PasswordIncorrectException {
        Optional<User> userOptional = userRepo.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new UserDoesNotExitsException("User with email: " + email + " does not exits");
        }
        User user = userOptional.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordIncorrectException("Email or password is incorrect");
        }
//      Generate JWT token
        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("userID", user.getId());
        payloadMap.put("email", user.getEmail());
        payloadMap.put("roles", user.getRoles());
        String jwtToken = Jwts.builder().claims(payloadMap).signWith(key).compact();


//        Store session
        Session session = new Session();
        session.setUser(user);
        session.setToken(jwtToken);
        session.setSessionStatus(SessionStatus.ACTIVE);
        sessionRepository.save(session);

//        Login response
        LoginResponseDto responseDto = new LoginResponseDto();
        responseDto.setToken(jwtToken);
        responseDto.setUserDto(UserDto.from(user));
        return responseDto;
    }

    @Override
    public UserDto signUp(String email, String password) throws UserAlreadyExistsException {
        Optional<User> userExists = userRepo.findByEmail(email);
        if (userExists.isPresent()) {
            throw new UserAlreadyExistsException("User with email: " + email + " already exists");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        User savedUser = userRepo.save(user);

        return UserDto.from(savedUser);
    }

    @Override
    public SessionStatus validate(String token) throws IncorrectTokenException {
//        Decode token
        Claims claims;
        try {
            claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        } catch (JwtException e) {
            throw new IncorrectTokenException("Token is not correct");
        }
        Long userID = ((Number) claims.get("userID")).longValue();

//        Check session
        Session session = sessionRepository.findByTokenAndUser_Id(token, userID);
        if (session == null) {
            return SessionStatus.INVALID;
        }

        if (!session.getSessionStatus().equals(SessionStatus.ACTIVE)) {
            return SessionStatus.EXPIRED;
        }

        return SessionStatus.ACTIVE;
    }

    @Override
    public void logout(String token) throws IncorrectTokenException {
//        Decode token
        Claims claims;
        try {
            claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        } catch (JwtException e) {
            throw new IncorrectTokenException("Token is not correct");
        }

        Long userID = ((Number) claims.get("userID")).longValue();

//        Check session
        Session session = sessionRepository.findByTokenAndUser_Id(token, userID);

        if (session == null) {
            return;
        }

        session.setSessionStatus(SessionStatus.EXPIRED);
        sessionRepository.save(session);
    }
}
