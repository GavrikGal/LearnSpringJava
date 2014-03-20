package com.gmail.gal.gavrik.display.service.jpa;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.gal.gavrik.display.domain.DateOfMeasurement;
import com.gmail.gal.gavrik.display.repository.DateOfMeasurementRepository;
import com.gmail.gal.gavrik.display.service.DateOfMeasurementService;
import com.google.common.collect.Lists;

@Service("dateOfMeasurementService")
@Repository
@Transactional
public class DateOfMeasurementServiceImpl implements DateOfMeasurementService {

	@Autowired
	private DateOfMeasurementRepository	dateOfMeasurementRepository;

	@Transactional(readOnly = true)
	public List<DateOfMeasurement> findAll() {
		return Lists.newArrayList(dateOfMeasurementRepository.findAll());
	}

	@Transactional(readOnly = true)
	public DateOfMeasurement findById(Long id) {
		return dateOfMeasurementRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public DateOfMeasurement findByDate(DateTime date) {
		return dateOfMeasurementRepository.findByDate(date);
	}

	public DateOfMeasurement save(DateOfMeasurement dateOfMeasurement) {
		if (dateOfMeasurement != null) {
			DateOfMeasurement checkingDateOfMeasurement = dateOfMeasurementRepository
					.findByDate(dateOfMeasurement.getDate());
			if (checkingDateOfMeasurement == null) {
				return dateOfMeasurementRepository.save(dateOfMeasurement);
			} else {
				return checkingDateOfMeasurement;
			}
		} else {
			return null;
		}
	}

}
