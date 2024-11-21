package com.example.greenshadowbackendspringboot.dao;

import com.example.greenshadowbackendspringboot.entity.impl.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDao extends JpaRepository<VehicleEntity, String> {
}
