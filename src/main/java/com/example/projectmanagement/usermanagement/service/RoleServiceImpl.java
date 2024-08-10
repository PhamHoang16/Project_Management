package com.example.projectmanagement.usermanagement.service;

import com.example.projectmanagement.usermanagement.dto.RoleDTO;
import com.example.projectmanagement.usermanagement.entity.Role;
import com.example.projectmanagement.usermanagement.enumeration.Permission;
import com.example.projectmanagement.usermanagement.mapper.RoleMapper;
import com.example.projectmanagement.usermanagement.repository.RoleRepository;
import com.example.projectmanagement.usermanagement.request.RoleRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class RoleServiceImpl implements RoleService{
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    /**
     * Create a new role
     *
     * @param roleRequest role request
     * @return role dto
     */
    @Override
    public RoleDTO createRole(RoleRequest roleRequest) {
        Role role = roleMapper.requestToEntity(roleRequest);

        roleRepository.save(role);
        log.error(role.getCreatedDate().toString());

        return roleMapper.entityToDto(role);
    }

    /**
     * Add new permissions to a role
     *
     * @param roleId         role id
     * @param permissionList list of permissions
     * @return role dto
     */
    @Override
    public RoleDTO addPermissionToRole(Long roleId, Set<Permission> permissionList) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        Set<Permission> permissionsExisted = role.getPermissions();

        permissionList.removeAll(permissionsExisted);
        permissionsExisted.addAll(permissionList);
        role.setPermissions(permissionsExisted);
        roleRepository.save(role);

        return roleMapper.entityToDto(role);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream().map(roleMapper::entityToDto).toList();
    }
}
