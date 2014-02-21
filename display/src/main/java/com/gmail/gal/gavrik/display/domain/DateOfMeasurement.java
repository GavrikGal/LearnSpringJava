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
	private Set<Measurements>	measurements		= new HashSet<Measurements>();
	private Set<Measurements>	secondMeasurements	= new HashSet<Measurements>();

	@OneToMany(mappedBy = "dateOfSecondMeasurement", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Measurements> getSecondMeasurements() {
		return secondMeasurements;
	}

	public void setSecondMeasurements(Set<Measurements> secondMeasurements) {
		this.secondMeasurements = secondMeasurements;
	}

	@OneToMany(mappedBy = "dateOfMeasurement", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Measurements> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(Set<Measurements> measurements) {
		this.measurements = measurements;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Date_of_measurrement")
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
