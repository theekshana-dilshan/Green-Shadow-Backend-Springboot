package com.example.greenshadowbackendspringboot.service.impl;

import com.example.greenshadowbackendspringboot.customStatusCodes.SelectedErrorStatus;
import com.example.greenshadowbackendspringboot.dao.FieldDao;
import com.example.greenshadowbackendspringboot.dto.FieldStatus;
import com.example.greenshadowbackendspringboot.dto.impl.FieldDTO;
import com.example.greenshadowbackendspringboot.entity.impl.FieldEntity;
import com.example.greenshadowbackendspringboot.exception.DataPersistException;
import com.example.greenshadowbackendspringboot.exception.FieldNotFoundException;
import com.example.greenshadowbackendspringboot.service.FieldService;
import com.example.greenshadowbackendspringboot.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class FieldServiceIMPL implements FieldService {

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private Mapping fieldMapping;

    @Override
    public void saveField(FieldDTO fieldDTO) {
        /*fieldDTO.setFieldCode(AppUtil.generateNoteId());*/
        FieldEntity savedField =
                fieldDao.save(fieldMapping.toFieldEntity(fieldDTO));
        if(savedField == null){
            throw new DataPersistException("Field not saved");
        }
    }

    @Override
    public List<FieldDTO> getAllFields() {
        return fieldMapping.asFieldDTOList( fieldDao.findAll());
    }

    @Override
    public FieldStatus getField(String fieldCode) {
        if(fieldDao.existsById(fieldCode)){
            var selectedField = fieldDao.getReferenceById(fieldCode);
            return fieldMapping.toFieldDTO(selectedField);
        }else {
            return new SelectedErrorStatus(2,"Selected field not found");
        }
    }

    @Override
    public void deleteField(String fieldCode) {
        Optional<FieldEntity> foundField = fieldDao.findById(fieldCode);
        if (!foundField.isPresent()) {
            throw new FieldNotFoundException("Field not found");
        }else {
            fieldDao.deleteById(fieldCode);
        }
    }

    @Override
    public void updateField(String fieldCode, FieldDTO fieldDTO) {
        Optional<FieldEntity> findField = fieldDao.findById(fieldCode);
        if (!findField.isPresent()) {
            throw new FieldNotFoundException("Field not found");
        }else {
            findField.get().setFieldName(fieldDTO.getFieldName());
            findField.get().setFieldLocation(fieldDTO.getFieldLocation());
            findField.get().setSizeOfTheField(fieldDTO.getSizeOfTheField());
            findField.get().setCropList(fieldDTO.getCropList());
            findField.get().setStaffList(fieldDTO.getStaffList());
            findField.get().setFieldImage1(fieldDTO.getFieldImage1());
            findField.get().setFieldImage2(fieldDTO.getFieldImage2());
        }
    }
}
