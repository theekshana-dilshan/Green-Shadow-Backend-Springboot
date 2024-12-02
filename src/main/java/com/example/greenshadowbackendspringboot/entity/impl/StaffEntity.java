package com.example.greenshadowbackendspringboot.entity.impl;

import com.example.greenshadowbackendspringboot.dto.impl.FieldDTO;
import com.example.greenshadowbackendspringboot.dto.impl.VehicleDTO;
import com.example.greenshadowbackendspringboot.entity.Gender;
import com.example.greenshadowbackendspringboot.entity.Role;
import com.example.greenshadowbackendspringboot.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class StaffEntity implements SuperEntity {
    @Id
    String id;
    String firstName;
    String lastName;
    String designation;
    @Enumerated(EnumType.STRING)
    Gender gender;
    Date joinedDate;
    Date dob;
    String address;
    String contact;
    String email;
    @Enumerated(EnumType.STRING)
    Role role;

    @ManyToMany
    @JoinTable(
            name = "staff_field",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "fieldCode")
    )
    List<FieldEntity> fields;

    @OneToMany(mappedBy = "staff")
    List<VehicleEntity> vehicleEntities;

    @OneToMany(mappedBy = "staff")
    List<EquipmentEntity> equipmentEntityList;

    @OneToOne(mappedBy = "staffEntity")
    LogEntity log;

    public void setRole(Role role) {
    }
}
