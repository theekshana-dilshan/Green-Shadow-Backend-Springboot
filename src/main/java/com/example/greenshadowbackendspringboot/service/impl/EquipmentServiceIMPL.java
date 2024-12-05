package com.example.greenshadowbackendspringboot.service.impl;

import com.example.greenshadowbackendspringboot.customStatusCodes.SelectedErrorStatus;
import com.example.greenshadowbackendspringboot.dao.EquipmentDao;
import com.example.greenshadowbackendspringboot.dto.CustomStatus;
import com.example.greenshadowbackendspringboot.dto.impl.EquipmentDTO;
import com.example.greenshadowbackendspringboot.entity.impl.EquipmentEntity;
import com.example.greenshadowbackendspringboot.entity.impl.FieldEntity;
import com.example.greenshadowbackendspringboot.entity.impl.StaffEntity;
import com.example.greenshadowbackendspringboot.exception.DataPersistException;
import com.example.greenshadowbackendspringboot.exception.EquipmentNotFoundException;
import com.example.greenshadowbackendspringboot.service.EquipmentService;
import com.example.greenshadowbackendspringboot.util.Mapping;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.yaml.snakeyaml.nodes.NodeId.mapping;

@Service
@Transactional
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
    public CustomStatus getEquipment(String equipmentId) {
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
        Optional<EquipmentEntity> byId = equipmentDao.findById(equipmentId);

        if (byId.isPresent()) {
            EquipmentEntity equipmentEntity = byId.get();

            equipmentEntity.setName(equipmentDTO.getName());
            equipmentEntity.setEquipmentType(equipmentDTO.getEquipmentType());
            equipmentEntity.setStatus(equipmentDTO.getStatus());

            if (equipmentDTO.getStaff() != null) {
                StaffEntity staffEntity = equipmentMapping.toStaffEntity(equipmentDTO.getStaff());
                equipmentEntity.setStaff(staffEntity);
            }

            if (equipmentDTO.getField() != null) {
                FieldEntity fieldEntity = equipmentMapping.toFieldEntity(equipmentDTO.getField());
                equipmentEntity.setField(fieldEntity);
            }

            equipmentDao.save(equipmentEntity);
        } else {
            throw new EntityNotFoundException("Equipment entity with ID " + equipmentId + " not found.");
        }
    }
}
