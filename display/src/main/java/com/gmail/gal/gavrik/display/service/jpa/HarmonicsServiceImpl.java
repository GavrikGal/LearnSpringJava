package com.gmail.gal.gavrik.display.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.gal.gavrik.display.domain.Harmonics;
import com.gmail.gal.gavrik.display.repository.HarmonicsRepository;
import com.gmail.gal.gavrik.display.service.HarmonicsService;
import com.google.common.collect.Lists;

@Service("harmonicsService")
@Repository
@Transactional
public class HarmonicsServiceImpl implements HarmonicsService {

	@Autowired
	private HarmonicsRepository	harmonicsRepository;

	@Transactional(readOnly = true)
	public List<Harmonics> findAll() {
		return Lists.newArrayList(harmonicsRepository.findAll());
	}

	@Transactional(readOnly = true)
	public Harmonics findById(Long id) {
		return harmonicsRepository.findOne(id);
	}

	public Harmonics save(Harmonics harmonics) {
		return harmonicsRepository.save(harmonics);
	}

}
