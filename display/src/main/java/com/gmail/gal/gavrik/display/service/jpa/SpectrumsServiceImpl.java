package com.gmail.gal.gavrik.display.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.gal.gavrik.display.domain.Measurements;
import com.gmail.gal.gavrik.display.domain.Spectrums;
import com.gmail.gal.gavrik.display.domain.SpectrumsParameters;
import com.gmail.gal.gavrik.display.repository.SpectrumsRepository;
import com.gmail.gal.gavrik.display.service.SpectrumsService;
import com.google.common.collect.Lists;

@Service("spectrumsService")
@Repository
@Transactional
public class SpectrumsServiceImpl implements SpectrumsService {

	@Autowired
	private SpectrumsRepository	spectrumsRepository;

	@Transactional(readOnly = true)
	public List<Spectrums> findAll() {
		return Lists.newArrayList(spectrumsRepository.findAll());
	}

	@Transactional(readOnly = true)
	public Spectrums findById(Long id) {
		return spectrumsRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public Spectrums findByMeasurementAndSpectrumParameters(Measurements measurements,
			SpectrumsParameters spectrumsParameters) {
		return spectrumsRepository.findByMeasurementAndSpectrumParameters(measurements,
				spectrumsParameters);
	}

	public Spectrums save(Spectrums spectrums) {
		return spectrumsRepository.save(spectrums);
	}

}
