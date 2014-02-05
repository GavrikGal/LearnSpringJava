package com.gmail.gal.gavrik.display.repository;

import org.springframework.data.repository.CrudRepository;

import com.gmail.gal.gavrik.display.domain.Measurements;

public interface MeasurementsRepository extends CrudRepository<Measurements, Long> {
}
