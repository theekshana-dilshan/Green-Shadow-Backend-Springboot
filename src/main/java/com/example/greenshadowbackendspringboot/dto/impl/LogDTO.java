package com.example.greenshadowbackendspringboot.dto.impl;

import com.example.greenshadowbackendspringboot.dto.LogStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogDTO implements LogStatus {
    private String logCode;
    private Date logDate;
    private String observation;
    private String observedImage;
    private List<FieldDTO> fieldList;
    private List<CropDTO> cropList;
    private List<StaffDTO> staffList;
}
