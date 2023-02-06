package com.musala.drones.job;

import com.musala.drones.entity.Drone;
import com.musala.drones.repository.DroneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class DroneBatteryCheck {
    @Autowired
    private DroneRepository droneRepository;

    static final Logger log = LoggerFactory.getLogger(DroneBatteryCheck.class);

    @Scheduled(fixedRate = 1000 * 60 * 15)
    public void logDroneBatteryLevel()  {

        List<Drone> drones = droneRepository.findAll();

        if (drones.size() > 0)
        for (Drone drone : drones){
            log.info("Drone : " + drone.getSerialNumber() +" has a battery Level : " +drone.getBattery()+"%");
            System.out.println("Drone : " + drone.getSerialNumber() +" has a battery Level : "  +drone.getBattery()+"%");
        }

    }

}
