package com.example.projectmanagement.usermanagement.dto;

import com.example.projectmanagement.usermanagement.enumeration.Permission;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
public class RoleDTO {
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Set<Permission> permissions;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_date", nullable = false, updatable = false)
    private Date createdDate;
}
