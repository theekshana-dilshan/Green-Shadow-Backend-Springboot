package com.example.greenshadowbackendspringboot.service.impl;

import com.example.greenshadowbackendspringboot.customStatusCodes.SelectedErrorStatus;
import com.example.greenshadowbackendspringboot.dao.LogDao;
import com.example.greenshadowbackendspringboot.dto.LogStatus;
import com.example.greenshadowbackendspringboot.dto.impl.LogDTO;
import com.example.greenshadowbackendspringboot.entity.impl.LogEntity;
import com.example.greenshadowbackendspringboot.exception.DataPersistException;
import com.example.greenshadowbackendspringboot.exception.LogNotFoundException;
import com.example.greenshadowbackendspringboot.service.LogService;
import com.example.greenshadowbackendspringboot.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class LogServiceIMPL implements LogService {

    @Autowired
    private LogDao logDao;

    @Autowired
    private Mapping logMapping;

    @Override
    public void saveLog(LogDTO logDTO) {
        /*logDTO.setNoteId(AppUtil.generateNoteId());*/
        LogEntity savedLog =
                logDao.save(logMapping.toLogEntity(logDTO));
        if(savedLog == null){
            throw new DataPersistException("log not saved");
        }
    }

    @Override
    public List<LogDTO> getAllLogs() {
        return logMapping.asLogDTOList( logDao.findAll());
    }

    @Override
    public LogStatus getLog(String logCode) {
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
        Optional<LogEntity> findLog = logDao.findById(logCode);
        if (!findLog.isPresent()) {
            throw new LogNotFoundException("Log not found");
        }else {
            findLog.get().setLogDate(logDTO.getLogDate());
            findLog.get().setObservation(logDTO.getObservation());
            findLog.get().setObservedImage(logDTO.getObservedImage());
            findLog.get().setFieldList(logDTO.getFieldList());
            findLog.get().setCropList(logDTO.getCropList());
            findLog.get().setStaffList(logDTO.getStaffList());
        }
    }
}
