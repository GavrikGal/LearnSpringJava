package com.gmail.gal.gavrik.display.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "equipments")
public class Equipments implements Serializable {

	private static final long	serialVersionUID	= 7277923833960735466L;

	private Long				idEquipment;
	private Models				model;
	private String				serialNumber;
	private Set<Measurements>	measurements		= new HashSet<Measurements>();

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Equipment")
	public Long getIdEquipment() {
		return idEquipment;
	}

	public void setIdEquipment(Long idEquipment) {
		this.idEquipment = idEquipment;
	}

	@ManyToOne
	@JoinColumn(name = "Model")
	public Models getModel() {
		return model;
	}

	public void setModel(Models model) {
		this.model = model;
	}

	@Column(name = "Serial_number")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Measurements> getMeasurements() {
		return this.measurements;
	}

	public void setMeasurements(Set<Measurements> measurements) {
		this.measurements = measurements;
	}

}
