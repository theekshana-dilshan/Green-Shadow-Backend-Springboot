package com.example.greenshadowbackendspringboot.dto.impl;

import com.example.greenshadowbackendspringboot.dto.CustomStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldDTO implements CustomStatus {
    String fieldCode;
    String fieldName;
    String fieldLocation;
    double  fieldSize;
    String fieldImage;
}
