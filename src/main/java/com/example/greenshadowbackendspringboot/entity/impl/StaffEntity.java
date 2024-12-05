package com.example.greenshadowbackendspringboot.entity.impl;

import com.example.greenshadowbackendspringboot.entity.SuperEntity;
import com.example.greenshadowbackendspringboot.util.Gender;
import com.example.greenshadowbackendspringboot.util.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "staff_field",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "field_code")
    )
    List<FieldEntity> fields;

    @OneToMany(mappedBy = "staff")
    List<VehicleEntity> vehicleEntities;

    @OneToMany(mappedBy = "staff")
    List<EquipmentEntity> equipmentEntityList;

    @OneToOne(mappedBy ="staffEntity")
    LogEntity log;
}
