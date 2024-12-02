package com.example.greenshadowbackendspringboot.entity.impl;

import com.example.greenshadowbackendspringboot.dto.impl.FieldDTO;
import com.example.greenshadowbackendspringboot.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cropEntity")
public class CropEntity implements SuperEntity {
    @Id
    String cropCode;
    String commonName;
    String scientificName;
    @Column(columnDefinition = "LONGTEXT")
    String image;
    String category;
    String season;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "fieldCode", nullable = false)
    private FieldEntity fieldEntity;



    @OneToOne(mappedBy = "cropEntity" )
    LogEntity log;
}
