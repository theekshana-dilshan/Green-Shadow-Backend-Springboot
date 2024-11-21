package com.example.greenshadowbackendspringboot.dto.impl;

import com.example.greenshadowbackendspringboot.dto.FieldStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements FieldStatus{
    private String fieldCode;
    private String fieldName;
    private Point fieldLocation;
    private Double sizeOfTheField;
    private List<CropDTO> cropList;
    private List<StaffDTO> staffList;
    private String fieldImage1;
    private String fieldImage2;
}
