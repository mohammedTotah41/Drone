package com.musala.drones.repository;

import com.musala.drones.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository <Medication ,Long> {
    Medication findByCode(String code);
}
