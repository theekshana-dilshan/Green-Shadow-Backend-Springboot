package com.example.greenshadowbackendspringboot.service.impl;

import com.example.greenshadowbackendspringboot.customStatusCodes.SelectedErrorStatus;
import com.example.greenshadowbackendspringboot.dao.UserDao;
import com.example.greenshadowbackendspringboot.dto.UserStatus;
import com.example.greenshadowbackendspringboot.dto.impl.UserDTO;
import com.example.greenshadowbackendspringboot.entity.impl.UserEntity;
import com.example.greenshadowbackendspringboot.exception.DataPersistException;
import com.example.greenshadowbackendspringboot.exception.UserNotFoundException;
import com.example.greenshadowbackendspringboot.service.UserService;
import com.example.greenshadowbackendspringboot.util.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveUser(UserDTO userDTO) {
        UserEntity savedUser =
                userDao.save(mapping.toUserEntity(userDTO));
        if (savedUser == null) {
            throw new DataPersistException("User not saved");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> allUsers = userDao.findAll();
        return mapping.asUserDTOList(allUsers);
    }

    @Override
    public UserStatus getUser(String email) {
        if(userDao.existsByEmail(email)){
            UserEntity selectedUser = userDao.getReferenceById(email);
            return mapping.toUserDTO(selectedUser);
        }else {
            return new SelectedErrorStatus(2, "User with id " + email + " not found");
        }
    }

    @Override
    public void deleteUser(String email) {
        Optional<UserEntity> existsUser = userDao.findByEmail(email);
        if (!existsUser.isPresent()){
            throw new UserNotFoundException("User with id " + email + " not found");
        }else {
            userDao.deleteByEmail(email);
        }
    }

    @Override
    public void updateUser(String email, UserDTO userDTO) {
        Optional<UserEntity> tempUser = userDao.findByEmail(email);
        if (tempUser.isPresent()){
            tempUser.get().setEmail(userDTO.getEmail());
            tempUser.get().setPassword(userDTO.getPassword());
            tempUser.get().setRole(userDTO.getRole());
        }
    }

    @Override
    public UserDetailsService userDetailService() {
        return    userName-> userDao.findByEmail(userName).orElseThrow(()-> new UserNotFoundException("User not found"));
    }
}
