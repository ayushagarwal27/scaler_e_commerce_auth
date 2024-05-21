package org.ayush.e_commerce_auth.controllers;

import org.ayush.e_commerce_auth.dtos.CreateRoleDto;
import org.ayush.e_commerce_auth.models.Role;
import org.ayush.e_commerce_auth.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody CreateRoleDto role) {
        Role savedRole = roleService.createRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRole);
    }
}
