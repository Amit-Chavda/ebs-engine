package com.backend.ebs.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private boolean active;
    private String role;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime deletedAt;
    private String deletedBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
