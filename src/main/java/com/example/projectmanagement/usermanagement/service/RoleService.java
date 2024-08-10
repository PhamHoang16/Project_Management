package com.example.projectmanagement.usermanagement.service;

import com.example.projectmanagement.usermanagement.dto.RoleDTO;
import com.example.projectmanagement.usermanagement.enumeration.Permission;
import com.example.projectmanagement.usermanagement.request.RoleRequest;

import java.util.List;
import java.util.Set;

public interface RoleService {
    /**
     * Create a new role
     *
     * @param roleRequest role requestq
     * @return role dto
     */
    RoleDTO createRole(RoleRequest roleRequest);

    /**
     * Add new permissions to a role
     *
     * @param roleId         role id
     * @param permissionList list of permissions
     * @return role dto
     */
    RoleDTO addPermissionToRole(Long roleId, Set<Permission> permissionList);

    List<RoleDTO> getAllRoles();

}
