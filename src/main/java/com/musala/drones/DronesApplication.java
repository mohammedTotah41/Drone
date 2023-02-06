package com.musala.drones;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@EnableWebSecurity
public class DronesApplication {
	public static void main(String[] args) {
		SpringApplication.run(DronesApplication.class, args);
	}

}
