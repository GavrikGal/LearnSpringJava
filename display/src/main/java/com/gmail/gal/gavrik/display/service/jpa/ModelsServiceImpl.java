package com.gmail.gal.gavrik.display.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.gal.gavrik.display.domain.Models;
import com.gmail.gal.gavrik.display.repository.ModelsRepository;
import com.gmail.gal.gavrik.display.service.ModelsService;
import com.google.common.collect.Lists;

@Service("modelsService")
@Repository
@Transactional
public class ModelsServiceImpl implements ModelsService {

	@Autowired
	private ModelsRepository modelsRepository;
	
	@Transactional(readOnly=true)
	public List<Models> findAll() {
		return Lists.newArrayList(modelsRepository.findAll());
	}

	@Transactional(readOnly=true)
	public Models findById(Long id) {
		return modelsRepository.findOne(id);
	}

	public Models save(Models model) {
		return modelsRepository.save(model);
	}

}
