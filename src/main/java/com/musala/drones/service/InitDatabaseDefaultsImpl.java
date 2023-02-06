package com.musala.drones.service;

import com.musala.drones.entity.Drone;
import com.musala.drones.entity.Fleet;
import com.musala.drones.entity.Medication;
import com.musala.drones.enums.DroneModel;
import com.musala.drones.enums.DroneState;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.repository.FleetRepository;
import com.musala.drones.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class InitDatabaseDefaultsImpl {

    @Autowired(required = true)
    private  DroneRepository droneRepository;

    @Autowired
    private  MedicationRepository medicationRepository;

    @Autowired
    private  FleetRepository fleetRepository;

    public  void initDatabasesStaticData(){
        initDrones();
        initMedications();
        initFleets();
        updateDroneStatus();
    }

    private  void initDrones(){
        var drones = Arrays.asList(
                new Drone("SER_500", DroneModel.LIGHT_WEIGHT, 50.0F , 75.0F , DroneState.IDLE, LocalDateTime.now() , LocalDateTime.now()),
                new Drone("SER_501", DroneModel.MIDDLE_WEIGHT, 150.0F , 80F , DroneState.IDLE, LocalDateTime.now() , LocalDateTime.now()),
                new Drone("SER_502", DroneModel.CRUISER_WEIGHT, 300.0F , 65.0F , DroneState.IDLE, LocalDateTime.now() , LocalDateTime.now()),
                new Drone("SER_503", DroneModel.HEAVY_WEIGHT, 450.0F , 90.00F , DroneState.IDLE, LocalDateTime.now() , LocalDateTime.now()),
                new Drone("SER_504", DroneModel.LIGHT_WEIGHT, 100.0F , 95.00F , DroneState.IDLE, LocalDateTime.now() , LocalDateTime.now()),
                new Drone("SER_505", DroneModel.MIDDLE_WEIGHT, 180.0F , 30.00F , DroneState.IDLE, LocalDateTime.now() , LocalDateTime.now()),
                new Drone("SER_506", DroneModel.CRUISER_WEIGHT, 350.0F , 45.00F , DroneState.IDLE, LocalDateTime.now() , LocalDateTime.now()),
                new Drone("SER_507", DroneModel.HEAVY_WEIGHT, 500.0F , 20.00F , DroneState.IDLE, LocalDateTime.now() , LocalDateTime.now()),
                new Drone("SER_508", DroneModel.MIDDLE_WEIGHT, 160.0F , 22.00F , DroneState.IDLE, LocalDateTime.now() , LocalDateTime.now()),
                new Drone("SER_509", DroneModel.CRUISER_WEIGHT, 280.0F , 25.00F , DroneState.IDLE, LocalDateTime.now() , LocalDateTime.now())
        );
        droneRepository.saveAll(drones);
    }

    private  void initMedications(){
        var meds = Arrays.asList(
                new Medication("MED_100","Adderall",50.0F,"Adderall.jpg" , LocalDateTime.now(),LocalDateTime.now()),
                new Medication("MED_101","Amitriptyline",80.0F,"Amitriptyline.jpg" , LocalDateTime.now(),LocalDateTime.now()),
                new Medication("MED_102","Carboplatin",110.0F,"Carboplatin.jpg" , LocalDateTime.now(),LocalDateTime.now()),
                new Medication("MED_103","Leukeran",130.0F,"Leukeran.jpg" , LocalDateTime.now(),LocalDateTime.now()),
                new Medication("MED_104","Cosmegen",160.0F,"Cosmegen.jpg" , LocalDateTime.now(),LocalDateTime.now()),
                new Medication("MED_105","Toposar",190.0F,"Toposar.jpg" , LocalDateTime.now(),LocalDateTime.now()),
                new Medication("MED_106","Etopophos",220.0F,"Etopophos.jpg" , LocalDateTime.now(),LocalDateTime.now()),
                new Medication("MED_107","Hycamtin",250.0F,"Hycamtin.jpg" , LocalDateTime.now(),LocalDateTime.now()),
                new Medication("MED_108","Tepadina",280.0F,"Tepadina.jpg" , LocalDateTime.now(),LocalDateTime.now()),
                new Medication("MED_109","Topotecan",310.0F,"Topotecan.jpg" , LocalDateTime.now(),LocalDateTime.now()),


                new Medication("MED_110","Remdesivir",340.0F,"Remdesivir.jpg" , LocalDateTime.now(),LocalDateTime.now()),
                new Medication("MED_111","Bamlanivimab",370.0f,"Bamlanivimab.jpg" , LocalDateTime.now(),LocalDateTime.now()),
                new Medication("MED_112","Baricitinib",400.0F,"Baricitinib.jpg" , LocalDateTime.now(),LocalDateTime.now()),
                new Medication("MED_113","Bebtelovimab",430.0F,"Bebtelovimab.jpg" , LocalDateTime.now(),LocalDateTime.now()),
                new Medication("MED_114","Casirivimab",470.0F,"Casirivimab.jpg" , LocalDateTime.now(),LocalDateTime.now()),
                new Medication("MED_115","Evusheld ",500.0F,"Evusheld .jpg" , LocalDateTime.now(),LocalDateTime.now()),
                new Medication("MED_116","Kineret",100.0F,"Kineret.jpg" , LocalDateTime.now(),LocalDateTime.now()),
                new Medication("MED_117","Hycamtin",200.0F,"Hycamtin.jpg" , LocalDateTime.now(),LocalDateTime.now()),
                new Medication("MED_118","Molnupiravir",300.0F,"Molnupiravir.jpg" , LocalDateTime.now(),LocalDateTime.now()),
                new Medication("MED_119","Paxlovid",400.0F,"Paxlovid.jpg" , LocalDateTime.now(),LocalDateTime.now())
        );
        medicationRepository.saveAll(meds);
    }

    private  void initFleets(){
        var fleets = Arrays.asList(
                new Fleet("Musala","Australia",LocalDateTime.now(),
                        droneRepository.findById(1L).get(),
                        Arrays.asList(
                                medicationRepository.findById(1L).get(),medicationRepository.findById(2L).get())),

                new Fleet("Musala","Canda",LocalDateTime.now(),
                        droneRepository.findById(2L).get(),
                        Arrays.asList(
                                medicationRepository.findById(3L).get(),medicationRepository.findById(4L).get())),

                new Fleet("Musala","Japan",LocalDateTime.now(),
                        droneRepository.findById(3L).get(),
                        Arrays.asList(
                                medicationRepository.findById(5L).get(),medicationRepository.findById(6L).get())),

                new Fleet("Musala","Russia",LocalDateTime.now(),
                        droneRepository.findById(4L).get(),
                        Arrays.asList(
                                medicationRepository.findById(7L).get(),medicationRepository.findById(8L).get())),

                new Fleet("Musala","Germany",LocalDateTime.now(),
                        droneRepository.findById(5L).get(),
                        Arrays.asList(
                                medicationRepository.findById(9L).get(),medicationRepository.findById(10L).get()))

        );
        fleetRepository.saveAll(fleets);
    }

    private  void updateDroneStatus(){
        var drone = new Drone();

        for (int i = 1 ; i <= 5 ; i++ ){
            drone = droneRepository.findById(Long.valueOf(i)).get();
            drone.setState(DroneState.DELIVERING);
            droneRepository.save(drone);
        }

    }


}
