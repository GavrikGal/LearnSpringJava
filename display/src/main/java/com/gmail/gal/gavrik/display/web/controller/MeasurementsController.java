package com.gmail.gal.gavrik.display.web.controller;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gmail.gal.gavrik.display.domain.Measurands;
import com.gmail.gal.gavrik.display.domain.Measurements;
import com.gmail.gal.gavrik.display.domain.Models;
import com.gmail.gal.gavrik.display.domain.ScreenResolutions;
import com.gmail.gal.gavrik.display.domain.Types;
import com.gmail.gal.gavrik.display.domain.Users;
import com.gmail.gal.gavrik.display.service.DateOfMeasurementService;
import com.gmail.gal.gavrik.display.service.EquipmentsService;
import com.gmail.gal.gavrik.display.service.HarmonicsService;
import com.gmail.gal.gavrik.display.service.MeasurandsService;
import com.gmail.gal.gavrik.display.service.MeasurementsService;
import com.gmail.gal.gavrik.display.service.MeasurementsUpdaterService;
import com.gmail.gal.gavrik.display.service.ModelsService;
import com.gmail.gal.gavrik.display.service.ScreenResolutionsService;
import com.gmail.gal.gavrik.display.service.SpectrumsParametersService;
import com.gmail.gal.gavrik.display.service.SpectrumsService;
import com.gmail.gal.gavrik.display.service.TypesService;
import com.gmail.gal.gavrik.display.service.UsersService;
import com.gmail.gal.gavrik.display.web.controller.util.ListOfMeasurementsViews;
import com.gmail.gal.gavrik.display.web.controller.util.MeasurementsView;
import com.gmail.gal.gavrik.display.web.form.MeasurementsForm;
import com.gmail.gal.gavrik.display.web.form.Message;
import com.google.common.collect.Lists;

@RequestMapping("/measurements")
@Controller
public class MeasurementsController {

	final int							rowAtPage	= 30;

	final Logger						logger		= LoggerFactory
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
	private MeasurementsUpdaterService	measurementsUpdaterService;

	@Autowired
	MessageSource						messageSource;

	// ____________________Request Mapping_________________________________

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel, MeasurementsForm measurementsForm, HttpServletRequest request) {
		logger.info("Listing measurements");
		
		measurementsUpdaterService.updateFromFolder();

		if (!uiModel.containsAttribute("measurementsForm")) {
			measurementsForm = new MeasurementsForm();

			List<MeasurementsView> measurementsViews = getMeasurementsView(
					new ListOfMeasurementsViews(), uiModel);

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
		} else {
			measurementsForm.setDescription("");
			uiModel.addAttribute("measurementsForm", measurementsForm);
		}
		
		
		return "measurements/list";
	}

	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
	public String listNextPage(@PathVariable("page") int page,
			ListOfMeasurementsViews listOfMeasurementsViews,
			MeasurementsForm measurementsForm, Model uiModel,
			RedirectAttributes redirectAttributes, Locale locale) {

		PageRequest pageRequest = null;
		pageRequest = new PageRequest(page, rowAtPage);
		Page<Measurements> measurementsPage = measurementsService.findAllByPage(pageRequest);
		List<Measurements> measurements = Lists.newArrayList(measurementsPage.iterator());
		Collections.reverse(measurements);
		for (Measurements measurement : measurements) {
			listOfMeasurementsViews.addMeasurement(measurement);
		}
		List<MeasurementsView> measurementsViews = listOfMeasurementsViews
				.getMeasurementsViews();

		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute("measurementsViews", measurementsViews);
		redirectAttributes.addFlashAttribute("measurementsForm", measurementsForm);
		redirectAttributes.addFlashAttribute("listOfMeasurementsViews",
				listOfMeasurementsViews);
		redirectAttributes.addFlashAttribute("currentPage", page);

		return "redirect:/measurements";
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
	@RequestMapping(value = "/protocol/{id}", method = RequestMethod.GET)
	public String protocol(@PathVariable("id") Long id, Model uiModel,
			RedirectAttributes redirectAttributes, Locale locale) {
		Measurements protocolMeasurement = measurementsService.findById(id);
		uiModel.addAttribute("protocolMeasurement", protocolMeasurement);
		
		
		return "measurements/protocol";
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

		measurementsUpdaterService.saveMeasurements(
				measurementsForm.getModel(),
				measurementsForm.getSerialNumber(),
				measurementsForm.getMeasurand(), measurementsForm.getType(),
				measurementsForm.getScreenResolutions(),
				measurementsForm.getDescription(), measurementsForm.getUser());

		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute("measurementsForm", measurementsForm);
		redirectAttributes.addFlashAttribute(
				"message",
				new Message("success", messageSource.getMessage("measurements_save_success",
						new Object[] {}, locale)));

		return "redirect:/measurements";
	}

	// _________________Model Attribute_______________________________________
	@ModelAttribute
	public List<MeasurementsView> getMeasurementsView(
			ListOfMeasurementsViews listOfMeasurementsViews, Model uiModel) {

		System.out.println(listOfMeasurementsViews);
		List<MeasurementsView> measurementsViews = null;

		if (listOfMeasurementsViews.getMeasurementsViews().isEmpty()) {
			Long countOfMeasurements = measurementsService.count();
			System.out.println(countOfMeasurements);
			int numberOfLastPage = countOfMeasurements.intValue() / rowAtPage;
			if (countOfMeasurements == (numberOfLastPage * rowAtPage)) {
				numberOfLastPage--;
			}

			PageRequest pageRequest = null;
			pageRequest = new PageRequest(numberOfLastPage, rowAtPage);
			Page<Measurements> measurementsPage = measurementsService
					.findAllByPage(pageRequest);
			List<Measurements> measurements = Lists.newArrayList(measurementsPage.iterator());
			if ((measurements.size() < rowAtPage) && (numberOfLastPage > 0)) {
				numberOfLastPage--;
				pageRequest = new PageRequest(numberOfLastPage, rowAtPage);
				measurementsPage = measurementsService.findAllByPage(pageRequest);
				List<Measurements> newMeasurements = Lists.newArrayList(measurementsPage
						.iterator());
				Collections.reverse(measurements);
				Collections.reverse(newMeasurements);
				for (Measurements measurement : newMeasurements) {
					measurements.add(measurement);
				}
			} else {
				Collections.reverse(measurements);
			}
			listOfMeasurementsViews = new ListOfMeasurementsViews(measurements);
			measurementsViews = listOfMeasurementsViews.getMeasurementsViews();

			uiModel.addAttribute("listOfMeasurementsViews", listOfMeasurementsViews);
			uiModel.addAttribute("measurementsViews", measurementsViews);
			uiModel.addAttribute("currentPage", numberOfLastPage);
		} else {
			measurementsViews = listOfMeasurementsViews.getMeasurementsViews();
		}
		return measurementsViews;

	}

	@ModelAttribute("currentDate")
	public DateTime getCurrentDate() {
		return new DateTime();
	}

	// @ModelAttribute("page")
	// private Long getNumberOfLastPage(){
	// final Long rowAtPage = 20l;
	// Long countOfMeasurements = measurementsService.count();
	// Long numberOfLastPage = countOfMeasurements / rowAtPage;
	// if (countOfMeasurements > (numberOfLastPage*rowAtPage)) {
	// numberOfLastPage++;
	// }
	// return numberOfLastPage;
	// }

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

}
