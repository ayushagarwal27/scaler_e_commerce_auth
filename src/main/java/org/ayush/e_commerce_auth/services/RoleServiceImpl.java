package org.ayush.e_commerce_auth.services;

import org.ayush.e_commerce_auth.dtos.CreateRoleDto;
import org.ayush.e_commerce_auth.models.Role;
import org.ayush.e_commerce_auth.repositories.RoleRepo;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Role createRole(CreateRoleDto createRoleDto) {
        Role role = new Role();
        role.setName(createRoleDto.getName());
        return roleRepo.save(role);
    }
}
