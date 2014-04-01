package com.gmail.gal.gavrik.display.web.controller.util;

import org.joda.time.DateTime;

import com.gmail.gal.gavrik.display.domain.Measurements;

public class MeasurementsView {

	private Measurements	measurements;
	private String			modelName;
	private DateTime		dateOfMeasurement;
	private DateTime		dateOfSecondMeasurement;

	public Measurements getMeasurements() {
		return measurements;
	}

	public void setMeasurements(Measurements measurements) {
		this.measurements = measurements;
	}

	public DateTime getDateOfMeasurement() {
		return dateOfMeasurement;
	}

	public void setDateOfMeasurement(DateTime dateOfMeasurement) {
		this.dateOfMeasurement = dateOfMeasurement;
	}

	public DateTime getDateOfSecondMeasurement() {
		return dateOfSecondMeasurement;
	}

	public void setDateOfSecondMeasurement(DateTime dateOfSecondMeasurement) {
		this.dateOfSecondMeasurement = dateOfSecondMeasurement;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}
