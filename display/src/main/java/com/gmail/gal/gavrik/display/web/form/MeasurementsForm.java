package com.gmail.gal.gavrik.display.web.form;

import javax.validation.constraints.Size;

public class MeasurementsForm {

	private String	model;
	private String	serialNumber;
	private String	measurand;
	private String	type;
	private String	screenResolutions;
	private String	description;
	private String	user;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Size(max=100, message="{validation.serialnumber.Size.message}")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getMeasurand() {
		return measurand;
	}

	public void setMeasurand(String measurand) {
		this.measurand = measurand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getScreenResolutions() {
		return screenResolutions;
	}

	public void setScreenResolutions(String screenResolutions) {
		this.screenResolutions = screenResolutions;
	}

	@Size(max=255, message="{validation.description.Size.message}")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
