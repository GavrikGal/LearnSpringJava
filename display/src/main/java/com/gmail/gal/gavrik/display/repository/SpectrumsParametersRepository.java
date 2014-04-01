package com.gmail.gal.gavrik.display.repository;


import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;

import com.gmail.gal.gavrik.display.domain.Measurands;
//import com.gmail.gal.gavrik.display.domain.PurposeOfMeasurement;
import com.gmail.gal.gavrik.display.domain.ScreenResolutions;
import com.gmail.gal.gavrik.display.domain.SpectrumsParameters;
import com.gmail.gal.gavrik.display.domain.Types;

public interface SpectrumsParametersRepository extends CrudRepository<SpectrumsParameters, Long> {
	
	
	//@Query("select e from Equipments e where e.model = :models and e.serialNumber = :serialNumber")
//	@Query("select s from SpectrumsParameters s where s.measurand = :measurand and s.type = :type and s.resolution = :resolution")// and s.purposeOfMeasurement = :purposeOfMeasurement")
//	public SpectrumsParameters findWithDetail(@Param("measurand") Measurands measurands,
//			@Param("type") Types types,
//			@Param("resolution") ScreenResolutions screenResolutions/*,
//			@Param("purposeOfMeasurement") PurposeOfMeasurement purposeOfMeasurement*/);
//	
	public SpectrumsParameters findByMeasurandAndTypeAndResolution(Measurands measurand,
			Types type, ScreenResolutions resolution);

}
