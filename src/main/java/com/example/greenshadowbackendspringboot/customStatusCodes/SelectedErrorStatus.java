package com.example.greenshadowbackendspringboot.customStatusCodes;

import com.example.greenshadowbackendspringboot.dto.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatus implements CropStatus, EquipmentStatus, FieldStatus, LogStatus, StaffStatus, UserStatus, VehicleStatus {
    private int status;
    private String statusMessage;
}
