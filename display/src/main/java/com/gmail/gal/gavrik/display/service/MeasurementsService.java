package com.gmail.gal.gavrik.display.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.gmail.gal.gavrik.display.domain.Equipments;
import com.gmail.gal.gavrik.display.domain.Measurements;

public interface MeasurementsService {

	public List<Measurements> findAll();
	
	public Page<Measurements> findAllByPage(Pageable pageable);

	public Measurements findById(Long id);
	
//	public List<Measurements> findAllWithDetail();
	public List<Measurements> findByEquipment(Equipments equipments);

	public Measurements save(Measurements measurements);
	
	public void delete(Measurements measurements);
	
	public Long count();

}
