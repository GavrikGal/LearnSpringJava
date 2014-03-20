package com.gmail.gal.gavrik.display.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gmail.gal.gavrik.display.domain.Measurements;
import com.gmail.gal.gavrik.display.domain.Spectrums;
import com.gmail.gal.gavrik.display.domain.SpectrumsParameters;

public interface SpectrumsRepository extends CrudRepository<Spectrums, Long> {

	@Query("select s from Spectrums s where s.spectrumParameters = :spectrumParameters and s.measurement = :measurement")
	public Spectrums findByMeasurementAndSpectrumParameters(
			@Param("measurement") Measurements measurement,
			@Param("spectrumParameters") SpectrumsParameters spectrumParameters);

}
