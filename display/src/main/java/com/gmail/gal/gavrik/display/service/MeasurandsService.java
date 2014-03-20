package com.gmail.gal.gavrik.display.service;

import java.util.List;

import com.gmail.gal.gavrik.display.domain.Measurands;


public interface MeasurandsService {
	
	public List<Measurands> findAll();
	
	public Measurands findById(String id);
	
	public Measurands save(Measurands models);

}
