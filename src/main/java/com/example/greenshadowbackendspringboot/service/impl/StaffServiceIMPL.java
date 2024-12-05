package com.example.greenshadowbackendspringboot.service.impl;

import com.example.greenshadowbackendspringboot.customStatusCodes.SelectedErrorStatus;
import com.example.greenshadowbackendspringboot.dao.StaffDao;
import com.example.greenshadowbackendspringboot.dto.CustomStatus;
import com.example.greenshadowbackendspringboot.dto.impl.StaffDTO;
import com.example.greenshadowbackendspringboot.entity.impl.FieldEntity;
import com.example.greenshadowbackendspringboot.entity.impl.StaffEntity;
import com.example.greenshadowbackendspringboot.exception.DataPersistException;
import com.example.greenshadowbackendspringboot.exception.StaffNotFoundException;
import com.example.greenshadowbackendspringboot.service.StaffService;
import com.example.greenshadowbackendspringboot.util.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.yaml.snakeyaml.nodes.NodeId.mapping;

@Service
@Transactional
public class StaffServiceIMPL implements StaffService {

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private Mapping staffMapping;

    @Override
    public void saveStaff(StaffDTO staffDTO) {
        /*staffDTO.setStaffId(AppUtil.generateNoteId());*/
        StaffEntity savedStaff =
                staffDao.save(staffMapping.toStaffEntity(staffDTO));
        if(savedStaff == null){
            throw new DataPersistException("Staff not saved");
        }
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        return staffMapping.asStaffDTOList( staffDao.findAll());
    }

    @Override
    public CustomStatus getStaff(String staffId) {
        if(staffDao.existsById(staffId)){
            var selectedStaff = staffDao.getReferenceById(staffId);
            return staffMapping.toStaffDTO(selectedStaff);
        }else {
            return new SelectedErrorStatus(2,"Selected staff not found");
        }
    }

    @Override
    public void deleteStaff(String staffId) {
        Optional<StaffEntity> foundStaff = staffDao.findById(staffId);
        if (!foundStaff.isPresent()) {
            throw new StaffNotFoundException("Staff not found");
        }else {
            staffDao.deleteById(staffId);
        }
    }

    @Override
    public void updateStaff(String staffId, StaffDTO staffDTO) {
        Optional<StaffEntity> findStaff = staffDao.findById(staffId);
        if (!findStaff.isPresent()) {
            throw new StaffNotFoundException("Staff not found");
        }else {
            StaffEntity staffEntity = findStaff.get();

            staffEntity.setFirstName(staffDTO.getFirstName());
            staffEntity.setLastName(staffDTO.getLastName());
            staffEntity.setDesignation(staffDTO.getDesignation());
            staffEntity.setGender(staffDTO.getGender());
            staffEntity.setJoinedDate(staffDTO.getJoinedDate());
            staffEntity.setDob(staffDTO.getDob());
            staffEntity.setAddress(staffDTO.getAddress());
            staffEntity.setContact(staffDTO.getContact());
            staffEntity.setEmail(staffDTO.getEmail());
            staffEntity.setRole(staffDTO.getRole());

            if (staffDTO.getFields() != null) {
                List<FieldEntity> fieldEntityList = staffMapping.asFieldEntityList(staffDTO.getFields());
                staffEntity.setFields(fieldEntityList);
            }

            staffDao.save(staffEntity);
        }
    }
}
