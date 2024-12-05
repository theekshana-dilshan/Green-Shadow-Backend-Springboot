package com.example.greenshadowbackendspringboot.service;

import com.example.greenshadowbackendspringboot.dto.CustomStatus;
import com.example.greenshadowbackendspringboot.dto.impl.LogDTO;

import java.util.List;

public interface LogService {
    void saveLog(LogDTO logDTO);
    List<LogDTO> getAllLogs();
    CustomStatus getLog(String logCode);
    void deleteLog(String logCode);
    void updateLog(String logCode, LogDTO logDTO);
}
