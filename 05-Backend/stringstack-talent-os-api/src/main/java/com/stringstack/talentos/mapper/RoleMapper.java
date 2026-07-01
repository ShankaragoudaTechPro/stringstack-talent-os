package com.stringstack.talentos.mapper;



import com.stringstack.talentos.dto.RoleRequest;
import com.stringstack.talentos.dto.RoleResponse;
import com.stringstack.talentos.entity.Role;

public class RoleMapper {

    public static Role toEntity(RoleRequest request) {

        Role role = new Role();

        role.setRoleName(request.getRoleName());
        role.setDescription(request.getDescription());
        role.setActive(request.getActive());

        return role;
    }

    public static RoleResponse toResponse(Role role) {

        return RoleResponse.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .description(role.getDescription())
                .active(role.getActive())
                .createdAt(role.getCreatedAt())
                .updatedAt(role.getUpdatedAt())
                .build();
    }
}