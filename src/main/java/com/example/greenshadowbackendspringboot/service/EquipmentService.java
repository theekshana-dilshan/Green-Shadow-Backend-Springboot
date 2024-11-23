package com.example.greenshadowbackendspringboot.service;

import com.example.greenshadowbackendspringboot.dto.EquipmentStatus;
import com.example.greenshadowbackendspringboot.dto.impl.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);
    List<EquipmentDTO> getAllEquipments();
    EquipmentStatus getEquipment(String equipmentId);
    void deleteEquipment(String equipmentId);
    void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO);
}
