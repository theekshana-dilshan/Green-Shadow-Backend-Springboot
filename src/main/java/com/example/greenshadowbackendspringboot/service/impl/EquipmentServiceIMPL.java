package com.example.greenshadowbackendspringboot.service.impl;

import com.example.greenshadowbackendspringboot.customStatusCodes.SelectedErrorStatus;
import com.example.greenshadowbackendspringboot.dao.EquipmentDao;
import com.example.greenshadowbackendspringboot.dto.EquipmentStatus;
import com.example.greenshadowbackendspringboot.dto.impl.EquipmentDTO;
import com.example.greenshadowbackendspringboot.entity.impl.EquipmentEntity;
import com.example.greenshadowbackendspringboot.exception.DataPersistException;
import com.example.greenshadowbackendspringboot.exception.EquipmentNotFoundException;
import com.example.greenshadowbackendspringboot.service.EquipmentService;
import com.example.greenshadowbackendspringboot.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class EquipmentServiceIMPL implements EquipmentService {

    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private Mapping equipmentMapping;

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        /*equipmentDTO.setEquipmentId(AppUtil.generateNoteId());*/
        EquipmentEntity savedNote =
                equipmentDao.save(equipmentMapping.toEquipmentEntity(equipmentDTO ));
        if(savedNote == null){
            throw new DataPersistException("Equipment not saved");
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipments() {
        return equipmentMapping.asEquipmentDTOList( equipmentDao.findAll());
    }

    @Override
    public EquipmentStatus getEquipment(String equipmentId) {
        if(equipmentDao.existsById(equipmentId)){
            var selectedEquipment = equipmentDao.getReferenceById(equipmentId);
            return equipmentMapping.toEquipmentDTO(selectedEquipment);
        }else {
            return new SelectedErrorStatus(2,"Selected equipment not found");
        }
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        Optional<EquipmentEntity> foundEquipment = equipmentDao.findById(equipmentId);
        if (!foundEquipment.isPresent()) {
            throw new EquipmentNotFoundException("Equipment not found");
        }else {
            equipmentDao.deleteById(equipmentId);
        }
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {
        Optional<EquipmentEntity> findEquipment = equipmentDao.findById(equipmentId);
        if (!findEquipment.isPresent()) {
            throw new EquipmentNotFoundException("Equipment not found");
        }else {
            findEquipment.get().setName(equipmentDTO.getName());
            findEquipment.get().setType(equipmentDTO.getType());
            findEquipment.get().setStatus(equipmentDTO.getStatus());
            findEquipment.get().setAssignedStaffDetails(equipmentDTO.getAssignedStaffDetails());
        }
    }
}
