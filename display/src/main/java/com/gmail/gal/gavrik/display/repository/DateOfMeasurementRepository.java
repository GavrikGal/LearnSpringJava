package com.gmail.gal.gavrik.display.repository;

import org.joda.time.DateTime;

import org.springframework.data.repository.CrudRepository;



import com.gmail.gal.gavrik.display.domain.DateOfMeasurement;

public interface DateOfMeasurementRepository extends CrudRepository<DateOfMeasurement, Long> {
	
	//@Query("select d from DateOfMeasurement d where d.date = :date")
	public DateOfMeasurement findByDate(/*@Param("date")*/ DateTime date);

}
