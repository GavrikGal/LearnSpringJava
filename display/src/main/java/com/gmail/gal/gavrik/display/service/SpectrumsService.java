package com.gmail.gal.gavrik.display.service;

import java.util.List;

import com.gmail.gal.gavrik.display.domain.Measurements;
import com.gmail.gal.gavrik.display.domain.Spectrums;
import com.gmail.gal.gavrik.display.domain.SpectrumsParameters;

public interface SpectrumsService {

	public List<Spectrums> findAll();

	public Spectrums findById(Long id);

	public Spectrums findByMeasurementAndSpectrumParameters(Measurements measurements,
			SpectrumsParameters spectrumsParameters);

	public Spectrums save(Spectrums spectrums);

}
