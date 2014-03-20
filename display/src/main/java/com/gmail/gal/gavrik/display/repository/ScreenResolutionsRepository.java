package com.gmail.gal.gavrik.display.repository;

import org.springframework.data.repository.CrudRepository;

import com.gmail.gal.gavrik.display.domain.ScreenResolutions;

public interface ScreenResolutionsRepository extends
		CrudRepository<ScreenResolutions, Long> {
	
	public ScreenResolutions findByResolution(String resolution);

}
