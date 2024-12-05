package com.example.greenshadowbackendspringboot.dto.impl;


import com.example.greenshadowbackendspringboot.dto.CustomStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CropDTO implements CustomStatus {
    String cropCode;
    String commonName;
    String scientificName;
    String image;
    String category;
    String season;
    FieldDTO fieldDTO;
}
