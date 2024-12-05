package com.example.greenshadowbackendspringboot.service.impl;

import com.example.greenshadowbackendspringboot.customStatusCodes.SelectedErrorStatus;
import com.example.greenshadowbackendspringboot.dao.CropDao;
import com.example.greenshadowbackendspringboot.dao.FieldDao;
import com.example.greenshadowbackendspringboot.dao.LogDao;
import com.example.greenshadowbackendspringboot.dao.StaffDao;
import com.example.greenshadowbackendspringboot.dto.CustomStatus;
import com.example.greenshadowbackendspringboot.dto.impl.CropDTO;
import com.example.greenshadowbackendspringboot.dto.impl.FieldDTO;
import com.example.greenshadowbackendspringboot.dto.impl.LogDTO;
import com.example.greenshadowbackendspringboot.dto.impl.StaffDTO;
import com.example.greenshadowbackendspringboot.entity.impl.CropEntity;
import com.example.greenshadowbackendspringboot.entity.impl.FieldEntity;
import com.example.greenshadowbackendspringboot.entity.impl.LogEntity;
import com.example.greenshadowbackendspringboot.entity.impl.StaffEntity;
import com.example.greenshadowbackendspringboot.exception.DataPersistException;
import com.example.greenshadowbackendspringboot.exception.LogNotFoundException;
import com.example.greenshadowbackendspringboot.service.LogService;
import com.example.greenshadowbackendspringboot.util.Mapping;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.yaml.snakeyaml.nodes.NodeId.mapping;

@Service
@Transactional
public class LogServiceIMPL implements LogService {

    @Autowired
    private LogDao logDao;

    @Autowired
    private Mapping logMapping;

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private CropDao cropDao;


    @Override
    public void saveLog(LogDTO logDTO) {
        FieldEntity field = null;
        if (logDTO.getFieldDTO() != null && logDTO.getFieldDTO().getFieldCode() != null) {
            field = fieldDao.getReferenceById(logDTO.getFieldDTO().getFieldCode());
        }

        StaffEntity staff = null;
        if (logDTO.getStaffDTO() != null && logDTO.getStaffDTO().getId() != null) {
            staff = staffDao.getReferenceById(logDTO.getStaffDTO().getId());
        }

        CropEntity crop = null;
        if (logDTO.getCropDTO() != null && logDTO.getCropDTO().getCropCode() != null) {
            crop = cropDao.getReferenceById(logDTO.getCropDTO().getCropCode());
        }

        LogEntity logEntity = logMapping.toLogEntity(logDTO);

        if (field != null) {
            logEntity.setFieldEntity(field);
        }

        if (staff != null) {
            logEntity.setStaffEntity(staff);
        }

        if (crop != null) {
            logEntity.setCropEntity(crop);
        }

        logDao.save(logEntity);
    }

    @Override
    public List<LogDTO> getAllLogs() {
        List<LogEntity> logEntities = logDao.findAll();
        return logEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomStatus getLog(String logCode) {
        if(logDao.existsById(logCode)){
            var selectedLog = logDao.getReferenceById(logCode);
            return logMapping.toLogDTO(selectedLog);
        }else {
            return new SelectedErrorStatus(2,"Selected log not found");
        }
    }

    @Override
    public void deleteLog(String logCode) {
        Optional<LogEntity> foundLog = logDao.findById(logCode);
        if (!foundLog.isPresent()) {
            throw new LogNotFoundException("Log not found");
        }else {
            logDao.deleteById(logCode);
        }
    }

    @Override
    public void updateLog(String logCode, LogDTO logDTO) {
        Optional<LogEntity> byId = logDao.findById(logCode);

        if (byId.isPresent()) {
            LogEntity logEntity = byId.get();

            logEntity.setDate(logDTO.getDate());
            logEntity.setObservation(logDTO.getObservation());
            logEntity.setObservationImage(logDTO.getObservationImage());

            logDao.save(logEntity);
        } else {
            throw new EntityNotFoundException("Log entity with ID " + logCode + " not found.");
        }
    }

    private LogDTO convertToDTO(LogEntity logEntity) {
        LogDTO logDTO = new LogDTO();
        logDTO.setLogCode(logEntity.getLogCode());
        logDTO.setDate(logEntity.getDate());
        logDTO.setObservation(logEntity.getObservation());
        logDTO.setObservationImage(logEntity.getObservationImage());

        if (logEntity.getFieldEntity() != null) {
            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setFieldCode(logEntity.getFieldEntity().getFieldCode());
            fieldDTO.setFieldName(logEntity.getFieldEntity().getFieldName());
            fieldDTO.setFieldLocation(logEntity.getFieldEntity().getFieldLocation());
            fieldDTO.setFieldSize(logEntity.getFieldEntity().getFieldSize());
            fieldDTO.setFieldImage(logEntity.getFieldEntity().getFieldImage());

            logDTO.setFieldDTO(fieldDTO);
        }

        if (logEntity.getCropEntity() != null) {
            CropDTO cropDTO = new CropDTO();
            cropDTO.setCropCode(logEntity.getCropEntity().getCropCode());
            cropDTO.setCommonName(logEntity.getCropEntity().getCommonName());
            cropDTO.setScientificName(logEntity.getCropEntity().getScientificName());
            cropDTO.setImage(logEntity.getCropEntity().getImage());
            cropDTO.setCategory(logEntity.getCropEntity().getCategory());
            cropDTO.setSeason(logEntity.getCropEntity().getSeason());
            logDTO.setCropDTO(cropDTO);
        }

        if (logEntity.getStaffEntity() != null) {
            StaffDTO staffDTO = new StaffDTO();
            StaffEntity staffEntity = logEntity.getStaffEntity();

            staffDTO.setId(staffEntity.getId());
            staffDTO.setFirstName(staffEntity.getFirstName());
            staffDTO.setLastName(staffEntity.getLastName());
            staffDTO.setDesignation(staffEntity.getDesignation());
            staffDTO.setGender(staffEntity.getGender());
            staffDTO.setJoinedDate(staffEntity.getJoinedDate());
            staffDTO.setDob(staffEntity.getDob());
            staffDTO.setAddress(staffEntity.getAddress());
            staffDTO.setContact(staffEntity.getContact());
            staffDTO.setEmail(staffEntity.getEmail());
            staffDTO.setRole(staffEntity.getRole());


            logDTO.setStaffDTO(staffDTO);
        }


        return logDTO;
    }
}
