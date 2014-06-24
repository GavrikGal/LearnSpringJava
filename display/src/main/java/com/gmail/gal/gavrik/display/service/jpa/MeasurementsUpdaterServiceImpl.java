package com.gmail.gal.gavrik.display.service.jpa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.joda.time.DateTime;
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
	
	final String frequencyCellName = "Частота, МГц";
	final String amplitudeCellName = "Ес+п, дБмкВ/м";
	final String noiseCellName = "Еп, дБмкВ/м";
	final String receiverBandwidthCellName = "ПП, кГц";

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
			
			String rootPath = "D:\\Данные";
			
			List<File> fileList = fileFinder.findFiles(rootPath, "[\\w[а-яА-Я]]+\\.(docx|doc)");
			
			for (File file : fileList) {
				
				// получение данных из путей каталогов
				// получение типа измерения и разрешения
				File typeMeasurementsFolder = new File(file.getParent());
				String[] typeAndResolutions = typeMeasurementsFolder.getName().split(" ");
				for (String string : typeAndResolutions) {
					System.out.println(string);
				}
				
				// получение модели изделия				
				File modelMeasurementsFilder = new File(typeMeasurementsFolder.getParent());
				System.out.println("model: " + modelMeasurementsFilder.getName());
				
				// получение серийного номера
				String fileName = file.getName();
				fileName = fileName.trim();
				System.out.println("sn: " + fileName.substring(0, fileName.indexOf(".doc")));

				String absoluteFileName = file.getAbsolutePath();

				if (!(absoluteFileName.endsWith(".doc") || absoluteFileName.endsWith(".docx"))) {
					throw new FileFormatException();
				} else {
					
					int frequencyCellNumber = -1;
					int amplitudeCellNumber = -1;
					int noiseCellNumber = -1;
					int receiverBandwidthCellNumber = -1;

					XWPFDocument document = new XWPFDocument(new FileInputStream(
							absoluteFileName));

					List<XWPFTable> tables = document.getTables();

					for (XWPFTable table : tables) {
						List<XWPFTableRow> rows = table.getRows();
						
						List<XWPFTableCell> headTable = rows.get(0).getTableCells();
						for (int a = 0 ; a< headTable.size(); a++) {
							if (headTable.get(a).getText().equalsIgnoreCase(frequencyCellName)) {
								frequencyCellNumber = a;
							}
							if (headTable.get(a).getText().equalsIgnoreCase(amplitudeCellName)) {
								amplitudeCellNumber = a;
							}
							if (headTable.get(a).getText().equalsIgnoreCase(noiseCellName)) {
								noiseCellNumber = a;
							}
							if (headTable.get(a).getText().equalsIgnoreCase(receiverBandwidthCellName)) {
								receiverBandwidthCellNumber = a;
							}
						}
						
						if (frequencyCellNumber == -1) {
							System.out.println("Не найдено колонки частоты");
							break;
						} 
						if (receiverBandwidthCellNumber == -1 ) {
							System.out.println("Не найдено колонки полосы пропускания");
							break;
						}
						if (amplitudeCellNumber == -1 ) {
							System.out.println("Не найдено колонки амплитуды");
							break;
						}
						if (noiseCellNumber == -1 ) {
							System.out.println("Не найдено колонки шума");
							break;
						}
						
						
						for (int i = 1; i < rows.size(); i++) {
							List<XWPFTableCell> cells = rows.get(i).getTableCells();
							System.out.print(cells.get(frequencyCellNumber).getText()+ "\t");
							System.out.print(cells.get(receiverBandwidthCellNumber).getText()+ "\t");
							System.out.print(cells.get(amplitudeCellNumber).getText()+ "\t");
							System.out.print(cells.get(noiseCellNumber).getText());
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Measurements saveMeasurements(String modelName, String serialNumber,
			String measurandName, String typeName,
			String screenResolutionsName, String description, String user) {
		Measurements newMeasurements = new Measurements();

		setCurrentDateOfMeasurement(newMeasurements);
		setEquipment(newMeasurements, modelName, serialNumber);

		newMeasurements = saveMeasurement(newMeasurements);

		saveSpectrum(newMeasurements, measurandName, typeName,
				screenResolutionsName, description);

		return newMeasurements;
	}

	private void setCurrentDateOfMeasurement(Measurements newMeasurements) {
		DateOfMeasurement newDateOfMeasurement = new DateOfMeasurement();
		DateTime currentDate = new DateTime();
		newDateOfMeasurement.setDate(currentDate.withTime(0, 0, 0, 0));
		newDateOfMeasurement = dateOfMeasurementService
				.save(newDateOfMeasurement);
		newMeasurements.setDateOfMeasurement(newDateOfMeasurement);
	}

	private void setEquipment(Measurements newMeasurements, String modelName,
			String serialNumber) {
		Equipments newEquipments = new Equipments();
		Models model = new Models();
		model = modelsService.findByModelName(modelName);

		newEquipments.setModel(model);
		newEquipments.setSerialNumber(serialNumber);
		newEquipments = equipmentsService.save(newEquipments);

		newMeasurements.setEquipment(newEquipments);
	}

	private Measurements saveMeasurement(Measurements newMeasurements) {
		List<Measurements> measurementsList = measurementsService
				.findByEquipment(newMeasurements.getEquipment());

		Users user = ((CustomUserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal()).getUsersDetails();

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

	private void saveSpectrum(Measurements newMeasurements,
			String measurandName, String typeName,
			String screenResolutionsName, String description) {
		Spectrums newSpectrums = new Spectrums();
		SpectrumsParameters newSpectrumsParameters = new SpectrumsParameters();
		Measurands measurands = measurandsService.findById(measurandName);

		newSpectrumsParameters.setMeasurand(measurands);

		ScreenResolutions screenResolutions = screenResolutionsService
				.findByResolution(screenResolutionsName);

		newSpectrumsParameters.setResolution(screenResolutions);

		Types type = typesService.findById(typeName);
		newSpectrumsParameters.setType(type);

		newSpectrumsParameters = spectrumsParametersService
				.save(newSpectrumsParameters);

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

		// //////////////////////// Description!!!!!!!?????
		newSpectrums = spectrumsService.save(newSpectrums);
		String newDescription = setHarmonics(description, newSpectrums);
		if (newSpectrums.getDescription() == null) {
			newSpectrums.setDescription(newDescription);
		} else {
			if (newSpectrums.getDescription().isEmpty()) {
				newSpectrums.setDescription(newDescription);
			} else {
				newSpectrums.setDescription(newSpectrums.getDescription()
						+ "; " + newDescription);
			}
		}
		// newSpectrums.setDescription(description);
		newSpectrums = spectrumsService.save(newSpectrums);
	}

	private String setHarmonics(String description, Spectrums newSpectrums) {

		DescriptionForParsing newDescription = new DescriptionForParsing(
				description);
		final Double deviationFrequency = 0.02;

		Double frequency, amplitude, noise;

		while (!newDescription.isString()) {

			frequency = newDescription.parseFrequency();
			if (frequency != null) {
				Harmonics newHarmonics = new Harmonics();
				List<Harmonics> oldHarmonics = newSpectrums.getHarmonics();
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
