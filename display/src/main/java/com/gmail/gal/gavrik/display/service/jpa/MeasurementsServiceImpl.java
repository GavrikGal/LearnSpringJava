package com.gmail.gal.gavrik.display.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.gal.gavrik.display.domain.Measurements;
import com.gmail.gal.gavrik.display.repository.MeasurementsRepository;
import com.gmail.gal.gavrik.display.service.MeasurementsService;
import com.google.common.collect.Lists;

@Service("measurementsService")
@Repository
@Transactional
public class MeasurementsServiceImpl implements MeasurementsService {

	@Autowired
	private MeasurementsRepository measurementsRepository;
	
	@Transactional(readOnly=true)
	public List<Measurements> findAll() {
		return Lists.newArrayList(measurementsRepository.findAll());
	}

	@Transactional(readOnly=true)
	public Measurements findById(Long id) {
		return measurementsRepository.findOne(id);
	}

	public Measurements save(Measurements measurements) {
		return measurementsRepository.save(measurements);
	}

}
