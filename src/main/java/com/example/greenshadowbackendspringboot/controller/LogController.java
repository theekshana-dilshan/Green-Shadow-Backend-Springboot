package com.example.greenshadowbackendspringboot.controller;

import com.example.greenshadowbackendspringboot.customStatusCodes.SelectedErrorStatus;
import com.example.greenshadowbackendspringboot.dto.CustomStatus;
import com.example.greenshadowbackendspringboot.dto.impl.LogDTO;
import com.example.greenshadowbackendspringboot.exception.DataPersistException;
import com.example.greenshadowbackendspringboot.exception.LogNotFoundException;
import com.example.greenshadowbackendspringboot.service.LogService;
import com.example.greenshadowbackendspringboot.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveLog(@RequestBody LogDTO logDTO) {
        try {
            logService.saveLog(logDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{logCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomStatus getSelectedLog(@PathVariable("logCode") String logCode){
        if (!RegexProcess.logIdMatcher(logCode)) {
            return new SelectedErrorStatus(1,"Log ID is not valid");
        }
        return logService.getLog(logCode);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LogDTO> getALlLogs(){
        return logService.getAllLogs();
    }
    @DeleteMapping(value = "/{logCode}")
    public ResponseEntity<Void> deleteLog(@PathVariable ("logCode") String logCode){
        try {
            if (!RegexProcess.logIdMatcher(logCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            logService.deleteLog(logCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (LogNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{logCode}")
    public ResponseEntity<Void> updateLog(@PathVariable ("logCode") String logCode,
                                           @RequestBody LogDTO updatedLogDTO){
        //validations
        try {
            if(!RegexProcess.logIdMatcher(logCode) || updatedLogDTO == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            logService.updateLog(logCode,updatedLogDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (LogNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
