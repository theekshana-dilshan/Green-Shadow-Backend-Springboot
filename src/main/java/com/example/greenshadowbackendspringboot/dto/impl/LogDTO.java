package com.example.greenshadowbackendspringboot.dto.impl;

import com.example.greenshadowbackendspringboot.dto.CustomStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogDTO implements CustomStatus {
    String logCode;
    Date date;
    String observation;
    String observationImage;
    FieldDTO fieldDTO;
    StaffDTO staffDTO;
    CropDTO cropDTO;
}
