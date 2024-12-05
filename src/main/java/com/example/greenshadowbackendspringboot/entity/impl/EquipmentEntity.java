package com.example.greenshadowbackendspringboot.entity.impl;

import com.example.greenshadowbackendspringboot.entity.SuperEntity;
import com.example.greenshadowbackendspringboot.util.EquipmentStatus;
import com.example.greenshadowbackendspringboot.util.EquipmentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EquipmentEntity implements SuperEntity {
    @Id
    String EquipmentId;
    String name;
    @Enumerated(EnumType.STRING)
    EquipmentType equipmentType;
    @Enumerated(EnumType.STRING)
    EquipmentStatus status;

    @ManyToOne
    @JoinColumn(name = "id")
    StaffEntity staff;

    @ManyToOne
    @JoinColumn(name = "field_code")
    FieldEntity field;
}
