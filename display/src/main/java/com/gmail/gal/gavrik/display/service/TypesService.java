package com.gmail.gal.gavrik.display.service;

import java.util.List;

import com.gmail.gal.gavrik.display.domain.Types;

public interface TypesService {

	public List<Types> findAll();

	public Types findById(String id);

	public Types save(Types types);

}
