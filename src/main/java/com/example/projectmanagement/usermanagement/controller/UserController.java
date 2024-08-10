package com.example.projectmanagement.usermanagement.controller;

import com.example.projectmanagement.common.dto.GenericResponse;
import com.example.projectmanagement.common.enumeration.MessageEnum;
import com.example.projectmanagement.identityservice.request.SignInRequest;
import com.example.projectmanagement.usermanagement.dto.AuthenticationResponseDTO;
import com.example.projectmanagement.usermanagement.dto.UserDTO;
import com.example.projectmanagement.usermanagement.request.UserRequest;
import com.example.projectmanagement.usermanagement.service.AuthenticationServiceImpl;
import com.example.projectmanagement.usermanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/user")
@RestController
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final AuthenticationServiceImpl authenticationService;

    @GetMapping("/getAll")
    public ResponseEntity<GenericResponse<List<UserDTO>>> getAllUsers() {
        GenericResponse<List<UserDTO>> response = GenericResponse
                .<List<UserDTO>>builder()
                .isSuccess(true)
                .message(MessageEnum.SUCCESS)
                .data(userService.getAllUser())
                .build();

        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<GenericResponse<UserDTO>> getUser(@RequestParam String username) {
        GenericResponse<UserDTO> response = GenericResponse
                .<UserDTO>builder()
                .isSuccess(true)
                .message(MessageEnum.SUCCESS)
                .data(userService.getUserByUsername(username))
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<GenericResponse<UserDTO>> createUser(@RequestBody @Valid UserRequest userRequest) {
        GenericResponse<UserDTO> response = GenericResponse
                .<UserDTO>builder()
                .isSuccess(true)
                .message(MessageEnum.SUCCESS)
                .data(userService.createUser(userRequest))
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signIn")
    public ResponseEntity<GenericResponse<AuthenticationResponseDTO>> signIn(@RequestBody SignInRequest signInRequest) {
        AuthenticationResponseDTO authenticationResponseDTO = authenticationService.signIn(signInRequest);
        GenericResponse<AuthenticationResponseDTO> response = GenericResponse
                .<AuthenticationResponseDTO>builder()
                .data(authenticationResponseDTO)
                .message(MessageEnum.SUCCESS)
                .build();

        return ResponseEntity.ok(response);

    }

    @PostMapping("/{userId}/assign-role")
    public ResponseEntity<GenericResponse<UserDTO>> assignRole(
            @PathVariable Long userId, @RequestParam Long roleId) {
        GenericResponse<UserDTO> response = GenericResponse
                .<UserDTO>builder()
                .isSuccess(true)
                .message(MessageEnum.SUCCESS)
                .data(userService.assignRoleToUser(userId, roleId))
                .build();
        return ResponseEntity.ok(response);
    }
}
