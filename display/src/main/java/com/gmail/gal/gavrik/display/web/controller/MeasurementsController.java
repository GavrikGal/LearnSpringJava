package com.gmail.gal.gavrik.display.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gmail.gal.gavrik.display.domain.Measurements;
import com.gmail.gal.gavrik.display.service.MeasurementsService;

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

		//List<Measurements> measurements = measurementsService.findAllWithDetail();
		List<Measurements> measurements = measurementsService.findAll();
		
		uiModel.addAttribute("measurements", measurements);

		logger.info("No. of measurements: " + measurements.size());

		return "measurements/list";
	}

}
