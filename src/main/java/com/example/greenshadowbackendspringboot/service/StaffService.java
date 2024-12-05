package com.example.greenshadowbackendspringboot.service;

import com.example.greenshadowbackendspringboot.dto.CustomStatus;
import com.example.greenshadowbackendspringboot.dto.impl.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);
    List<StaffDTO> getAllStaff();
    CustomStatus getStaff(String staffId);
    void deleteStaff(String staffId);
    void updateStaff(String staffId, StaffDTO staffDTO);
}
