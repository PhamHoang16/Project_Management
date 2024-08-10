package com.example.projectmanagement.usermanagement.controller;

import com.example.projectmanagement.common.dto.GenericResponse;
import com.example.projectmanagement.common.enumeration.MessageEnum;
import com.example.projectmanagement.usermanagement.dto.RoleDTO;
import com.example.projectmanagement.usermanagement.enumeration.Permission;
import com.example.projectmanagement.usermanagement.request.RoleRequest;
import com.example.projectmanagement.usermanagement.service.RoleServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    private final RoleServiceImpl roleService;

    public RoleController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<RoleDTO>>> getAllRoles() {
            List<RoleDTO> roleDTOList = roleService.getAllRoles();

        GenericResponse<List<RoleDTO>> response = GenericResponse
                .<List<RoleDTO>>builder()
                .isSuccess(true)
                .message(MessageEnum.SUCCESS)
                .data(roleDTOList)
                .build();
        return ResponseEntity.ok(response); }

    @PostMapping
    public ResponseEntity<GenericResponse<RoleDTO>> createRole(
            @RequestBody @Valid RoleRequest roleRequest
            ) {
        RoleDTO roleDTO = roleService.createRole(roleRequest);

        GenericResponse<RoleDTO> response = GenericResponse
                .<RoleDTO>builder()
                .isSuccess(true)
                .message(MessageEnum.SUCCESS)
                .data(roleDTO)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{roleId}/add-permission")
        public ResponseEntity<GenericResponse<RoleDTO>> addPermission(
            @PathVariable("roleId") Long roleId,
            @RequestBody @Valid Set<Permission> permissionList
            ) {
        RoleDTO roleDTO = roleService.addPermissionToRole(roleId, permissionList);

        GenericResponse<RoleDTO> response = GenericResponse
               .<RoleDTO>builder()
               .isSuccess(true)
               .message(MessageEnum.SUCCESS)
               .data(roleDTO)
               .build();
        return ResponseEntity.ok(response);
    }
}
