package com.example.greenshadowbackendspringboot.service;

import com.example.greenshadowbackendspringboot.dto.StaffStatus;
import com.example.greenshadowbackendspringboot.dto.impl.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);
    List<StaffDTO> getAllStaff();
    StaffStatus getStaff(String staffId);
    void deleteStaff(String staffId);
    void updateStaff(String staffId, StaffDTO staffDTO);
}
