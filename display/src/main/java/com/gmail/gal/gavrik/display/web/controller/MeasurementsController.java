package com.gmail.gal.gavrik.display.web.controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gmail.gal.gavrik.display.domain.DateOfMeasurement;
import com.gmail.gal.gavrik.display.domain.Equipments;
import com.gmail.gal.gavrik.display.domain.Harmonics;
import com.gmail.gal.gavrik.display.domain.Measurands;
import com.gmail.gal.gavrik.display.domain.Measurements;
import com.gmail.gal.gavrik.display.domain.Models;
import com.gmail.gal.gavrik.display.domain.ScreenResolutions;
import com.gmail.gal.gavrik.display.domain.Spectrums;
import com.gmail.gal.gavrik.display.domain.SpectrumsParameters;
import com.gmail.gal.gavrik.display.domain.Types;
import com.gmail.gal.gavrik.display.domain.Users;
import com.gmail.gal.gavrik.display.parser.DescriptionForParsing;
import com.gmail.gal.gavrik.display.service.DateOfMeasurementService;
import com.gmail.gal.gavrik.display.service.EquipmentsService;
import com.gmail.gal.gavrik.display.service.HarmonicsService;
import com.gmail.gal.gavrik.display.service.MeasurandsService;
import com.gmail.gal.gavrik.display.service.MeasurementsService;
import com.gmail.gal.gavrik.display.service.ModelsService;
import com.gmail.gal.gavrik.display.service.ScreenResolutionsService;
import com.gmail.gal.gavrik.display.service.SpectrumsParametersService;
import com.gmail.gal.gavrik.display.service.SpectrumsService;
import com.gmail.gal.gavrik.display.service.TypesService;
import com.gmail.gal.gavrik.display.service.UsersService;
import com.gmail.gal.gavrik.display.service.jpa.CustomUserDetails.CustomUserDetails;
import com.gmail.gal.gavrik.display.web.controller.util.ListOfMeasurementsViews;
import com.gmail.gal.gavrik.display.web.controller.util.MeasurementsView;
import com.gmail.gal.gavrik.display.web.form.MeasurementsForm;
import com.gmail.gal.gavrik.display.web.form.Message;

@RequestMapping("/measurements")
@Controller
public class MeasurementsController {

	final Logger						logger	= LoggerFactory
														.getLogger(MeasurementsController.class);

	@Autowired
	private MeasurementsService			measurementsService;

	@Autowired
	private ModelsService				modelsService;

	@Autowired
	private MeasurandsService			measurandsService;

	@Autowired
	private TypesService				typesService;

	@Autowired
	private ScreenResolutionsService	screenResolutionsService;

	@Autowired
	private UsersService				usersService;

	@Autowired
	private DateOfMeasurementService	dateOfMeasurementService;

	@Autowired
	private EquipmentsService			equipmentsService;

	@Autowired
	private SpectrumsParametersService	spectrumsParametersService;

	@Autowired
	private SpectrumsService			spectrumsService;

	@Autowired
	private HarmonicsService			harmonicsService;

	@Autowired
	MessageSource						messageSource;

