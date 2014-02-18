package com.gmail.gal.gavrik.display.domain;

import java.io.Serializable;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "spectrums")
public class Spectrums implements Serializable {

	private static final long	serialVersionUID	= 822452653650724060L;

	private Long				idSpectrums;
	private Measurements		measurement;
	private SpectrumsParameters	spectrumParameters;
	private DateOfMeasurement	date;
	private Time				time;
	private Set<Harmonics>		harmonics			= new HashSet<Harmonics>();

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@OneToMany(mappedBy = "spectrum", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Harmonics> getHarmonics() {
		return this.harmonics;
	}

	public void setHarmonics(Set<Harmonics> harmonics) {
		this.harmonics = harmonics;
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

	@ManyToOne
	@JoinColumn(name = "Spectrum_parameters")
	public SpectrumsParameters getSpectrumParameters() {
		return spectrumParameters;
	}

	public void setSpectrumParameters(SpectrumsParameters spectrumParameters) {
		this.spectrumParameters = spectrumParameters;
	}

	@ManyToOne
	@JoinColumn(name = "Date")
	public DateOfMeasurement getDate() {
		return date;
	}

	public void setDate(DateOfMeasurement date) {
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
