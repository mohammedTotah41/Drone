package com.musala.drones.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DroneRegisterRequest {
	public DroneRegisterRequest() {

	}

	public DroneRegisterRequest(String serialNumber, String model, Float weightLimit, Float battery,
								String state) {
		super();
		this.serialNumber = serialNumber;
		this.model = model;
		this.weightLimit = weightLimit;
		this.battery = battery;
		this.state = state;
	}

	@NotBlank
	@NotNull(message = "serialNumber number can not be null")
	private String serialNumber;

	@NotBlank
	@NotNull(message = "model number can not be null")
	private String model;

	@NotNull(message = "weightLimit number can not be null")
	private Float weightLimit;

	@NotNull(message = "battery number can not be null")
	private Float battery;

	@NotNull(message = "state number can not be null")
	private String state;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Float getWeightLimit() {
		return weightLimit;
	}

	public void setWeightLimit(Float weightLimit) {
		this.weightLimit = weightLimit;
	}

	public Float getBattery() {
		return battery;
	}

	public void setBattery(Float battery) {
		this.battery = battery;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
