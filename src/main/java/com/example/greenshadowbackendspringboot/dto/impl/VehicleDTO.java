package com.example.greenshadowbackendspringboot.dto.impl;

import com.example.greenshadowbackendspringboot.dto.CustomStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO implements CustomStatus {
    String vehicleCode;
    String licensePlateNum;
    String category;
    String fuelType;
    String status;
    String remarks;
    StaffDTO staff;
}
