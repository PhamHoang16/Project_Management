package com.example.projectmanagement.usermanagement.mapper;

import com.example.projectmanagement.usermanagement.dto.UserDTO;
import com.example.projectmanagement.usermanagement.entity.User;
import com.example.projectmanagement.usermanagement.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO requestToDTO(UserRequest userRequest);

    @Mapping(target = "password", ignore = true)
    User toEntity(UserRequest userRequest);

    UserDTO toDTO(User user);
}
