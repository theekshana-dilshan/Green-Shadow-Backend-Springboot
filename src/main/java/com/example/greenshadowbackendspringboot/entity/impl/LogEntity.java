package com.example.greenshadowbackendspringboot.entity.impl;

import com.example.greenshadowbackendspringboot.dto.impl.CropDTO;
import com.example.greenshadowbackendspringboot.dto.impl.FieldDTO;
import com.example.greenshadowbackendspringboot.dto.impl.StaffDTO;
import com.example.greenshadowbackendspringboot.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "logDetails")
public class LogEntity implements SuperEntity {
    @Id
    private String logCode;
    private Date logDate;
    private String observation;
    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;
    @ManyToOne
    @JoinColumn(name = "fieldCode",nullable = false)
    private List<FieldDTO> fieldList;
    @ManyToOne
    @JoinColumn(name = "cropCode",nullable = false)
    private List<CropDTO> cropList;
    @ManyToOne
    @JoinColumn(name = "staffId",nullable = false)
    private List<StaffDTO> staffList;
}
