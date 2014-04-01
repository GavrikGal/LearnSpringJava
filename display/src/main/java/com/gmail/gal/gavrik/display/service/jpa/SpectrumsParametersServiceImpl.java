package com.gmail.gal.gavrik.display.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.gal.gavrik.display.domain.Measurands;
//import com.gmail.gal.gavrik.display.domain.PurposeOfMeasurement;
import com.gmail.gal.gavrik.display.domain.ScreenResolutions;
import com.gmail.gal.gavrik.display.domain.SpectrumsParameters;
import com.gmail.gal.gavrik.display.domain.Types;
import com.gmail.gal.gavrik.display.repository.SpectrumsParametersRepository;
import com.gmail.gal.gavrik.display.service.SpectrumsParametersService;
import com.google.common.collect.Lists;

@Service("spectrumsParametersService")
@Repository
@Transactional
public class SpectrumsParametersServiceImpl implements SpectrumsParametersService {

	@Autowired
	private SpectrumsParametersRepository	spectrumsParametersRepository;

	@Transactional(readOnly = true)
	public List<SpectrumsParameters> findAll() {
		return Lists.newArrayList(spectrumsParametersRepository.findAll());
	}

	@Transactional(readOnly = true)
	public SpectrumsParameters findById(Long id) {
		return spectrumsParametersRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public SpectrumsParameters findWithDetail(Measurands measurands, Types types,
			ScreenResolutions screenResolutions/*
												 * , PurposeOfMeasurement
												 * purposeOfMeasurement
												 */) {
		return spectrumsParametersRepository.findByMeasurandAndTypeAndResolution(measurands,
				types, screenResolutions/* , purposeOfMeasurement */);
	}

	public SpectrumsParameters save(SpectrumsParameters spectrumsParameters) {
		SpectrumsParameters checkingParameters = spectrumsParametersRepository
				.findByMeasurandAndTypeAndResolution(spectrumsParameters.getMeasurand(),
						spectrumsParameters.getType(), spectrumsParameters.getResolution());
		if (checkingParameters == null) {
			return spectrumsParametersRepository.save(spectrumsParameters);
		} else {
			return checkingParameters;
		}
	}

}
