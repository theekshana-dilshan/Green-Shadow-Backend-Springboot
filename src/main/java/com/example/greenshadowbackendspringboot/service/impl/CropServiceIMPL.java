package com.example.greenshadowbackendspringboot.service.impl;

import com.example.greenshadowbackendspringboot.customStatusCodes.SelectedErrorStatus;
import com.example.greenshadowbackendspringboot.dao.CropDao;
import com.example.greenshadowbackendspringboot.dto.CustomStatus;
import com.example.greenshadowbackendspringboot.dto.impl.CropDTO;
import com.example.greenshadowbackendspringboot.entity.impl.CropEntity;
import com.example.greenshadowbackendspringboot.entity.impl.FieldEntity;
import com.example.greenshadowbackendspringboot.exception.CropNotFoundException;
import com.example.greenshadowbackendspringboot.exception.DataPersistException;
import com.example.greenshadowbackendspringboot.service.CropService;
import com.example.greenshadowbackendspringboot.util.AppUtil;
import com.example.greenshadowbackendspringboot.util.Mapping;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropServiceIMPL implements CropService {

    @Autowired
    private CropDao cropDao;

    @Autowired
    private Mapping cropMapping;

    @Override
    public void saveCrop(CropDTO cropDTO) {
        /*cropDTO.setCropCode(AppUtil.generateNoteId());*/
        CropEntity savedNote =
                cropDao.save(cropMapping.toCropEntity(cropDTO));
        if(savedNote == null){
            throw new DataPersistException("Note not saved");
        }
    }

    @Override
    public List<CropDTO> getAllCrops() {
        return cropMapping.asCropDTOList( cropDao.findAll());
    }

    @Override
    public CustomStatus getCrop(String cropCode) {
        if(cropDao.existsById(cropCode)){
            var selectedUser = cropDao.getReferenceById(cropCode);
            return cropMapping.toCropDTO(selectedUser);
        }else {
            return new SelectedErrorStatus(2,"Selected crop not found");
        }
    }

    @Override
    public void deleteCrop(String cropCode) {
        Optional<CropEntity> foundCrop = cropDao.findById(cropCode);
        if (!foundCrop.isPresent()) {
            throw new CropNotFoundException("Crop not found");
        }else {
            cropDao.deleteById(cropCode);
        }
    }

    @Override
    public void updateCrop(String cropCode, CropDTO cropDTO) {
        Optional<CropEntity> byId = cropDao.findById(cropCode);

        if (byId.isPresent()) {
            CropEntity cropEntity = byId.get();

            cropEntity.setCategory(cropDTO.getCategory());
            cropEntity.setImage(cropDTO.getImage());
            cropEntity.setSeason(cropDTO.getSeason());
            cropEntity.setCommonName(cropDTO.getCommonName());
            cropEntity.setScientificName(cropDTO.getScientificName());

            if (cropDTO.getFieldDTO() != null) {
                FieldEntity fieldEntity = cropMapping.toFieldEntity(cropDTO.getFieldDTO());
                cropEntity.setFieldEntity(fieldEntity);
            }


            cropDao.save(cropEntity);
        } else {
            throw new EntityNotFoundException("Crop entity with ID " + cropCode + " not found.");
        }
    }
}
