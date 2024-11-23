package com.example.greenshadowbackendspringboot.service;

import com.example.greenshadowbackendspringboot.dto.FieldStatus;
import com.example.greenshadowbackendspringboot.dto.impl.FieldDTO;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);
    List<FieldDTO> getAllFields();
    FieldStatus getField(String fieldCode);
    void deleteField(String fieldCode);
    void updateField(String fieldCode, FieldDTO fieldDTO);
}
