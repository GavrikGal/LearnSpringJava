package com.gmail.gal.gavrik.display.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gmail.gal.gavrik.display.domain.Equipments;
import com.gmail.gal.gavrik.display.domain.Measurements;

public interface MeasurementsRepository extends PagingAndSortingRepository<Measurements, Long> {

	// @Query("select distinct m from Measurements m left join fetch m.users u join fetch m.equipment e join fetch m.spectrums s join fetch e.model mo join fetch s.harmonics h join fetch s.date d")
	// @Query("select distinct m from Measurements m")
	// public List<Measurements> findAllWithDetail();

	// @Query("select d from DateOfMeasurement d where d.date = :date")
	public List<Measurements> findByEquipment(Equipments equipments);
	
	//public Iterable<Measurements> findAllOrderByIdMeasurements();

}
