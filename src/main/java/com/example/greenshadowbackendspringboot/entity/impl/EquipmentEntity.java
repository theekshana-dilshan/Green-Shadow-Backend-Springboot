package com.example.greenshadowbackendspringboot.entity.impl;

import com.example.greenshadowbackendspringboot.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "equipment")
public class EquipmentEntity implements SuperEntity {

    @Id
    private String EquipmentId;
    private String name;
    private String equipmentType;

    private String status;

    @ManyToOne
    @JoinColumn(name = "id")
    StaffEntity staff;

    @ManyToOne
    @JoinColumn(name = "fieldCode")
    FieldEntity field;

}
