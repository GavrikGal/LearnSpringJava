package com.gmail.gal.gavrik.display.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "types")
public class Types implements Serializable {

	private static final long			serialVersionUID	= -1529433046273888216L;
	private String						idType;
	private Set<SpectrumsParameters>	spectrumsParameters	= new HashSet<SpectrumsParameters>();

	@OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
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
	@Column(name = "id_Type")
	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String toString() {
		return idType;
	}

}
