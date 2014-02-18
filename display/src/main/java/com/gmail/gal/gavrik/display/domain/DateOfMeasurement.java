package com.gmail.gal.gavrik.display.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "date_of_measurement")
public class DateOfMeasurement implements Serializable {

	private static final long	serialVersionUID	= -544910450691585253L;
	private Long				idDate;
	private DateTime			date;
	private Set<Spectrums>		spectrums			= new HashSet<Spectrums>();

	@OneToMany(mappedBy = "date", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Spectrums> getSpectrums() {
		return this.spectrums;
	}

	public void setSpectrums(Set<Spectrums> spectrums) {
		this.spectrums = spectrums;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Date")
	public Long getIdDate() {
		return idDate;
	}

	public void setIdDate(Long idDate) {
		this.idDate = idDate;
	}

	@Column(name = "Date")
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	@DateTimeFormat(iso = ISO.DATE)
	public DateTime getDate() {
		return date;
	}

	@Transient
	public String getDateString() {
		String dateString = "";
		if (date != null) {
			dateString = org.joda.time.format.DateTimeFormat.forPattern("dd.MM.yyyy").print(
					date);
		}
		return dateString;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
