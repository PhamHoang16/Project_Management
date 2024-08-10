package com.example.projectmanagement.usermanagement.mapper;

import com.example.projectmanagement.usermanagement.dto.RoleDTO;
import com.example.projectmanagement.usermanagement.entity.Role;
import com.example.projectmanagement.usermanagement.request.RoleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO requestToDto(RoleRequest roleRequest);

    Role toEntity(RoleDTO roleDTO);

    @Mapping(target = "createdDate", ignore = true)
    Role requestToEntity(RoleRequest roleRequest);

    @Mapping(target = "createdDate", source = "createdDate", dateFormat = "dd-MM-yyyy")
    @Mapping(target = "id", source = "id")
    RoleDTO entityToDto(Role role);
}
