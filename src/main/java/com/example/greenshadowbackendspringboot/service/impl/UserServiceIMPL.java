package com.example.greenshadowbackendspringboot.service.impl;

import com.example.greenshadowbackendspringboot.exception.DataPersistException;
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
    /*@Autowired
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
    public UserStatus getUser(String userId) {
        if(userDao.existsById(userId)){
            UserEntity selectedUser = userDao.getReferenceById(userId);
            return mapping.toUserDTO(selectedUser);
        }else {
            return new SelectedUserAndNoteErrorStatus(2, "User with id " + userId + " not found");
        }
    }

    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> existsUser = userDao.findById(userId);
        if (!existsUser.isPresent()){
            throw new UserNotFoundException("User with id " + userId + " not found");
        }else {
            userDao.deleteById(userId);
        }
    }

    @Override
    public void updateUser(String userId, UserDTO userDTO) {
        Optional<UserEntity> tempUser = userDao.findById(userId);
        if (tempUser.isPresent()){
            tempUser.get().setFirstName(userDTO.getFirstName());
            tempUser.get().setLastName(userDTO.getLastName());
            tempUser.get().setEmail(userDTO.getEmail());
            tempUser.get().setPassword(userDTO.getPassword());
            tempUser.get().setProfilePic(userDTO.getProfilePic());
        }
    }

    @Override
    public UserDetailsService userDetailService() {
        return    userName-> userDao.findByEmail(userName).orElseThrow(()-> new UserNotFoundException("User not found"));
    }*/
}
