package com.musala.drones.repository;

import com.musala.drones.entity.Drone;
import com.musala.drones.enums.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    Drone findBySerialNumber(String serialNumber);
    List<Drone> findByState(DroneState state);
}
