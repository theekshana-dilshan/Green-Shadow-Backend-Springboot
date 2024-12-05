package com.example.greenshadowbackendspringboot.dto.impl;

import com.example.greenshadowbackendspringboot.dto.CustomStatus;
import com.example.greenshadowbackendspringboot.util.EquipmentStatus;
import com.example.greenshadowbackendspringboot.util.EquipmentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDTO implements CustomStatus {
    String EquipmentId;
    String name;
    @Enumerated(EnumType.STRING)
    EquipmentType equipmentType;
    @Enumerated(EnumType.STRING)
    EquipmentStatus status;
    StaffDTO staff;
    FieldDTO field;
}
