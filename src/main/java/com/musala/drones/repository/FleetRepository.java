package com.musala.drones.repository;

import com.musala.drones.entity.Drone;
import com.musala.drones.entity.Fleet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FleetRepository extends JpaRepository<Fleet , Long> {
    Fleet findByDrone(Drone drone);
}
