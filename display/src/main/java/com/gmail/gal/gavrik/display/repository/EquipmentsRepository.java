package com.gmail.gal.gavrik.display.repository;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;

import com.gmail.gal.gavrik.display.domain.Equipments;
import com.gmail.gal.gavrik.display.domain.Models;

public interface EquipmentsRepository extends CrudRepository<Equipments, Long> {

	//@Query("select e from Equipments e where e.model = :models and e.serialNumber = :serialNumber")
	public Equipments findBySerialNumberAndModel(/*@Param("serialNumber") */String serialNumber,
			/*@Param("models")*/ Models model);

}
