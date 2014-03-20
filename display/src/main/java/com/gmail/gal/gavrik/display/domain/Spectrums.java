package com.gmail.gal.gavrik.display.domain;

import java.io.Serializable;
import java.sql.Time;
//import java.util.ArrayList;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "spectrums")
public class Spectrums implements Serializable {

	private static final long	serialVersionUID	= 822452653650724060L;

	private Long				idSpectrums;
	private Measurements		measurement;
	private SpectrumsParameters	spectrumParameters;
	private String				description;
	private Time				time;
	private List<Harmonics>		harmonics;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@OneToMany(mappedBy = "spectrum", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("frequency")
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<Harmonics> getHarmonics() {
		return this.harmonics;
	}

	public void setHarmonics(List<Harmonics> harmonics) {
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
	@JoinColumn(name = "Measurements")
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

	@Column(name = "Time")
	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	@Column(name = "Description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
