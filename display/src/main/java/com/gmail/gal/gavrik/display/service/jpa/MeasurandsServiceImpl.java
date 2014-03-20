package com.gmail.gal.gavrik.display.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.gal.gavrik.display.domain.Measurands;
import com.gmail.gal.gavrik.display.repository.MeasurandsRepository;
import com.gmail.gal.gavrik.display.service.MeasurandsService;
import com.google.common.collect.Lists;

@Service("measurandsService")
@Repository
@Transactional
public class MeasurandsServiceImpl implements MeasurandsService {

	@Autowired
	private MeasurandsRepository measurandsRepository;
	
	@Transactional(readOnly=true)
	public List<Measurands> findAll() {
		return Lists.newArrayList(measurandsRepository.findAll());
	}

	@Transactional(readOnly=true)
	public Measurands findById(String id) {
		return measurandsRepository.findOne(id);
	}

	@Override
	public Measurands save(Measurands measurands) {
		return measurandsRepository.save(measurands);
	}

}
