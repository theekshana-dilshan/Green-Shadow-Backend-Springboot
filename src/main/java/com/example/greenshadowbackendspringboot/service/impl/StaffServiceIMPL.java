package com.example.greenshadowbackendspringboot.service.impl;

import com.example.greenshadowbackendspringboot.customStatusCodes.SelectedErrorStatus;
import com.example.greenshadowbackendspringboot.dao.StaffDao;
import com.example.greenshadowbackendspringboot.dto.StaffStatus;
import com.example.greenshadowbackendspringboot.dto.impl.StaffDTO;
import com.example.greenshadowbackendspringboot.entity.impl.StaffEntity;
import com.example.greenshadowbackendspringboot.exception.DataPersistException;
import com.example.greenshadowbackendspringboot.exception.StaffNotFoundException;
import com.example.greenshadowbackendspringboot.service.StaffService;
import com.example.greenshadowbackendspringboot.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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
    public StaffStatus getStaff(String staffId) {
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
            findStaff.get().setFirstName(staffDTO.getFirstName());
            findStaff.get().setLastName(staffDTO.getLastName());
            findStaff.get().setDesignation(staffDTO.getDesignation());
            findStaff.get().setGenderEnum(staffDTO.getGenderEnum());
            findStaff.get().setJoinedDate(staffDTO.getJoinedDate());
            findStaff.get().setDateOfBirth(staffDTO.getDateOfBirth());
            findStaff.get().setAddressLine01(staffDTO.getAddressLine01());
            findStaff.get().setAddressLine02(staffDTO.getAddressLine02());
            findStaff.get().setAddressLine03(staffDTO.getAddressLine03());
            findStaff.get().setAddressLine04(staffDTO.getAddressLine04());
            findStaff.get().setAddressLine05(staffDTO.getAddressLine05());
            findStaff.get().setContactNo(staffDTO.getContactNo());
            findStaff.get().setEmail(staffDTO.getEmail());
            findStaff.get().setRole(staffDTO.getRole());
            findStaff.get().setFieldList(staffDTO.getFieldList());
            findStaff.get().setVehicleList(staffDTO.getVehicleList());
        }
    }
}
