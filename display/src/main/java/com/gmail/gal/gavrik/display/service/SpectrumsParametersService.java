package com.gmail.gal.gavrik.display.service;

import java.util.List;



import com.gmail.gal.gavrik.display.domain.Measurands;
//import com.gmail.gal.gavrik.display.domain.PurposeOfMeasurement;
import com.gmail.gal.gavrik.display.domain.ScreenResolutions;
import com.gmail.gal.gavrik.display.domain.SpectrumsParameters;
import com.gmail.gal.gavrik.display.domain.Types;

public interface SpectrumsParametersService {

	public List<SpectrumsParameters> findAll();

	public SpectrumsParameters findById(Long id);

	public SpectrumsParameters findWithDetail(Measurands measurands, Types types,
			ScreenResolutions screenResolutions/*, PurposeOfMeasurement purposeOfMeasurement*/);

	public SpectrumsParameters save(SpectrumsParameters spectrumsParameters);
}
