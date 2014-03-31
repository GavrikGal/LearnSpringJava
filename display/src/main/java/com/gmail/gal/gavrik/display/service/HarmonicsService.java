package com.gmail.gal.gavrik.display.service;

import java.util.List;

import com.gmail.gal.gavrik.display.domain.Harmonics;

public interface HarmonicsService {

	public List<Harmonics> findAll();

	public Harmonics findById(Long id);

	public Harmonics save(Harmonics harmonics);

}
