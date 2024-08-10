package com.example.projectmanagement.usermanagement.service;

import com.example.projectmanagement.identityservice.request.SignInRequest;
import com.example.projectmanagement.usermanagement.dto.UserDTO;
import com.example.projectmanagement.usermanagement.request.UserRequest;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserRequest userRequest);

    List<UserDTO> getAllUser();

    UserDTO getUserByUsername(String username);

    boolean signIn(SignInRequest signInRequest);

    /**
     * Assign a role to a user
     *
     * @param userId user id
     * @param roleId role id
     * @return user dto
     */
    UserDTO assignRoleToUser(Long userId, Long roleId);
}
