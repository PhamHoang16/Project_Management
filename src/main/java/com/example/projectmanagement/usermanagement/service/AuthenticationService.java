package com.example.projectmanagement.usermanagement.service;

import com.example.projectmanagement.identityservice.request.SignInRequest;
import com.example.projectmanagement.usermanagement.dto.AuthenticationResponseDTO;

public interface AuthenticationService {
    AuthenticationResponseDTO signIn(SignInRequest signInRequest);
}
