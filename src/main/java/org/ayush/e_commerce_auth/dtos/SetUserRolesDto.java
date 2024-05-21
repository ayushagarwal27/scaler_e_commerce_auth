package org.ayush.e_commerce_auth.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SetUserRolesDto {
    private List<Long> roleIds;
}
