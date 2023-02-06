package com.musala.drones.service;

import com.musala.drones.entity.Drone;
import com.musala.drones.entity.Fleet;
import com.musala.drones.entity.Medication;
import com.musala.drones.enums.DroneState;
import com.musala.drones.model.FleetRegisterRequest;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.repository.FleetRepository;
import com.musala.drones.repository.MedicationRepository;
import com.musala.drones.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FleetService {

    @Autowired
    private FleetRepository fleetRepository;

    @Autowired
    private DroneService droneService;

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    public List<Fleet> getAllFleets(){
        return fleetRepository.findAll();
    }
    public Fleet registerFleet(FleetRegisterRequest fleetRegisterRequest){
        var fleet = Mapper.mapFleetRegisterRequestToFleet(fleetRegisterRequest,droneService,medicationRepository);
        if (droneService.isDroneAvailable(fleet.getDrone()) &&
                isMedicationWeightAvailiable(fleet.getDrone(),fleet.getMedication())){

            droneService.updateDroneStateTo(fleet.getDrone(), DroneState.LOADING);
            return fleetRepository.save(fleet);
        }
        throw new RuntimeException("Drone is not Available ");
    }

    private boolean isMedicationWeightAvailiable(Drone drone, List<Medication> medications) {
        return drone.getWeightLimit() > medications.stream().mapToDouble(Medication::getWeight).sum();
    }

    public Fleet getFleetMedicationsByDroneSerialNumber(String serialNumber){
        var drone = droneService.getDroneBySerialNumber(serialNumber);
        return fleetRepository.findByDrone(drone);
    }

}
