package com.gmail.gal.gavrik.display.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.gal.gavrik.display.domain.Equipments;
import com.gmail.gal.gavrik.display.domain.Models;
import com.gmail.gal.gavrik.display.repository.EquipmentsRepository;
import com.gmail.gal.gavrik.display.service.EquipmentsService;
import com.google.common.collect.Lists;

@Service("equipmentsService")
@Repository
@Transactional
public class EquipmentsServiceImpl implements EquipmentsService {

	@Autowired
	private EquipmentsRepository	equipmentsRepository;

	@Transactional(readOnly = true)
	public List<Equipments> findAll() {
		return Lists.newArrayList(equipmentsRepository.findAll());
	}

	@Transactional(readOnly = true)
	public Equipments findById(Long id) {
		return equipmentsRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public Equipments findBySerialNumberAndModel(String serialNumber, Models models) {
		return equipmentsRepository.findBySerialNumberAndModel(serialNumber, models);
	}

	public Equipments save(Equipments equipments) {
		Equipments checkingEquipments = equipmentsRepository.findBySerialNumberAndModel(
				equipments.getSerialNumber(), equipments.getModel());
		if (checkingEquipments == null) {
			return equipmentsRepository.save(equipments);
		} else {
			return checkingEquipments;
		}
	}

	public void delete(Equipments equipments) {
		equipmentsRepository.delete(equipments);		
	}

}
