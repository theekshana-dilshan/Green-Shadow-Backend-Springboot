package com.example.greenshadowbackendspringboot.dao;

import com.example.greenshadowbackendspringboot.entity.impl.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentDao extends JpaRepository<EquipmentEntity, String> {
}
