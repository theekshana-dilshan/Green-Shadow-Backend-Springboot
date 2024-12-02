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
@Table(name = "monitorLog")
public class LogEntity implements SuperEntity {

    @Id
    String logCode;

    Date date;
    String observation;

    @Column(columnDefinition = "LONGTEXT")
    String observationImage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "field_code", referencedColumnName = "fieldCode")
    FieldEntity fieldEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "crop_code", referencedColumnName = "cropCode")
    CropEntity cropEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    StaffEntity staffEntity;

}
