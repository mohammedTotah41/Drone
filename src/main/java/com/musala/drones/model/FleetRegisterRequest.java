package com.musala.drones.model;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class FleetRegisterRequest {

    public FleetRegisterRequest() {
    }

    public FleetRegisterRequest(String droneSerialNumber, String endDestination, String startDestination, List<String> medicationCodes) {
        this.droneSerialNumber = droneSerialNumber;
        this.endDestination = endDestination;
        this.startDestination = startDestination;
        this.medicationCodes = medicationCodes;
    }

    @NotBlank
    @NotNull(message = "droneSerialNumber number can not be null")
    private String droneSerialNumber;

    @NotBlank
    @NotNull(message = "endDestination number can not be null")
    private String endDestination;

    @NotBlank
    @NotNull(message = "startDestination number can not be null")
    private String startDestination;

    @NotNull(message = "medicationIds number can not be null")
    private List<String> medicationCodes;

    public String getDroneSerialNumber() {
        return droneSerialNumber;
    }

    public void setDroneSerialNumber(String droneSerialNumber) {
        this.droneSerialNumber = droneSerialNumber;
    }

    public String getEndDestination() {
        return endDestination;
    }

    public void setEndDestination(String endDestination) {
        this.endDestination = endDestination;
    }

    public String getStartDestination() {
        return startDestination;
    }

    public void setStartDestination(String startDestination) {
        this.startDestination = startDestination;
    }

    public List<String> getMedicationCodes() {
        return medicationCodes;
    }

    public void setMedicationCodes(List<String> medicationCodes) {
        this.medicationCodes = medicationCodes;
    }
}
