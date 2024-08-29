package com.backend.ebs.controller;

import com.backend.ebs.dto.GenericResponse;
import com.backend.ebs.dto.JwtTokenDTO;
import com.backend.ebs.dto.UserDTO;
import com.backend.ebs.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<GenericResponse<?>> createUser(@RequestBody UserDTO userDTO) {
        if (Objects.isNull(userDTO) || Objects.isNull(userDTO.getEmail()) || Objects.isNull(userDTO.getPassword())) {
            return ResponseEntity.badRequest()
                    .body(new GenericResponse<>(false, "Invalid input: Email and Password are required", null));
        }

        try {
            String token = authenticationService.generateToken(userDTO);
            return new ResponseEntity<>(
                    new GenericResponse<>(true, "Logged in successfully", new JwtTokenDTO(token, LocalDateTime.now().plusHours(1))),
                    HttpStatus.OK
            );
        } catch (AuthenticationException ex) {
            return new ResponseEntity<>(new GenericResponse<>(false, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
        }


    }
}
