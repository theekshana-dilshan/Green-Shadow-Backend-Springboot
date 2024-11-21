package com.example.greenshadowbackendspringboot.dao;

import com.example.greenshadowbackendspringboot.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, String> {
}
