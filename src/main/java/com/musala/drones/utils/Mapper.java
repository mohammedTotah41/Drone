package com.musala.drones.utils;

import com.musala.drones.entity.Drone;
import com.musala.drones.entity.Fleet;
import com.musala.drones.entity.Medication;
import com.musala.drones.enums.DroneModel;
import com.musala.drones.enums.DroneState;
import com.musala.drones.model.DroneRegisterRequest;
import com.musala.drones.model.FleetRegisterRequest;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.repository.MedicationRepository;
import com.musala.drones.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;


public  class Mapper {

    public static Drone mapDroneRegisterRequestToDrone(DroneRegisterRequest droneRegisterRequest){
        var drone = new Drone();
        drone.setSerialNumber(droneRegisterRequest.getSerialNumber());
        drone.setModel(DroneModel.valueOf(droneRegisterRequest.getModel()));
        drone.setState(DroneState.valueOf(droneRegisterRequest.getState()));
        drone.setBattery(droneRegisterRequest.getBattery());
        drone.setWeightLimit(droneRegisterRequest.getWeightLimit());
        return drone;
    }

    public static Fleet mapFleetRegisterRequestToFleet(FleetRegisterRequest fleetRegisterRequest, DroneService droneService, MedicationRepository medicationRepository){
        var fleet = new Fleet();
        fleet.setDrone(droneService.getDroneBySerialNumber(fleetRegisterRequest.getDroneSerialNumber()));
        fleet.setStartDestination(fleetRegisterRequest.getStartDestination());
        fleet.setEndDestination(fleetRegisterRequest.getEndDestination());
        var medications = new ArrayList<Medication>();
        for (String code : fleetRegisterRequest.getMedicationCodes())
            medications.add(medicationRepository.findByCode(code));
        fleet.setMedication(medications);
        fleet.setCreationDate(LocalDateTime.now());
        return fleet;
    }


}
