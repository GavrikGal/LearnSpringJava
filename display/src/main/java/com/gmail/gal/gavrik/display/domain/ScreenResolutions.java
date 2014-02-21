package com.gmail.gal.gavrik.display.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "screen_resolutions")
public class ScreenResolutions implements Serializable {

	private static final long			serialVersionUID	= 8281839097726999031L;
	private Long						idResolution;
	private String						resolution;
	private Set<SpectrumsParameters>	spectrumsParameters	= new HashSet<SpectrumsParameters>();

	@OneToMany(mappedBy = "resolution", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<SpectrumsParameters> getSpectrumsParameters() {
		return this.spectrumsParameters;
	}

	public void setSpectrumsParameters(Set<SpectrumsParameters> spectrumsParameters) {
		this.spectrumsParameters = spectrumsParameters;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Resolution")
	public Long getIdResolution() {
		return idResolution;
	}

	public void setIdResolution(Long idResolution) {
		this.idResolution = idResolution;
	}

	@Column(name = "Resolution")
	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String toString() {
		return resolution;
	}

}
