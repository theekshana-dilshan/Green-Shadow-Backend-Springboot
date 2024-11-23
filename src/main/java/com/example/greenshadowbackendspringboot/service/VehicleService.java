package com.example.greenshadowbackendspringboot.service;

import com.example.greenshadowbackendspringboot.dto.VehicleStatus;
import com.example.greenshadowbackendspringboot.dto.impl.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);
    List<VehicleDTO> getAllVehicle();
    VehicleStatus getVehicle(String vehicleCode);
    void deleteVehicle(String vehicleCode);
    void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO);
}
