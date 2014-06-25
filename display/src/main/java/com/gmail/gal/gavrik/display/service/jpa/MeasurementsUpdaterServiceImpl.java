package com.gmail.gal.gavrik.display.service.jpa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.gmail.gal.gavrik.display.service.FileFinderService;
import com.gmail.gal.gavrik.display.service.HarmonicsService;
import com.gmail.gal.gavrik.display.service.MeasurandsService;
import com.gmail.gal.gavrik.display.service.MeasurementsService;
import com.gmail.gal.gavrik.display.service.MeasurementsUpdaterService;
import com.gmail.gal.gavrik.display.service.ModelsService;
import com.gmail.gal.gavrik.display.service.ScreenResolutionsService;
import com.gmail.gal.gavrik.display.service.SpectrumsParametersService;
import com.gmail.gal.gavrik.display.service.SpectrumsService;
import com.gmail.gal.gavrik.display.service.TypesService;
import com.gmail.gal.gavrik.display.service.jpa.CustomUserDetails.CustomUserDetails;

@Service("measurementsUpdaterService")
@Transactional
public class MeasurementsUpdaterServiceImpl implements
		MeasurementsUpdaterService {

	final Logger logger = LoggerFactory
			.getLogger(MeasurementsUpdaterServiceImpl.class);

	final String frequencyCellName = "�������, ���";
	final String amplitudeCellName = "��+�, �����/�";
	final String noiseCellName = "��, �����/�";
	final String receiverBandwidthCellName = "��, ���";

	final String rootPathName = "D:\\������";

	@Autowired
	private DateOfMeasurementService dateOfMeasurementService;

	@Autowired
	private ModelsService modelsService;

	@Autowired
	private EquipmentsService equipmentsService;

	@Autowired
	private MeasurementsService measurementsService;

	@Autowired
	private MeasurandsService measurandsService;

	@Autowired
	private TypesService typesService;

	@Autowired
	private ScreenResolutionsService screenResolutionsService;

	@Autowired
	private SpectrumsService spectrumsService;

	@Autowired
	private HarmonicsService harmonicsService;

	@Autowired
	private SpectrumsParametersService spectrumsParametersService;

	@Autowired
	private FileFinderService fileFinder;

	public void updateFromFolder() {
		try {

			// String rootPath = "D:\\������";

			List<File> fileList = fileFinder.findFiles(rootPathName,
					"[\\w[�-��-�]]+\\.(docx|doc)");

			for (File file : fileList) {

				// ��������� ������ �� ����� ���������
				// ��������� ���� ��������� � ����������
				File typeMeasurementsFolder = new File(file.getParent());
				String[] typeAndResolutions = typeMeasurementsFolder.getName()
						.split(" ");
				String measurandName = typeAndResolutions[0];
				String screenResolutionsNameOrType = typeAndResolutions[1];
				String typeName;
				String screenResolutionsName;
				if (screenResolutionsNameOrType != "���") {
					typeName = "��";
					screenResolutionsName = screenResolutionsNameOrType;
				} else {
					typeName = "���";
					screenResolutionsName = "";
				}
				System.out.println("Measurand: " + measurandName);
				System.out.println("Resolution: " + screenResolutionsName);
				System.out.println("Type: " + typeName);

				// ��������� ������ �������
				File modelMeasurementsFilder = new File(
						typeMeasurementsFolder.getParent());
				String modelName = modelMeasurementsFilder.getName();
				System.out.println("model: " + modelName);

				// ��������� ��������� ������
				String fileName = file.getName();
				fileName = fileName.trim();
				String serialNumber = fileName.substring(0,
						fileName.indexOf(".doc"));
				System.out.println("sn: " + serialNumber);

				String absoluteFileName = file.getAbsolutePath();

				if (!(absoluteFileName.endsWith(".doc") || absoluteFileName
						.endsWith(".docx"))) {
					throw new FileFormatException();
				} else {

				//	Measurements newMeasurements = new Measurements();

				//	setCurrentDateOfMeasurement(newMeasurements);
				//	setEquipment(newMeasurements, modelName, serialNumber);

					int frequencyCellNumber = -1;
					int amplitudeCellNumber = -1;
					int noiseCellNumber = -1;
					int receiverBandwidthCellNumber = -1;

					XWPFDocument document = new XWPFDocument(
							new FileInputStream(absoluteFileName));

					List<XWPFTable> tables = document.getTables();

					for (XWPFTable table : tables) {
						List<XWPFTableRow> rows = table.getRows();

						List<XWPFTableCell> headTable = rows.get(0)
								.getTableCells();
						for (int a = 0; a < headTable.size(); a++) {
							if (headTable.get(a).getText()
									.equalsIgnoreCase(frequencyCellName)) {
								frequencyCellNumber = a;
							}
							if (headTable.get(a).getText()
									.equalsIgnoreCase(amplitudeCellName)) {
								amplitudeCellNumber = a;
							}
							if (headTable.get(a).getText()
									.equalsIgnoreCase(noiseCellName)) {
								noiseCellNumber = a;
							}
							if (headTable
									.get(a)
									.getText()
									.equalsIgnoreCase(receiverBandwidthCellName)) {
								receiverBandwidthCellNumber = a;
							}
						}

						if (frequencyCellNumber == -1) {
							System.out.println("�� ������� ������� �������");
							break;
						}
						if (receiverBandwidthCellNumber == -1) {
							System.out
									.println("�� ������� ������� ������ �����������");
							break;
						}
						if (amplitudeCellNumber == -1) {
							System.out.println("�� ������� ������� ���������");
							break;
						}
						if (noiseCellNumber == -1) {
							System.out.println("�� ������� ������� ����");
							break;
						}

						for (int i = 1; i < rows.size(); i++) {
							List<XWPFTableCell> cells = rows.get(i)
									.getTableCells();
							System.out.print(cells.get(frequencyCellNumber)
									.getText() + "\t");
							System.out.print(cells.get(
									receiverBandwidthCellNumber).getText()
									+ "\t");
							System.out.print(cells.get(amplitudeCellNumber)
									.getText() + "\t");
							System.out.print(cells.get(noiseCellNumber)
									.getText());
							System.out.println();
						}
					}
				}

			}

		} catch (FileFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Measurements saveMeasurements(String modelName, String serialNumber,
			String measurandName, String typeName,
			String screenResolutionsName, String description, String user) {

		// ���� ������, ���� �� ���, �� ������� �� � ����� � ���
		Models model = getModel(modelName);

		// ���� ������� � ���� ������, ���� �� �������, �� ������� �����
		Equipments equipment = getEquipments(model, serialNumber);

		// ���� ������� ��������� � �� ���� ������� �����
		Measurements measurement = getMeasurements(equipment);

		// ������� �������� ��������� ������� �� ��, ��� ������� ��
		SpectrumsParameters spectrumsParameters = getSpectrumsParameters(
				measurandName, typeName, screenResolutionsName);

		// ���� ������ � ��, ��� ������� �����
		Spectrums spectrum = getSpectrums(measurement, spectrumsParameters);

		// �������� � ������ �������� �� ��������. ������ �������� �� �������
		// ��������, ���������� ����� �������� ��� ��������
		String newDescription = setHarmonicsFromDescription(spectrum,
				description);
		
		// ��������� ��������
		setDescription(spectrum, newDescription);

		return measurement;
	}

	// ��������� ������� ���� �� �� ���� �������� ����� ������ � ��
	private DateOfMeasurement getCurrentDateOfMeasurement() {
		DateTime currentDate = new DateTime();
		DateOfMeasurement currentDateOfMeasurement = new DateOfMeasurement();
		currentDateOfMeasurement.setDate(currentDate.withTime(0, 0, 0, 0));
		currentDateOfMeasurement = dateOfMeasurementService
				.save(currentDateOfMeasurement);
		return currentDateOfMeasurement;
	}

	// �������� ������ �� ��, ���� �� ��� ��� �� ������� �������
	private Models getModel(String modelName) {
		Models model = findModel(modelName);
		if (model == null) {
			logger.info("Trying create new models");
			try {
				model = createNewModel(modelName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return model;
	}

	// ����� ������ ������� � ����
	private Models findModel(String modelName) {
		modelName = modelName.trim();
		Models model = modelsService.findByModelName(modelName);
		logger.info("Model found in the database. Id: " + model.getIdModel());
		return model;
	}

	// �������� ����� ������ � ����
	private Models createNewModel(String modelName) {
		modelName = modelName.trim();
		Models model = new Models();
		model.setModelName(modelName);
		model = modelsService.save(model);

		// ��������� ������� ����� ������, ���� �� ������ �� ������� �� �������
		try {
			List<File> searchModelFolder = fileFinder.findDirectories(
					rootPathName, modelName);

			if (searchModelFolder.isEmpty()) {
				File modelFolder = new File(rootPathName + File.separator
						+ modelName);
				modelFolder.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	// �������� ������� �� ��, ���� ��� ��� ��� �� ������� �������
	private Equipments getEquipments(Models model, String serialNumber) {
		Equipments equipment = new Equipments();
		equipment.setModel(model);
		equipment.setSerialNumber(serialNumber);
		equipment = equipmentsService.save(equipment);
		if (equipment.getMeasurements() == null) {
			equipment.setMeasurements(new HashSet<Measurements>());
		}
		return equipment;
	}

	// ��������� ���������� ��������� ��� �������� ������� � ������� ����
	// �����������
	private Measurements getMeasurements(Equipments equipment) {

		DateOfMeasurement currentDateOfMeasurement = getCurrentDateOfMeasurement();
		Measurements measurement;
		// setCurrentDateOfMeasurement(measurement);

		Users user = null;

		if (SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal() != "anonymousUser") {
			user = ((CustomUserDetails) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal()).getUsersDetails();
		}

		List<Measurements> measurementsList = measurementsService
				.findByEquipment(equipment);

		if (measurementsList.isEmpty()) {
			measurement = new Measurements();
			measurement.setDateOfMeasurement(currentDateOfMeasurement);
			measurement.setEquipment(equipment);
			measurement.setSpectrums(new ArrayList<Spectrums>());
			measurement = measurementsService.save(measurement);
			equipment.getMeasurements().add(measurement);
		} else {
			Measurements lastMeasurements = measurementsList
					.get(measurementsList.size() - 1);
			if (lastMeasurements.getDateOfMeasurement().getIdDate() == currentDateOfMeasurement
					.getIdDate()) {
				measurement = lastMeasurements;
			} else {
				if (lastMeasurements.getDateOfSecondMeasurement() == null) {
					lastMeasurements
							.setDateOfSecondMeasurement(currentDateOfMeasurement);
					measurement = lastMeasurements;
				} else {
					if (lastMeasurements.getDateOfSecondMeasurement()
							.getIdDate() == currentDateOfMeasurement
							.getIdDate()) {
						measurement = lastMeasurements;
					} else {
						measurement = new Measurements();
						measurement
								.setDateOfMeasurement(currentDateOfMeasurement);
						measurement.setEquipment(equipment);
						measurement.setSpectrums(new ArrayList<Spectrums>());
						measurement = measurementsService.save(measurement);
						equipment.getMeasurements().add(measurement);
					}
				}
			}
		}
		if (user != null) {
			measurement.setUser(user);
		}

		if (measurement.getIdMeasurements() == null) {
			System.out
					.println("�������. � ��� ��������. IdMeasurements == null");
		}
		return measurement;
	}

	// �������� ��������� ������� �� �� ��� ������� �����
	private SpectrumsParameters getSpectrumsParameters(String measurandName,
			String typeName, String screenResolutionsName) {
		Measurands measurand = measurandsService.findById(measurandName);
		if (measurand == null) {
			logger.error("�� ������ ��������: measurand = " + measurandName
					+ ". ������� ��� � ��");
		}
		ScreenResolutions screenResolution = getScreenResolutions(screenResolutionsName);
		Types type = typesService.findById(typeName);
		if (type == null) {
			logger.error("�� ������ ��������: type = " + typeName
					+ ". ������� ��� � ��");
		}

		SpectrumsParameters spectrumsParameters = new SpectrumsParameters();
		spectrumsParameters.setMeasurand(measurand);
		spectrumsParameters.setResolution(screenResolution);
		spectrumsParameters.setType(type);
		spectrumsParameters.setSpectrums(new HashSet<Spectrums>());

		spectrumsParameters = spectrumsParametersService
				.save(spectrumsParameters);
		return spectrumsParameters;
	}

	// ��������� ���������� ������, ���� ��� ���
	private ScreenResolutions getScreenResolutions(String screenResolutionsName) {
		screenResolutionsName = screenResolutionsName.trim();
		ScreenResolutions screenResolution = screenResolutionsService
				.findByResolution(screenResolutionsName);
		if (screenResolution == null) {
			screenResolution = new ScreenResolutions();
			screenResolution.setResolution(screenResolutionsName);
			screenResolution
					.setSpectrumsParameters(new HashSet<SpectrumsParameters>());
			screenResolution = screenResolutionsService.save(screenResolution);
		}
		return screenResolution;
	}

	// �������� ����� �� �� ��� ������� �����
	private Spectrums getSpectrums(Measurements measurement,
			SpectrumsParameters spectrumsParameters) {

		Spectrums spectrum = spectrumsService
				.findByMeasurementAndSpectrumParameters(measurement,
						spectrumsParameters);

		if (spectrum == null) {
			spectrum = new Spectrums();
			spectrum.setMeasurement(measurement);
			spectrum.setSpectrumParameters(spectrumsParameters);
			spectrum.setHarmonics(new ArrayList<Harmonics>());
		}
		DateTime currentDate = new DateTime();
		spectrum.setTime(new Time(currentDate.getMillis()));

		spectrum = spectrumsService.save(spectrum);
		return spectrum;
	}

	// ������ �������� �� ������� �������� � ������������� �� � ������
	private String setHarmonicsFromDescription(Spectrums spectrum,
			String description) {

		DescriptionForParsing newDescription = new DescriptionForParsing(
				description);
		final Double deviationFrequency = 0.02;

		Double frequency, amplitude, noise;

		while (!newDescription.isString()) {

			frequency = newDescription.parseFrequency();
			if (frequency != null) {
				Harmonics newHarmonics = new Harmonics();
				List<Harmonics> oldHarmonics = spectrum.getHarmonics();
				for (Harmonics harmonics : oldHarmonics) {
					if (((harmonics.getFrequency() + deviationFrequency
							* harmonics.getFrequency()) > frequency)
							&& ((harmonics.getFrequency() - deviationFrequency
									* harmonics.getFrequency()) < frequency)) {
						newHarmonics = harmonics;
					}
				}
				newHarmonics.setFrequency(frequency);
				newHarmonics.setReceiverBandwidth(30.0);
				amplitude = newDescription.parseAmplitude();
				if (amplitude != null) {
					newHarmonics.setAmplitude(amplitude);
					noise = newDescription.parseNoise();
					if (noise == null) {
						noise = 0.0;
					}
					newHarmonics.setNoise(noise);
					newHarmonics.setSpectrum(spectrum);
					harmonicsService.save(newHarmonics);
				}
			} else {
				break;
			}
		}

		description = newDescription.getDescription();
		return description;
	}
	
	// ��������� �������� � ������� � ���� ����, �� ��������� �����
	private void setDescription(Spectrums spectrum, String description) {
		if (description != null) {
			if (spectrum.getDescription() == null) {
				spectrum.setDescription(description);
			} else {
				if (spectrum.getDescription().isEmpty()) {
					spectrum.setDescription(description);
				} else {
					if (!(spectrum.getDescription().contains(description))) {
						spectrum.setDescription(spectrum.getDescription()
								+ "; " + description);
					} 
				}
			}
			spectrum = spectrumsService.save(spectrum);
		}
	}

}
