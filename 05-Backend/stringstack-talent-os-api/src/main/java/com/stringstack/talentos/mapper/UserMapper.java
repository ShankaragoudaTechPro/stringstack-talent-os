package com.stringstack.talentos.mapper;

import com.stringstack.talentos.dto.user.UserResponse;
import com.stringstack.talentos.entity.User;

public class UserMapper {

    public static UserResponse toResponse(User user){

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .roleName(user.getRole().getRoleName())
                .active(user.getActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}