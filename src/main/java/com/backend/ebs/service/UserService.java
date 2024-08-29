package com.backend.ebs.service;

import com.backend.ebs.dto.UserDTO;
import com.backend.ebs.entity.User;
import com.backend.ebs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User savedUser = userRepository.save(user);
        BeanUtils.copyProperties(savedUser, userDTO);
        return userDTO;
    }

    public UserDTO getUserById(long id) {
        return userRepository.findById(id)
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(user, userDTO);
                    return userDTO;
                }).orElse(new UserDTO());
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(user, userDTO);
                    return userDTO;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDTO updateUser(long id, UserDTO userDTO) {
        if (userRepository.existsById(id)) {
            User user = new User();
            BeanUtils.copyProperties(user, userDTO);
            user.setId(id);
            User updatedUser = userRepository.save(user);
            BeanUtils.copyProperties(userDTO, updatedUser);
            return userDTO;
        }
        return new UserDTO();
    }


}
