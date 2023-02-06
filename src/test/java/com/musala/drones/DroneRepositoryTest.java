package com.musala.drones;

import com.musala.drones.entity.Drone;
import com.musala.drones.enums.DroneModel;
import com.musala.drones.enums.DroneState;
import com.musala.drones.repository.DroneRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DroneRepositoryTest {

    @Autowired
    DroneRepository droneRepository;

    @Test
    public void testRegisterDrone() {
        var drone = new Drone("SER_500", DroneModel.LIGHT_WEIGHT,
                50.0F , 75.0F , DroneState.IDLE, LocalDateTime.now() , LocalDateTime.now());
        droneRepository.save(drone);

        Iterable<Drone> drones = droneRepository.findAll();
        Assertions.assertThat(drones).extracting(Drone::getSerialNumber).contains(drone.getSerialNumber());
        droneRepository.deleteAll();
        Assertions.assertThat(droneRepository.findAll()).isEmpty();
    }
}
