package com.example.greenshadowbackendspringboot.dao;

import com.example.greenshadowbackendspringboot.entity.impl.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldDao extends JpaRepository<FieldEntity, String> {
}
