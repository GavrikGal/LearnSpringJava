package com.gmail.gal.gavrik.display.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "models")
public class Models implements Serializable {

	private static final long	serialVersionUID	= 7092054057261196283L;
	private String				idModel;
	private Set<Equipments>		equipments			= new HashSet<Equipments>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Model")
	public String getIdModel() {
		return idModel;
	}

	public void setIdModel(String idModel) {
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
		return idModel;
	}

}
