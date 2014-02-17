package com.gmail.gal.gavrik.display.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "spectrums_parameters")
public class SpectrumsParameters implements Serializable {

	private static final long	serialVersionUID	= -8217254806166488713L;
	private Long				idSpectrumParameters;
	private Measurands			measurand;
	private Types				type;
	private ScreenResolutions	resolution;
	private Set<Spectrums>		spectrums			= new HashSet<Spectrums>();
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Spectrum_parameters")
	public Long getIdSpectrumParameters() {
		return idSpectrumParameters;
	}

	public void setIdSpectrumParameters(Long idSpectrumParameters) {
		this.idSpectrumParameters = idSpectrumParameters;
	}

	@ManyToOne
	@JoinColumn(name = "Measurand")
	public Measurands getMeasurand() {
		return measurand;
	}

	public void setMeasurand(Measurands measurand) {
		this.measurand = measurand;
	}

	@ManyToOne
	@JoinColumn(name = "Type")
	public Types getType() {
		return type;
	}

	public void setType(Types type) {
		this.type = type;
	}

	@ManyToOne
	@JoinColumn(name = "Resolution")
	public ScreenResolutions getResolution() {
		return resolution;
	}

	public void setResolution(ScreenResolutions resolution) {
		this.resolution = resolution;
	}

	@OneToMany(mappedBy = "spectrumParameters", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Spectrums> getSpectrums() {
		return this.spectrums;
	}

	public void setSpectrums(Set<Spectrums> spectrums) {
		this.spectrums = spectrums;
	}

	
	
}
