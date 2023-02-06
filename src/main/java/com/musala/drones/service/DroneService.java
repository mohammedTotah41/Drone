package com.musala.drones.service;

import com.musala.drones.entity.Drone;
import com.musala.drones.enums.DroneState;
import com.musala.drones.model.DroneRegisterRequest;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DroneService {
    @Autowired
    private DroneRepository droneRepository;

    public List<Drone> getAllDrones(){
        return droneRepository.findAll();
    }

    public Drone getDroneBySerialNumber(String serialNumber){
        var drone = droneRepository.findBySerialNumber(serialNumber);
        if (drone != null)
            return drone;
        throw new RuntimeException("No Such Drone with serial number : " + serialNumber + " exists");
    }
    public Drone registerDrone(DroneRegisterRequest droneRegisterRequest){
        var drone = Mapper.mapDroneRegisterRequestToDrone(droneRegisterRequest);
        if(checkDroneDataValidation(drone))
            return droneRepository.save(drone);
        throw new RuntimeException("Please make sure to insert valid drone data");
    }

    private boolean checkDroneDataValidation(Drone drone){
        return
                (drone.getState().equals(DroneState.IDLE) || drone.getState().equals(DroneState.LOADING) ||
                drone.getState().equals(DroneState.LOADED) ||drone.getState().equals(DroneState.DELIVERING) ||
                drone.getState().equals(DroneState.DELIVERED) || drone.getState().equals(DroneState.RETURNING))
                && (drone.getBattery() <= 100.0F && drone.getBattery() >= 0.0F)
                && (drone.getWeightLimit() <= 500.0F && drone.getWeightLimit() >= 0.0F)
                && (droneRepository.findBySerialNumber(drone.getSerialNumber()) == null);
    }

    public List<Drone> getAvailableDrones(){
        var drones = droneRepository.findByState(DroneState.IDLE);
        if (drones != null && drones.size() > 0)
           drones.removeIf(drone -> drone.getBattery() < 25.0F);
        return drones;
    }

    public boolean isDroneAvailable(Drone drone){
        return drone.getState().equals(DroneState.IDLE) && drone.getBattery() > 25.0F ? true: false;
    }

    public Drone deliverDrone(String serialNumber){
        var drone = getDroneBySerialNumber(serialNumber);
        drone.setState(DroneState.DELIVERED);
        drone.setModifiedDate(LocalDateTime.now());
        return droneRepository.save(drone);
    }


    public void updateDroneStateTo(Drone drone ,DroneState droneState){
        drone.setState(droneState);
        drone.setModifiedDate(LocalDateTime.now());
        droneRepository.save(drone);
    }

    public Drone getDroneBatteryLevel(String serialNumber ){
        return getDroneBySerialNumber(serialNumber);
    }
}
