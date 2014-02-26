package com.gmail.gal.gavrik.display.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "norms")
public class Norms implements Serializable {

	private static final long	serialVersionUID	= 9089008554064871620L;
	private Long				idNorms;
	private String				ShortNorms;
	private Set<Models> 		models;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Norms")
	public Long getIdNorms() {
		return idNorms;
	}

	public void setIdNorms(Long idNorms) {
		this.idNorms = idNorms;
	}

	@Column(name = "Short_norms")
	public String getShortNorms() {
		return ShortNorms;
	}

	public void setShortNorms(String shortNorms) {
		ShortNorms = shortNorms;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@ManyToMany
	@JoinTable(name = "norms_of_model", joinColumns = @JoinColumn(name = "Norms"), inverseJoinColumns = @JoinColumn(name = "Model"))
	public Set<Models> getModels() {
		return models;
	}

	public void setModels(Set<Models> models) {
		this.models = models;
	}
	
	

}
