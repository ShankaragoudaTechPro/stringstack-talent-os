package com.stringstack.talentos.dto.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String roleName;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}