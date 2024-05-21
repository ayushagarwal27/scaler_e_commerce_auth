package org.ayush.e_commerce_auth.services;

import org.ayush.e_commerce_auth.dtos.CreateRoleDto;
import org.ayush.e_commerce_auth.models.Role;

public interface RoleService {

    Role createRole(CreateRoleDto createRoleDto);
}
