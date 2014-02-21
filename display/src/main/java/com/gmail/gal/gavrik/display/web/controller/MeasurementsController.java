package com.gmail.gal.gavrik.display.web.controller;

import java.util.ArrayList;

import java.util.List;


import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gmail.gal.gavrik.display.domain.Measurements;
import com.gmail.gal.gavrik.display.service.MeasurementsService;
import com.gmail.gal.gavrik.display.web.controller.util.MeasurementsView;

@RequestMapping("/measurements")
@Controller
public class MeasurementsController {

	final Logger				logger	= LoggerFactory
												.getLogger(MeasurementsController.class);

	@Autowired
	private MeasurementsService	measurementsService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel) {
		logger.info("Listing measurements");

		List<Measurements> measurements = measurementsService.findAll();
		
		List<MeasurementsView> measurementsViews = new ArrayList<MeasurementsView>();
		DateTime currentMeasurementDate = null;
		
		
		
		for (Measurements measurement : measurements) {
			MeasurementsView measurementsView = new MeasurementsView();
			
			if(measurementsViews.isEmpty()) {
				currentMeasurementDate = measurement.getDateOfMeasurement().getDate();
				measurementsView.setDateOfMeasurement(currentMeasurementDate);
			}
			
			measurementsView.setMeasurements(measurement);
			if(measurement.getDateOfSecondMeasurement() != null) { 
				measurementsView.setDateOfSecondMeasurement(measurement.getDateOfSecondMeasurement().getDate());
			}
			
			
			if(currentMeasurementDate.isBefore(measurement.getDateOfMeasurement().getDate())) {
				currentMeasurementDate = measurement.getDateOfMeasurement().getDate();
				measurementsView.setDateOfMeasurement(currentMeasurementDate);
			}	
			
			measurementsViews.add(measurementsView);
		}
		
		uiModel.addAttribute("measurements", measurements);
		uiModel.addAttribute("measurementsViews", measurementsViews);

		logger.info("No. of measurements: " + measurements.size());
		logger.info("No. of measurementsViews: " + measurementsViews.size());

		return "measurements/list";
	}

}
