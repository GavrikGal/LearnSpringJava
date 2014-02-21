package com.gmail.gal.gavrik.display.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "purpose_of_measurement")
public class PurposeOfMeasurement implements Serializable {

	private static final long	serialVersionUID	= 3847060908637301976L;
	private Long				idPurposeOfMeasurement;
	private String				purpose;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_Purpose_of_measurement")
	public Long getIdPurposeOfMeasurement() {
		return idPurposeOfMeasurement;
	}

	public void setIdPurposeOfMeasurement(Long idPurposeOfMeasurement) {
		this.idPurposeOfMeasurement = idPurposeOfMeasurement;
	}

	@Column(name = "Purpose")
	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
