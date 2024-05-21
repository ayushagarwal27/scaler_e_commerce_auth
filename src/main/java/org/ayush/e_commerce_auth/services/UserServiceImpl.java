package org.ayush.e_commerce_auth.services;


import org.ayush.e_commerce_auth.dtos.SetUserRolesDto;
import org.ayush.e_commerce_auth.dtos.UserDto;
import org.ayush.e_commerce_auth.models.Role;
import org.ayush.e_commerce_auth.models.User;
import org.ayush.e_commerce_auth.repositories.RoleRepo;
import org.ayush.e_commerce_auth.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }


    @Override
    public UserDto getUserDetails(Long userId) {
        Optional<User> userDetails = userRepo.findById(userId);

        if (userDetails.isEmpty()) {
            return null;
        }
        return UserDto.from(userDetails.get());
    }

    @Override
    public UserDto setUserRoles(Long userId, SetUserRolesDto userRoleIds) {
        Optional<User> userOptional = userRepo.findById(userId);
        List<Role> userRoles = roleRepo.findAllByIdIn(userRoleIds.getRoleIds());

        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();
        user.setRoles(Set.copyOf(userRoles));

        User savedUser = userRepo.save(user);

        return UserDto.from(savedUser);
    }
}
