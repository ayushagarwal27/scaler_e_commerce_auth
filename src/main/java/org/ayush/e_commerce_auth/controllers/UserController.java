package org.ayush.e_commerce_auth.controllers;

import org.ayush.e_commerce_auth.dtos.SetUserRolesDto;
import org.ayush.e_commerce_auth.dtos.UserDto;
import org.ayush.e_commerce_auth.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable Long userId) {
        UserDto userDetails = userService.getUserDetails(userId);
        return ResponseEntity.ok(userDetails);
    }

    @PostMapping("{userId}/roles")
    public ResponseEntity<UserDto> setUserRoles(@PathVariable Long userId, @RequestBody SetUserRolesDto userRoleIds) {
        UserDto savedUser = userService.setUserRoles(userId, userRoleIds);
        return ResponseEntity.ok(savedUser);
    }

}
