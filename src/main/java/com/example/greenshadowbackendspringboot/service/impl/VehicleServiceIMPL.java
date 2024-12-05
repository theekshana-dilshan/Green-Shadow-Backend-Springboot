package com.example.greenshadowbackendspringboot.service.impl;

import com.example.greenshadowbackendspringboot.customStatusCodes.SelectedErrorStatus;
import com.example.greenshadowbackendspringboot.dao.VehicleDao;
import com.example.greenshadowbackendspringboot.dto.CustomStatus;
import com.example.greenshadowbackendspringboot.dto.impl.VehicleDTO;
import com.example.greenshadowbackendspringboot.entity.impl.StaffEntity;
import com.example.greenshadowbackendspringboot.entity.impl.VehicleEntity;
import com.example.greenshadowbackendspringboot.exception.DataPersistException;
import com.example.greenshadowbackendspringboot.exception.VehicleNotFoundException;
import com.example.greenshadowbackendspringboot.service.VehicleService;
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
    public CustomStatus getVehicle(String vehicleCode) {
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
        Optional<VehicleEntity> byId = vehicleDao.findById(vehicleCode);

        if (byId.isPresent()) {
            VehicleEntity vehicleEntity = byId.get();

            vehicleEntity.setCategory(vehicleDTO.getCategory());
            vehicleEntity.setStatus(vehicleDTO.getStatus());
            vehicleEntity.setRemarks(vehicleDTO.getRemarks());
            vehicleEntity.setFuelType(vehicleDTO.getFuelType());
            vehicleEntity.setLicensePlateNum(vehicleDTO.getLicensePlateNum());

            if (vehicleDTO.getStaff() != null) {
                StaffEntity staffEntity = vehicleMapping.toStaffEntity(vehicleDTO.getStaff());
                vehicleEntity.setStaff(staffEntity);
            }

            vehicleDao.save(vehicleEntity);
        } else {
            throw new EntityNotFoundException("Vehicle entity with ID " + vehicleCode + " not found.");
        }
    }
}
