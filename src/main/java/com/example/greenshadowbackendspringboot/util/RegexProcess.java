package com.example.greenshadowbackendspringboot.util;

import java.util.regex.Pattern;

public class RegexProcess {
    public static boolean cropIdMatcher(String cropCode) {
        String regexForCropID = "^CROP-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForCropID);
        return regexPattern.matcher(cropCode).matches();
    }

    public static boolean equipmentIdMatcher(String equipmentId) {
        String regexForEquipmentID = "^EQUIPMENT-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForEquipmentID);
        return regexPattern.matcher(equipmentId).matches();
    }

    public static boolean fieldIdMatcher(String fieldCode) {
        String regexForFieldID = "^FIELD-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForFieldID);
        return regexPattern.matcher(fieldCode).matches();
    }

    public static boolean logIdMatcher(String logCode) {
        String regexForLogID = "^LOG-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForLogID);
        return regexPattern.matcher(logCode).matches();
    }

    public static boolean staffIdMatcher(String staffId) {
        String regexForStaffID = "^STAFF-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForStaffID);
        return regexPattern.matcher(staffId).matches();
    }

    public static boolean vehicleIdMatcher(String vehicleCode) {
        String regexForVehicleID = "^VEHICLE-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForVehicleID);
        return regexPattern.matcher(vehicleCode).matches();
    }
}
