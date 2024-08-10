package com.example.projectmanagement.usermanagement.service;

import com.example.projectmanagement.common.enumeration.MessageEnum;
import com.example.projectmanagement.identityservice.request.SignInRequest;
import com.example.projectmanagement.usermanagement.entity.Role;
import com.example.projectmanagement.usermanagement.exception.UserManagementException;
import com.example.projectmanagement.usermanagement.dto.UserDTO;
import com.example.projectmanagement.usermanagement.entity.User;
import com.example.projectmanagement.usermanagement.mapper.UserMapper;
import com.example.projectmanagement.usermanagement.repository.RoleRepository;
import com.example.projectmanagement.usermanagement.repository.UserRepository;
import com.example.projectmanagement.usermanagement.request.UserRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(UserRequest userRequest) {
        if (userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            throw new UserManagementException(MessageEnum.USER_ALREADY_EXISTS);
        }
        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        log.info(user.toString());
        userRepository.save(user);

        return userMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> getAllUser() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UserManagementException(MessageEnum.USER_NOT_FOUND));

        return userMapper.toDTO(user);
    }

    @Override
    public boolean signIn(SignInRequest signInRequest) {
        User user = userRepository.findByUsername(signInRequest.getUsername()).orElseThrow(() ->
                new UserManagementException(MessageEnum.USER_NOT_FOUND));

        return new BCryptPasswordEncoder().matches(signInRequest.getPassword(), user.getPassword());
    }

    /**
     * Assign a role to a user
     *
     * @param userId user id
     * @param roleId role id
     * @return user dto
     */
    @Override
    public UserDTO assignRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserManagementException(MessageEnum.USER_NOT_FOUND));

        Role role = roleRepository.findById(roleId).orElseThrow(() ->
                new UserManagementException(MessageEnum.ROLE_NOT_FOUND));

        user.setRole(role);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }
}
