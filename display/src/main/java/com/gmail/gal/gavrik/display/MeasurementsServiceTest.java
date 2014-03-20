package com.gmail.gal.gavrik.display;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.gmail.gal.gavrik.display.domain.Harmonics;
import com.gmail.gal.gavrik.display.domain.Measurements;
import com.gmail.gal.gavrik.display.domain.Norms;
import com.gmail.gal.gavrik.display.domain.Spectrums;
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

		MeasurementsService measurementsService = ctx.getBean("measurementsService",
				MeasurementsService.class);

		List<Measurements> measurements = measurementsService.findAll();

		for (Measurements measurement : measurements) {
			System.out.println(measurement);
		}

		System.out.println("by id: " + measurementsService.findById(2l));

		//measurements = measurementsService.findAllWithDetail();
		measurements = measurementsService.findAll();
		
		

		for (Measurements measurement : measurements) {
			
//			for (Users users : measurement.getUsers()) {
//				System.out.println("Short name: " + users.getShortName());
//			}
			System.out.println(" Equipment: model: " + measurement.getEquipment().getModel()
					+ "s/n: " + measurement.getEquipment().getSerialNumber());
			
			System.out.println("Model descriptions: " + measurement.getEquipment().getModel().getDescription());
			
			int i = 1;
			for (Norms norms : measurement.getEquipment().getModel().getNorms()) {
				
				System.out.println("Norm #" + i + " is: " + norms.getShortNorms());
				i++;
				
			}
			
			for (Spectrums spectrums : measurement.getSpectrums()) {
				System.out.println("------------------spectrum ¹: " 
						+ spectrums.getIdSpectrums() + " param:\n ------------> measurand: "
						+ "measurand: " +spectrums.getSpectrumParameters().getMeasurand()
						+ " ------------> type: "
						+ spectrums.getSpectrumParameters().getType()
						+ "------------> resolution: "
						+ spectrums.getSpectrumParameters().getResolution() + "\n");
				for (Harmonics harmonics : spectrums.getHarmonics()) {
					System.out.println("        Harmonics: \n" + "          F: "
							+ harmonics.getFrequency() + "       A: "
							+ harmonics.getAmplitude());
				}
			}
		}

		ctx.close();

	}
}
