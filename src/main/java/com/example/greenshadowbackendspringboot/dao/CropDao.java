package com.example.greenshadowbackendspringboot.dao;

import com.example.greenshadowbackendspringboot.entity.impl.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropDao extends JpaRepository<CropEntity, String> {
}
