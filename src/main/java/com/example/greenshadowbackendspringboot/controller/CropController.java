package com.example.greenshadowbackendspringboot.controller;

import com.example.greenshadowbackendspringboot.customStatusCodes.SelectedErrorStatus;
import com.example.greenshadowbackendspringboot.dto.CustomStatus;
import com.example.greenshadowbackendspringboot.dto.impl.CropDTO;
import com.example.greenshadowbackendspringboot.exception.CropNotFoundException;
import com.example.greenshadowbackendspringboot.exception.DataPersistException;
import com.example.greenshadowbackendspringboot.service.CropService;
import com.example.greenshadowbackendspringboot.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/crop")
public class CropController {

    @Autowired
    private CropService cropService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(@RequestBody CropDTO cropDTO) {
        try {
            cropService.saveCrop(cropDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{cropCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomStatus getSelectedCrop(@PathVariable("cropCode") String cropCode){
        if (!RegexProcess.cropIdMatcher(cropCode)) {
            return new SelectedErrorStatus(1,"Crop Code is not valid");
        }
        return cropService.getCrop(cropCode);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getALlCrops(){
        return cropService.getAllCrops();
    }
    @DeleteMapping(value = "/{cropCode}")
    public ResponseEntity<Void> deleteCrop(@PathVariable ("cropCode") String cropCode){
        try {
            if (!RegexProcess.cropIdMatcher(cropCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            cropService.deleteCrop(cropCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{cropCode}")
    public ResponseEntity<Void> updateCrop(@PathVariable ("cropCode") String cropCode,
                                           @RequestBody CropDTO updatedCropDTO){
        //validations
        try {
            if(!RegexProcess.cropIdMatcher(cropCode) || updatedCropDTO == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            cropService.updateCrop(cropCode,updatedCropDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
