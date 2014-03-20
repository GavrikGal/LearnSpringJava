package com.gmail.gal.gavrik.display.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "models")
public class Models implements Serializable {

	private static final long	serialVersionUID	= 7092054057261196283L;
	private Long				idModel;
	private String				modelName;
	private String				description;
	private byte[]				photo;
	private Set<Equipments>		equipments			= new HashSet<Equipments>();
	private List<Norms>			norms;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Model")
	public Long getIdModel() {
		return idModel;
	}

	public void setIdModel(Long idModel) {
		this.idModel = idModel;
	}

	@OneToMany(mappedBy = "model", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Equipments> getEquipments() {
		return this.equipments;
	}

	public void setEquipments(Set<Equipments> equipments) {
		this.equipments = equipments;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String toString() {
		return modelName;
	}

	@Column(name = "Description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Basic(fetch = FetchType.LAZY)
	@Lob
	@Column(name = "Photo")
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@ManyToMany
	@JoinTable(name = "norms_of_model", joinColumns = @JoinColumn(name = "Model"), inverseJoinColumns = @JoinColumn(name = "Norms"))
	@OrderBy("idNorms")
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<Norms> getNorms() {
		return norms;
	}

	public void setNorms(List<Norms> norms) {
		this.norms = norms;
	}

	@Column(name = "Model_name")
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	

}
