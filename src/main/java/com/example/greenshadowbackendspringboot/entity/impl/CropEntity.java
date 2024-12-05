package com.example.greenshadowbackendspringboot.entity.impl;


import com.example.greenshadowbackendspringboot.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CropEntity implements SuperEntity {
    @Id
    String cropCode;
    String commonName;
    String scientificName;
    @Column(columnDefinition = "LONGTEXT")
    String image;
    String category;
    String season;

    @ManyToOne
    @JoinColumn(name = "field_code" )
    FieldEntity fieldEntity;

    @OneToMany(mappedBy = "cropEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<LogEntity> logEntityList;
}
