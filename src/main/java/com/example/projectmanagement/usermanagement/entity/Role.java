package com.example.projectmanagement.usermanagement.entity;

import com.example.projectmanagement.common.entity.BaseEntity;
import com.example.projectmanagement.usermanagement.enumeration.Permission;
import com.example.projectmanagement.usermanagement.enumeration.RoleName;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity {
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @Enumerated(EnumType.STRING)
    private Set<Permission> permissions;

    @OneToMany(mappedBy = "role")
    public Set<User> users;
}
