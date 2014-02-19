package com.gmail.gal.gavrik.display.service;

import java.util.List;


import com.gmail.gal.gavrik.display.domain.Measurements;

public interface MeasurementsService {

	public List<Measurements> findAll();

	public Measurements findById(Long id);
	
//	public List<Measurements> findAllWithDetail();

	public Measurements save(Measurements measurements);

}
