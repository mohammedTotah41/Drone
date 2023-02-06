package com.musala.drones;

import com.musala.drones.entity.Drone;
import com.musala.drones.enums.DroneModel;
import com.musala.drones.enums.DroneState;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.service.DroneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.security.DrbgParameters;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DroneServiceTest {

    @InjectMocks
    DroneService droneService;
    @Mock
    DroneRepository droneRepository;

    @Test
    public void testGetAvailabeDrones() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        var drones = Arrays.asList(
                new Drone("SER_500", DroneModel.LIGHT_WEIGHT, 50.0F , 75.0F , DroneState.IDLE, LocalDateTime.now() , LocalDateTime.now()),
                new Drone("SER_501", DroneModel.MIDDLE_WEIGHT, 150.0F , 80F , DroneState.IDLE, LocalDateTime.now() , LocalDateTime.now()),
                new Drone("SER_502", DroneModel.CRUISER_WEIGHT, 300.0F , 65.0F , DroneState.IDLE, LocalDateTime.now() , LocalDateTime.now()),
                new Drone("SER_503", DroneModel.HEAVY_WEIGHT, 450.0F , 90.00F , DroneState.IDLE, LocalDateTime.now() , LocalDateTime.now()),
                new Drone("SER_504", DroneModel.LIGHT_WEIGHT, 100.0F , 95.00F , DroneState.IDLE, LocalDateTime.now() , LocalDateTime.now())
        );


        when(droneRepository.findByState(DroneState.IDLE)).thenReturn(drones);

        var availableDrones = droneService.getAvailableDrones();
        assertEquals(5, availableDrones.size());
        verify(droneRepository, times(1)).findByState(DroneState.IDLE);
    }

}
