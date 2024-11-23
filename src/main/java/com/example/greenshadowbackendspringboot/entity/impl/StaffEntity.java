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
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    @Enumerated(EnumType.STRING)
    private Gender genderEnum;
    private Date joinedDate;
    private Date dateOfBirth;
    private String addressLine01;
    private String addressLine02;
    private String addressLine03;
    private String addressLine04;
    private String addressLine05;
    private String contactNo;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany
    @JoinTable(
            name = "staff_field",
            joinColumns = @JoinColumn(name = "staffId"),
            inverseJoinColumns = @JoinColumn(name = "fieldCode")
    )
    private List<FieldDTO> fieldList;
    @OneToOne
    private List<VehicleDTO> vehicleList;
}
