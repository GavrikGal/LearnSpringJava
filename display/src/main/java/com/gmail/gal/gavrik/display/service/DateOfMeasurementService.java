package com.gmail.gal.gavrik.display.service;

import java.util.List;

import org.joda.time.DateTime;

import com.gmail.gal.gavrik.display.domain.DateOfMeasurement;

public interface DateOfMeasurementService {
	
	public List<DateOfMeasurement> findAll();
	
	public DateOfMeasurement findById(Long id);
	
	public DateOfMeasurement findByDate(DateTime date);

	public DateOfMeasurement save(DateOfMeasurement dateOfMeasurement);

}
