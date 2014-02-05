package com.gmail.gal.gavrik.display.domain;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "measurements")
public class Measurements implements Serializable {

	private static final long	serialVersionUID	= -1257165263393374914L;

	private Long				idMeasurements;
	private int					Equipment;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Measurements")
	public Long getIdMeasurements() {
		return idMeasurements;
	}
	
	@Column(name = "Equipment")
	public int getEquipment() {
		return Equipment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setIdMeasurements(Long idMeasurements) {
		this.idMeasurements = idMeasurements;
	}

	public void setEquipment(int equipment) {
		Equipment = equipment;
	}

	public String toString() {
		return "Measurements - Id: " + idMeasurements + ", Equipment: " + Equipment;
	}
}
