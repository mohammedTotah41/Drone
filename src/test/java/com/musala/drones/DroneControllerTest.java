package com.musala.drones;

import com.musala.drones.controller.DroneController;
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
import com.musala.drones.service.FleetService;
import com.musala.drones.utils.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DroneControllerTest {
    @InjectMocks
    DroneController droneController;

    @Mock
    DroneService droneService;

    @Mock
    FleetService fleetService;

    @Autowired
    MedicationRepository medicationRepository;

    @Test
    public void testRegisterDrone() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Drone droneResult = new Drone();
        droneResult.setSerialNumber("SER_500");

        when(droneService.registerDrone(any(DroneRegisterRequest.class))).thenReturn(droneResult);

        DroneRegisterRequest drone = new DroneRegisterRequest("SER_500", DroneModel.HEAVY_WEIGHT.name(), 150.0F, 80.0F,
                DroneState.IDLE.name());

        ResponseEntity<Drone> responseEntity = droneController.registerDrone(drone);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody()).isEqualTo(droneResult);

    }


    @Test
    public void testCheckDroneBattery() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Drone droneBattery = new Drone("SER_500", DroneModel.LIGHT_WEIGHT,
                50.0F , 75.0F , DroneState.IDLE, LocalDateTime.now() , LocalDateTime.now());

        when(droneService.getDroneBatteryLevel(any(String.class))).thenReturn(droneBattery);

        ResponseEntity<Float> responseEntity = droneController
                .getDroneBatteryLevel("SER_500");
        assertThat(responseEntity.getBody()).isEqualTo(droneBattery.getBattery());

    }


    @Test
    public void testLoadDrone() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Fleet fleet = new Fleet();
        fleet.setCreationDate(LocalDateTime.now());
        fleet.setEndDestination("Africa");
        fleet.setStartDestination("Musala");
        fleet.setMedication(Arrays.asList(
            new Medication("MED_100","Adderall",50.0F,"Adderall.jpg" , LocalDateTime.now(),LocalDateTime.now()),
            new Medication("MED_101","Amitriptyline",80.0F,"Amitriptyline.jpg" , LocalDateTime.now(),LocalDateTime.now())
        ));
        fleet.setDrone(new Drone("SER_500", DroneModel.LIGHT_WEIGHT,
                50.0F , 75.0F , DroneState.IDLE, LocalDateTime.now() , LocalDateTime.now()));



        when(fleetService.registerFleet(any(FleetRegisterRequest.class))).thenReturn(fleet);

        FleetRegisterRequest loadRequest = new FleetRegisterRequest("SER_500", "Africa", "Musala",
                Arrays.asList("MED_100","MED_101"));
        ResponseEntity<Fleet> responseEntity = droneController.loadDrone(loadRequest);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody()).isEqualTo(fleet);

    }

    @Test
    public void testCheckLoadedMedicationItem() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        var medication = new Medication("MED_100","Adderall",50.0F,"Adderall.jpg" ,
                LocalDateTime.now(),LocalDateTime.now());

        Fleet fleet = new Fleet();
        fleet.setCreationDate(LocalDateTime.now());
        fleet.setEndDestination("Africa");
        fleet.setStartDestination("Musala");
        fleet.setMedication(Arrays.asList(
                new Medication("MED_100","Adderall",50.0F,"Adderall.jpg" , LocalDateTime.now(),LocalDateTime.now())
        ));
        fleet.setDrone(new Drone("SER_500", DroneModel.LIGHT_WEIGHT,
                50.0F , 75.0F , DroneState.IDLE, LocalDateTime.now() , LocalDateTime.now()));

        String serialNumber = "SER_500";
        when(fleetService.getFleetMedicationsByDroneSerialNumber(serialNumber)).thenReturn(fleet);

        ResponseEntity<Fleet> responseEntity =droneController
                .getDroneMedications(serialNumber);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(fleet);

    }



}
