package com.gmail.gal.gavrik.display;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.gmail.gal.gavrik.display.domain.Measurements;
import com.gmail.gal.gavrik.display.domain.Spectrums;
import com.gmail.gal.gavrik.display.domain.Users;
import com.gmail.gal.gavrik.display.service.MeasurementsService;

public class MeasurementsServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:jpa-app-context.xml");
		ctx.refresh();
		
		System.out.println("App context initialized successfully");
		
		MeasurementsService measurementsService = ctx.getBean("measurementsService", MeasurementsService.class); 
		
		List<Measurements> measurements = measurementsService.findAll();
		
		for (Measurements measurement: measurements) {
			System.out.println(measurement);
		}
		
		System.out.println("by id: " + measurementsService.findById(2l));
		
		
		
		
		measurements = measurementsService.findAllWithDetail();
		
		for (Measurements measurement: measurements) {
			System.out.println(measurement);
			for (Users users: measurement.getUsers()) {
				System.out.println("Short name: " + users.getShortName());
			}
			System.out.println(" Equipment: model: " + measurement.getEquipment().getModel()+ "s/n: " + measurement.getEquipment().getSerialNumber());
			for (Spectrums spectrums: measurement.getSpectrums()) {
				System.out.println("------------------spectrum ¹: " + spectrums.getIdSpectrums() + " param: " + spectrums.getSpectrumParameters());
			}
		}
		
		ctx.close();

	}

}
