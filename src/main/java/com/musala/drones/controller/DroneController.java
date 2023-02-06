package com.musala.drones.controller;

import com.musala.drones.entity.Drone;
import com.musala.drones.entity.Fleet;
import com.musala.drones.entity.Medication;
import com.musala.drones.model.DroneRegisterRequest;
import com.musala.drones.model.FleetRegisterRequest;
import com.musala.drones.repository.MedicationRepository;
import com.musala.drones.service.DroneService;
import com.musala.drones.service.FleetService;
import com.musala.drones.service.InitDatabaseDefaultsImpl;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping(path="/drone")
public class DroneController {

    @Autowired
    private InitDatabaseDefaultsImpl initDatabaseDefaults;

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private DroneService droneService;

    @Autowired
    private FleetService fleetService;


    @PostMapping(path="/initData")
    public ResponseEntity registerDrone() {
         initDatabaseDefaults.initDatabasesStaticData();
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping(path="/getAllDrones")
    public ResponseEntity<List<Drone>> getAllDrones(){
        return  new ResponseEntity<>(droneService.getAllDrones(),HttpStatus.OK);
    }

    @GetMapping(path="/getAllMedications")
    public ResponseEntity<List<Medication>> getAllMedications(){
        return  new ResponseEntity<>(medicationRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping(path="/getAllFleets")
    public ResponseEntity<List<Fleet>> getAllFleets(){
        return  new ResponseEntity<>(fleetService.getAllFleets(),HttpStatus.OK);
    }


    @GetMapping(path="/getAvailableDrones")
    public ResponseEntity<List<Drone>> getAvailableDrones(){
        return  new ResponseEntity<>(droneService.getAvailableDrones(),HttpStatus.OK);
    }

    @PostMapping(path = "/registerDrone", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Drone> registerDrone(@RequestBody @NotNull @Valid DroneRegisterRequest drone){
        return new ResponseEntity<>(droneService.registerDrone(drone),HttpStatus.CREATED);
    }


    @PostMapping(path = "/loadDrone", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Fleet> loadDrone(@RequestBody @NotNull @Valid FleetRegisterRequest fleetRegisterRequest){
        return new ResponseEntity<>(fleetService.registerFleet(fleetRegisterRequest),HttpStatus.CREATED);
    }

    @PutMapping(path="/deliverDrone/{serialNumber}")
    public ResponseEntity<Drone> deliverDrone(@Valid @NotNull @PathVariable("serialNumber")String serialNumber){
        return new ResponseEntity<>(droneService.deliverDrone(serialNumber) , HttpStatus.OK);
    }

    @GetMapping(path = "/getDroneMedications/{serialNumber}" )
    public ResponseEntity<Fleet> getDroneMedications(@Valid @NotNull @PathVariable("serialNumber")String serialNumber){
        return new ResponseEntity<>(fleetService.getFleetMedicationsByDroneSerialNumber(serialNumber) , HttpStatus.OK);
    }

    @GetMapping(path = "/getDroneBatteryLevel/{serialNumber}" )
    public ResponseEntity<Float> getDroneBatteryLevel(@Valid @NotNull @PathVariable("serialNumber")String serialNumber){
        return new ResponseEntity<>(droneService.getDroneBatteryLevel(serialNumber).getBattery() , HttpStatus.OK);
    }


}
