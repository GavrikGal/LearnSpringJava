package com.gmail.gal.gavrik.display.service;

import java.util.List;


import com.gmail.gal.gavrik.display.domain.Equipments;
import com.gmail.gal.gavrik.display.domain.Measurements;

public interface MeasurementsService {

	public List<Measurements> findAll();

	public Measurements findById(Long id);
	
//	public List<Measurements> findAllWithDetail();
	public List<Measurements> findByEquipment(Equipments equipments);

	public Measurements save(Measurements measurements);
	
	public void delete(Measurements measurements);

}
