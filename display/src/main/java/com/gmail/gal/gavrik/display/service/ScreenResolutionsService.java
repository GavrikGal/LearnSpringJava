package com.gmail.gal.gavrik.display.service;

import java.util.List;

import com.gmail.gal.gavrik.display.domain.ScreenResolutions;

public interface ScreenResolutionsService {

	public List<ScreenResolutions> findAll();

	public ScreenResolutions findById(Long id);
	
	public ScreenResolutions findByResolution(String resolution);

	public ScreenResolutions save(ScreenResolutions screenResolutions);

}
