package com.gmail.gal.gavrik.display.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.gal.gavrik.display.domain.Types;
import com.gmail.gal.gavrik.display.repository.TypesRepository;
import com.gmail.gal.gavrik.display.service.TypesService;
import com.google.common.collect.Lists;

@Service("typesService")
@Repository
@Transactional
public class TypesServiceImpl implements TypesService {

	@Autowired
	private TypesRepository	typesRepository;

	@Transactional(readOnly = true)
	public List<Types> findAll() {
		return Lists.newArrayList(typesRepository.findAll());
	}

	@Transactional(readOnly = true)
	public Types findById(String id) {
		return typesRepository.findOne(id);
	}

	public Types save(Types types) {
		return typesRepository.save(types);
	}

}
