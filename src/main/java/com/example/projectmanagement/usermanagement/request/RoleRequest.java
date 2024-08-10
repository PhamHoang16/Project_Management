package com.example.projectmanagement.usermanagement.request;

import com.example.projectmanagement.usermanagement.enumeration.Permission;
import com.example.projectmanagement.usermanagement.enumeration.RoleName;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class RoleRequest {
    @NotBlank
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @Enumerated(EnumType.STRING)
    private Set<Permission> permissions;
}
