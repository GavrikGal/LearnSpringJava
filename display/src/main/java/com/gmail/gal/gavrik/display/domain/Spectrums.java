package com.gmail.gal.gavrik.display.domain;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.*;

@Entity
@Table(name = "spectrums")
public class Spectrums implements Serializable {

	private static final long	serialVersionUID	= 822452653650724060L;

	private Long				idSpectrums;
	private Measurements		measurement;
	private Long				spectrumParameters;
	private Long				date;
	private Time				time;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Spectrums")
	public Long getIdSpectrums() {
		return idSpectrums;
	}

	public void setIdSpectrums(Long idSpectrums) {
		this.idSpectrums = idSpectrums;
	}

	@ManyToOne
	@JoinColumn(name = "Measurement")
	public Measurements getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Measurements measurement) {
		this.measurement = measurement;
	}

	@Column(name = "Spectrum_parameters")
	public Long getSpectrumParameters() {
		return spectrumParameters;
	}

	public void setSpectrumParameters(Long spectrumParameters) {
		this.spectrumParameters = spectrumParameters;
	}

	@Column(name = "Date")
	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	@Column(name = "Time")
	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

}
