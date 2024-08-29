package com.backend.ebs.controller;

import com.backend.ebs.dto.GenericResponse;
import com.backend.ebs.dto.UserDTO;
import com.backend.ebs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<GenericResponse<UserDTO>> createUser(@RequestBody UserDTO userDTO) {
        if (Objects.isNull(userDTO) || Objects.isNull(userDTO.getUsername()) || Objects.isNull(userDTO.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new GenericResponse<>(false, "Invalid input: Username and Email are required", null));
        }

        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(
                new GenericResponse<>(true, "User created successfully", createdUser),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<UserDTO>> getUserById(@PathVariable long id) {
        UserDTO userDTO = userService.getUserById(id);
        if (Objects.nonNull(userDTO)) {
            return ResponseEntity.ok(new GenericResponse<>(true, "User retrieved successfully", userDTO));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new GenericResponse<>(false, "User not found", null));
        }
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<UserDTO>>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(new GenericResponse<>(true, "Users retrieved successfully", users));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<UserDTO>> updateUser(@PathVariable long id, @RequestBody UserDTO userDTO) {
        if (Objects.isNull(userDTO) || Objects.isNull(userDTO.getUsername()) || Objects.isNull(userDTO.getEmail())) {
            return ResponseEntity.badRequest().body(new GenericResponse<>(false, "Invalid input: Username and Email are required", null));
        }

        UserDTO updatedUser = userService.updateUser(id, userDTO);
        if (Objects.nonNull(updatedUser)) {
            return ResponseEntity.ok(new GenericResponse<>(true, "User updated successfully", updatedUser));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericResponse<>(false, "User not found", null));
        }
    }
}
