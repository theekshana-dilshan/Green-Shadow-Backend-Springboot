package com.example.greenshadowbackendspringboot.dto.impl;

import com.example.greenshadowbackendspringboot.dto.StaffStatus;
import com.example.greenshadowbackendspringboot.entity.Gender;
import com.example.greenshadowbackendspringboot.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements StaffStatus {
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
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
    private Role role;
    private List<FieldDTO> fieldList;
    private List<VehicleDTO> vehicleList;
}
