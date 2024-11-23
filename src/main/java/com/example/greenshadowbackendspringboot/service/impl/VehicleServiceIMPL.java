package com.example.greenshadowbackendspringboot.service.impl;

import com.example.greenshadowbackendspringboot.customStatusCodes.SelectedErrorStatus;
import com.example.greenshadowbackendspringboot.dao.VehicleDao;
import com.example.greenshadowbackendspringboot.dto.VehicleStatus;
import com.example.greenshadowbackendspringboot.dto.impl.VehicleDTO;
import com.example.greenshadowbackendspringboot.entity.impl.VehicleEntity;
import com.example.greenshadowbackendspringboot.exception.DataPersistException;
import com.example.greenshadowbackendspringboot.exception.VehicleNotFoundException;
import com.example.greenshadowbackendspringboot.service.VehicleService;
import com.example.greenshadowbackendspringboot.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class VehicleServiceIMPL implements VehicleService {

    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private Mapping vehicleMapping;

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        /*vehicleDTO.setNoteId(AppUtil.generateNoteId());*/
        VehicleEntity savedVehicle =
                vehicleDao.save(vehicleMapping.toVehicleEntity(vehicleDTO));
        if(savedVehicle == null){
            throw new DataPersistException("Vehicle not saved");
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicle() {
        return vehicleMapping.asVehicleDTOList( vehicleDao.findAll());
    }

    @Override
    public VehicleStatus getVehicle(String vehicleCode) {
        if (vehicleDao.existsById(vehicleCode)) {
            var selectedVehicle = vehicleDao.getReferenceById(vehicleCode);
            return vehicleMapping.toVehicleDTO(selectedVehicle);
        } else {
            return new SelectedErrorStatus(2, "Selected vehicle not found");
        }
    }

    @Override
    public void deleteVehicle(String vehicleCode) {
        Optional<VehicleEntity> foundVehicle = vehicleDao.findById(vehicleCode);
        if (!foundVehicle.isPresent()) {
            throw new VehicleNotFoundException("Vehicle not found");
        }else {
            vehicleDao.deleteById(vehicleCode);
        }
    }

    @Override
    public void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO) {
        Optional<VehicleEntity> findVehicle = vehicleDao.findById(vehicleCode);
        if (!findVehicle.isPresent()) {
            throw new VehicleNotFoundException("Vehicle not found");
        }else {
            findVehicle.get().setLicensePlateNumber(vehicleDTO.getLicensePlateNumber());
            findVehicle.get().setVehicleCategory(vehicleDTO.getVehicleCategory());
            findVehicle.get().setFuelType(vehicleDTO.getFuelType());
            findVehicle.get().setStatus(vehicleDTO.getStatus());
            findVehicle.get().setAllocatedStaffMemberDetails(vehicleDTO.getAllocatedStaffMemberDetails());
            findVehicle.get().setRemarks(vehicleDTO.getRemarks());
        }
    }
}
