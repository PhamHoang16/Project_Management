package com.example.projectmanagement.usermanagement.dto;

import com.example.projectmanagement.usermanagement.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String id;

    private String username;

    private String email;

    private Role role;
}
