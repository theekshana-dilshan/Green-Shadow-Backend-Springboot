package com.example.greenshadowbackendspringboot.dao;

import com.example.greenshadowbackendspringboot.entity.impl.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogDao extends JpaRepository<LogEntity, String> {
}
