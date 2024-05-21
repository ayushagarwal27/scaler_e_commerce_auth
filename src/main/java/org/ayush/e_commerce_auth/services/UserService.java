package org.ayush.e_commerce_auth.services;

import org.ayush.e_commerce_auth.dtos.SetUserRolesDto;
import org.ayush.e_commerce_auth.dtos.UserDto;

public interface UserService {

    UserDto getUserDetails(Long userId);

    UserDto setUserRoles(Long userId, SetUserRolesDto userRoleIds);
}
