package com.example.projectmanagement.usermanagement.configuration;

import com.example.projectmanagement.usermanagement.entity.User;
import com.example.projectmanagement.usermanagement.repository.UserRepository;
import com.example.projectmanagement.usermanagement.service.UserPrincipal;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserPrincipalService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found -> Username or password"+username));
        return UserPrincipal.build(user);
    }
}
