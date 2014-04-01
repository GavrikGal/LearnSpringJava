package com.gmail.gal.gavrik.display.service;

import java.util.List;

import com.gmail.gal.gavrik.display.domain.Equipments;
import com.gmail.gal.gavrik.display.domain.Models;

public interface EquipmentsService {

	public List<Equipments> findAll();

	public Equipments findById(Long id);

	public Equipments findBySerialNumberAndModel(String serialNumber, Models models);

	public Equipments save(Equipments equipments);
	
	public void delete(Equipments equipments);

}
