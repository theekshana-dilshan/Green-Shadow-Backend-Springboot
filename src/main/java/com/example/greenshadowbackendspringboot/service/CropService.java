package com.example.greenshadowbackendspringboot.service;

import com.example.greenshadowbackendspringboot.dto.CustomStatus;
import com.example.greenshadowbackendspringboot.dto.impl.CropDTO;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO cropDTO);
    List<CropDTO> getAllCrops();
    CustomStatus getCrop(String cropCode);
    void deleteCrop(String cropCode);
    void updateCrop(String cropCode, CropDTO cropDTO);
}
