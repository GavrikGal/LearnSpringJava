package com.gmail.gal.gavrik.display.service.jpa;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.gal.gavrik.display.domain.ScreenResolutions;
import com.gmail.gal.gavrik.display.repository.ScreenResolutionsRepository;
import com.gmail.gal.gavrik.display.service.ScreenResolutionsService;
import com.google.common.collect.Lists;

@Service("screenResolutionsService")
@Repository
@Transactional
public class ScreenResolutionsServiceImpl implements ScreenResolutionsService {

	@Autowired
	private ScreenResolutionsRepository	screenResolutionsRepository;

	@Transactional(readOnly = true)
	public List<ScreenResolutions> findAll() {
		return Lists.newArrayList(screenResolutionsRepository.findAll());
	}

	@Transactional(readOnly = true)
	public ScreenResolutions findById(Long id) {
		return screenResolutionsRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public ScreenResolutions findByResolution(String resolution) {
		return screenResolutionsRepository.findByResolution(resolution);
	}
	
	
	public ScreenResolutions save(ScreenResolutions screenResolutions) {
		return screenResolutionsRepository.save(screenResolutions);
	}

	

}
