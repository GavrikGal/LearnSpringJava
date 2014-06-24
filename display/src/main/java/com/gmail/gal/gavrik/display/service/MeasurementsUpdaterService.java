package com.gmail.gal.gavrik.display.service;

import com.gmail.gal.gavrik.display.domain.Measurements;

public interface MeasurementsUpdaterService {

	public void updateFromFolder();

	public Measurements saveMeasurements(String modelName, String serialNumber,
			String measurandName, String typeName,
			String screenResolutionsName, String description, String user);

}
