package com.gmail.gal.gavrik.display.service;

import java.util.List;

import com.gmail.gal.gavrik.display.domain.Models;

public interface ModelsService {
	
	public List<Models> findAll();
	
	public Models findById(Long id);

	public Models findByModelName(String modelName);
	
	public Models save(Models models);
	


}
