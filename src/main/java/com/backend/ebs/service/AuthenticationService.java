package com.backend.ebs.service;

import com.backend.ebs.dto.UserDTO;
import com.backend.ebs.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final SecurityUserService securityUserService;
    private final JwtTokenProvider jwtTokenProvider;

    void authenticateUser(String email, String password) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

    public String generateToken(UserDTO userDTO) throws AuthenticationException{

        authenticateUser(userDTO.getEmail(), userDTO.getPassword());
        return jwtTokenProvider.generateToken(securityUserService.loadUserByUsername(userDTO.getEmail()));
    }
}