	// ____________________Request Mapping_________________________________
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel) {
		logger.info("Listing measurements");

		if (!uiModel.containsAttribute("measurementsForm")) {
			MeasurementsForm measurementsForm = new MeasurementsForm();
			List<MeasurementsView> measurementsViews = getMeasurementsView();

			measurementsForm.setDescription("");
			measurementsForm.setMeasurand(measurementsViews.get(0).getMeasurements()
					.getSpectrums().get(0).getSpectrumParameters().getMeasurand()
					.getIdMeasurands());
			measurementsForm.setModel(measurementsViews.get(0).getMeasurements()
					.getEquipment().getModel().getModelName());
			measurementsForm.setScreenResolutions(measurementsViews.get(0).getMeasurements()
					.getSpectrums().get(0).getSpectrumParameters().getResolution()
					.getResolution());
			measurementsForm.setType(measurementsViews.get(0).getMeasurements().getSpectrums()
					.get(0).getSpectrumParameters().getType().getIdType());
				 
			uiModel.addAttribute("measurementsForm", measurementsForm);
		}

		return "measurements/list";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id, MeasurementsForm measurementsForm,
			Model uiModel, RedirectAttributes redirectAttributes, Locale locale) {

		Measurements deletedMeasurement = measurementsService.findById(id);
		System.out.println("deliting Measurement: id - "
				+ deletedMeasurement.getIdMeasurements() + "; Model - "
				+ deletedMeasurement.getEquipment().getModel().getModelName()
				+ ", serial No - " + deletedMeasurement.getEquipment().getSerialNumber()
				+ ", with date - " + deletedMeasurement.getDateOfMeasurement());

		logger.info("Deliting measutements with id : "
				+ deletedMeasurement.getIdMeasurements());

		measurementsService.delete(deletedMeasurement);

		uiModel.asMap().clear();

		redirectAttributes.addFlashAttribute("measurementsForm", measurementsForm);
		redirectAttributes.addFlashAttribute(
				"message",
				new Message("success", messageSource.getMessage("measurements_delete_success",
						new Object[] {}, locale)));
		return "redirect:/measurements";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, MeasurementsForm measurementsForm,
			Model uiModel, RedirectAttributes redirectAttributes, Locale locale) {

		Measurements editedMeasurement = measurementsService.findById(id);

		measurementsForm.setSerialNumber(editedMeasurement.getEquipment().getSerialNumber());
		measurementsForm.setModel(editedMeasurement.getEquipment().getModel().getModelName());
		measurementsForm.setMeasurand(editedMeasurement.getSpectrums().get(0)
				.getSpectrumParameters().getMeasurand().getIdMeasurands());
		measurementsForm.setScreenResolutions(editedMeasurement.getSpectrums().get(0)
				.getSpectrumParameters().getResolution().getResolution());
		measurementsForm.setType(editedMeasurement.getSpectrums().get(0)
				.getSpectrumParameters().getType().getIdType());
		measurementsForm.setDescription("");

		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute("measurementsForm", measurementsForm);
		// redirectAttributes.addFlashAttribute(
		// "message",
		// new Message("success",
		// messageSource.getMessage("measurements_delete_success",
		// new Object[] {}, locale)));
		return "redirect:/measurements";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String update(@Valid MeasurementsForm measurementsForm,
			BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest,
			RedirectAttributes redirectAttributes, Locale locale) {

		logger.info("Updating measutements");

		if (bindingResult.hasErrors()) {
			uiModel.addAttribute(
					"message",
					new Message("error", messageSource.getMessage("measurements_save_fail",
							new Object[] {}, locale)));
			uiModel.addAttribute("measurementsForm", measurementsForm);
			return "measurements/list";
		}

		saveMeasurements(measurementsForm);

		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute("measurementsForm", measurementsForm);
		redirectAttributes.addFlashAttribute(
				"message",
				new Message("success", messageSource.getMessage("measurements_save_success",
						new Object[] {}, locale)));

		return "redirect:/measurements";
	}

	// _________________Model Attribute_______________________________________
	@ModelAttribute("measurementsViews")
	public List<MeasurementsView> getMeasurementsView() {
		List<Measurements> measurements = measurementsService.findAll();
		Collections.reverse(measurements);
		ListOfMeasurementsViews listOfMeasurementsViews = new ListOfMeasurementsViews(measurements);
		List<MeasurementsView> measurementsViews = listOfMeasurementsViews.getMeasurementsViews();

//		DateTime currentMeasurementDate = null;
//
//		for (Measurements measurement : measurements) {
//			MeasurementsView measurementsView = new MeasurementsView();
//
//			if (measurementsViews.isEmpty()) {
//				currentMeasurementDate = measurement.getDateOfMeasurement().getDate();
//				measurementsView.setDateOfMeasurement(currentMeasurementDate);
//			}
//
//			measurementsView.setMeasurements(measurement);
//			if (measurement.getDateOfSecondMeasurement() != null) {
//				measurementsView.setDateOfSecondMeasurement(measurement
//						.getDateOfSecondMeasurement().getDate());
//			}
//
//			if (currentMeasurementDate.isAfter(measurement.getDateOfMeasurement().getDate())) {
//				currentMeasurementDate = measurement.getDateOfMeasurement().getDate();
//				measurementsView.setDateOfMeasurement(currentMeasurementDate);
//			}
//
//			measurementsViews.add(measurementsView);
//		}

		return measurementsViews;
	}

	@ModelAttribute("currentDate")
	public DateTime getCurrentDate() {
		return new DateTime();
	}

	@ModelAttribute("models")
	public List<Models> getAllModels() {
		return modelsService.findAll();
	}

	@ModelAttribute("measurands")
	public List<Measurands> getAllMeasurands() {
		return measurandsService.findAll();
	}

	@ModelAttribute("types")
	public List<Types> getAllTypes() {
		return typesService.findAll();
	}

	@ModelAttribute("screenResolutions")
	public List<ScreenResolutions> getAllScreenResolutions() {
		return screenResolutionsService.findAll();
	}

	@ModelAttribute("usersList")
	public List<String> getAllUsers() {
		List<Users> usersList = usersService.findAll();
		List<String> stringUserList = new ArrayList<String>();
		for (Users user : usersList) {
			stringUserList.add(user.getFirstName());
		}
		return stringUserList;
	}

	// _____________Utils_____________________________________
	private Measurements saveMeasurements(MeasurementsForm measurementsForm) {

		Measurements newMeasurements = new Measurements();

		setCurrentDateOfMeasurement(newMeasurements);
		setEquipment(newMeasurements, measurementsForm);
//		setUser(newMeasurements, measurementsForm);

		newMeasurements = saveMeasurement(newMeasurements);

		saveSpectrum(newMeasurements, measurementsForm);

		return newMeasurements;
	}

	private void setCurrentDateOfMeasurement(Measurements newMeasurements) {
		DateOfMeasurement newDateOfMeasurement = new DateOfMeasurement();
		DateTime currentDate = new DateTime();
		newDateOfMeasurement.setDate(currentDate.withTime(0, 0, 0, 0));
		newDateOfMeasurement = dateOfMeasurementService.save(newDateOfMeasurement);
		newMeasurements.setDateOfMeasurement(newDateOfMeasurement);
	}

	private void setEquipment(Measurements newMeasurements, MeasurementsForm measurementsForm) {
		Equipments newEquipments = new Equipments();
		Models model = new Models();
		model = modelsService.findByModelName(measurementsForm.getModel());

		newEquipments.setModel(model);
		newEquipments.setSerialNumber(measurementsForm.getSerialNumber());
		newEquipments = equipmentsService.save(newEquipments);

		newMeasurements.setEquipment(newEquipments);
	}

//	private void setUser(Measurements newMeasurements, MeasurementsForm measurementsForm) {
//		Users user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsersDetails();
//		newMeasurements.setUser(user);
//	}

	private Measurements saveMeasurement(Measurements newMeasurements) {
		List<Measurements> measurementsList = measurementsService
				.findByEquipment(newMeasurements.getEquipment());

		Users user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsersDetails();
		
		newMeasurements.setSpectrums(new ArrayList<Spectrums>());

		if (measurementsList.isEmpty()) {
			newMeasurements.setUser(user);
			newMeasurements = measurementsService.save(newMeasurements);
		} else {
			for (Measurements measurement : measurementsList) {
				if (measurement.getDateOfMeasurement().getIdDate() == newMeasurements
						.getDateOfMeasurement().getIdDate()) {
					newMeasurements = measurement;
					newMeasurements.setUser(user);
				} else {
					measurement.setDateOfSecondMeasurement(newMeasurements
							.getDateOfMeasurement());
					newMeasurements = measurement;
					newMeasurements.setUser(user);
				}
				if (newMeasurements.getIdMeasurements() == null) {
					newMeasurements.setUser(user);
					newMeasurements = measurementsService.save(newMeasurements);
				}

			}
		}
		return newMeasurements;
	}

	private void saveSpectrum(Measurements newMeasurements, MeasurementsForm measurementsForm) {
		Spectrums newSpectrums = new Spectrums();
		SpectrumsParameters newSpectrumsParameters = new SpectrumsParameters();
		Measurands measurands = measurandsService.findById(measurementsForm.getMeasurand());

		newSpectrumsParameters.setMeasurand(measurands);

		ScreenResolutions screenResolutions = screenResolutionsService
				.findByResolution(measurementsForm.getScreenResolutions());

		newSpectrumsParameters.setResolution(screenResolutions);

		Types type = typesService.findById(measurementsForm.getType());
		newSpectrumsParameters.setType(type);

		newSpectrumsParameters = spectrumsParametersService.save(newSpectrumsParameters);

		newSpectrums = spectrumsService.findByMeasurementAndSpectrumParameters(
				newMeasurements, newSpectrumsParameters);

		if (newSpectrums == null) {
			newSpectrums = new Spectrums();
			newSpectrums.setMeasurement(newMeasurements);
			newSpectrums.setSpectrumParameters(newSpectrumsParameters);
			newSpectrums.setHarmonics(new ArrayList<Harmonics>());
		}
		DateTime currentDate = new DateTime();
		newSpectrums.setTime(new Time(currentDate.getMillis()));

		if (newSpectrums.getDescription() == null) {
			newSpectrums.setDescription(measurementsForm.getDescription());
		} else {
			if (newSpectrums.getDescription().isEmpty()) {
				newSpectrums.setDescription(measurementsForm.getDescription());
			} else {
				newSpectrums.setDescription(newSpectrums.getDescription() + "; "
						+ measurementsForm.getDescription());
			}
		}
////////////////////////// Description!!!!!!!?????
		newSpectrums = spectrumsService.save(newSpectrums);
		String description = setHarmonics(measurementsForm.getDescription(), newSpectrums);
		newSpectrums.setDescription(description);
		newSpectrums = spectrumsService.save(newSpectrums);
	}

	private String setHarmonics(String description, Spectrums newSpectrums) {

		DescriptionForParsing newDescription = new DescriptionForParsing(description);

		Double frequency, amplitude, noise;

		while (!newDescription.isString()) {

			frequency = newDescription.parseFrequency();
			if (frequency != null) {
				Harmonics newHarmonics = new Harmonics();
				newHarmonics.setFrequency(frequency);
				amplitude = newDescription.parseAmplitude();
				if (amplitude != null) {
					newHarmonics.setAmplitude(amplitude);
					noise = newDescription.parseNoise();
					if (noise == null) {
						noise = 0.0;
					}
					newHarmonics.setNoise(noise);
					newHarmonics.setSpectrum(newSpectrums);
					harmonicsService.save(newHarmonics);
				}
			} else {
				break;
			}
		}

		description = newDescription.getDescription();
		return description;
	}

}
