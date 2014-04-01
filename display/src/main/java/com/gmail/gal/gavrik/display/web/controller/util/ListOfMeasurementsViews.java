package com.gmail.gal.gavrik.display.web.controller.util;

import java.util.ArrayList;

import java.util.List;

import org.joda.time.DateTime;

import com.gmail.gal.gavrik.display.domain.Measurements;

public class ListOfMeasurementsViews {

	private List<MeasurementsView>	measurementsViews	= new ArrayList<MeasurementsView>();
	
	private String currentModelName = null;
	private DateTime currentMeasurementDate = null;

	public ListOfMeasurementsViews(List<Measurements> measurements) {

		for (Measurements measurement : measurements) {
			addMeasurement(measurement);
		}
	}
	
	public void addMeasurement(Measurements measurement) {
		MeasurementsView measurementsView = new MeasurementsView();

		if (measurementsViews.isEmpty()) {
			currentMeasurementDate = measurement.getDateOfMeasurement().getDate();
			currentModelName = measurement.getEquipment().getModel().getModelName();
			measurementsView.setDateOfMeasurement(currentMeasurementDate);
			measurementsView.setModelName(currentModelName);
		}

		measurementsView.setMeasurements(measurement);
		if (measurement.getDateOfSecondMeasurement() != null) {
			measurementsView.setDateOfSecondMeasurement(measurement
					.getDateOfSecondMeasurement().getDate());
		}

		if (currentMeasurementDate.isAfter(measurement.getDateOfMeasurement().getDate())) {
			currentMeasurementDate = measurement.getDateOfMeasurement().getDate();
			measurementsView.setDateOfMeasurement(currentMeasurementDate);
			measurementsView.setModelName(currentModelName);
		}

		if (currentModelName != measurement.getEquipment().getModel().getModelName()) {
			currentModelName = measurement.getEquipment().getModel().getModelName();
			measurementsView.setModelName(currentModelName);
		}

		measurementsViews.add(measurementsView);
	}

	public List<MeasurementsView> getMeasurementsViews() {
		return measurementsViews;
	}

	public void setMeasurementsViews(List<MeasurementsView> measurementsViews) {
		this.measurementsViews = measurementsViews;
	}

}
