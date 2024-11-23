package com.example.greenshadowbackendspringboot.service;


import com.example.greenshadowbackendspringboot.dto.UserStatus;
import com.example.greenshadowbackendspringboot.dto.impl.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserStatus getUser(String email);
    void deleteUser(String email);
    void updateUser(String email, UserDTO userDTO);
    UserDetailsService userDetailService();
}
