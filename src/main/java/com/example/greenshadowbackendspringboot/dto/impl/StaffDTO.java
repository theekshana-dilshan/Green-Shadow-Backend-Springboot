package com.example.greenshadowbackendspringboot.dto.impl;

import com.example.greenshadowbackendspringboot.dto.CustomStatus;
import com.example.greenshadowbackendspringboot.util.Gender;
import com.example.greenshadowbackendspringboot.util.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDTO implements CustomStatus {
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
    List<FieldDTO> fields;
}
