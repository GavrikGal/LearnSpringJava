package com.gmail.gal.gavrik.display.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "harmonics")
public class Harmonics implements Serializable {

	private static final long	serialVersionUID	= -3487239594233702920L;
	private Long				idHarmonics;
	private Double				frequency;
	private Double				receiverBandwidth;
	private Double				amplitude;
	private Double				Noise;
	private Spectrums			spectrum;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Harmonics")
	public Long getIdHarmonics() {
		return idHarmonics;
	}

	public void setIdHarmonics(Long idHarmonics) {
		this.idHarmonics = idHarmonics;
	}

	@Column(name = "Frequency")
	public Double getFrequency() {
		return frequency;
	}

	public void setFrequency(Double frequency) {
		this.frequency = frequency;
	}

	@Column(name = "ReceiverBandwidth")
	public Double getReceiverBandwidth() {
		return receiverBandwidth;
	}

	public void setReceiverBandwidth(Double receiverBandwidth) {
		this.receiverBandwidth = receiverBandwidth;
	}
	
	@Column(name = "Amplitude")
	public Double getAmplitude() {
		return amplitude;
	}

	public void setAmplitude(Double amplitude) {
		this.amplitude = amplitude;
	}

	@Column(name = "Noise")
	public Double getNoise() {
		return Noise;
	}

	public void setNoise(Double noise) {
		Noise = noise;
	}

	@ManyToOne
	@JoinColumn(name = "Spectrum")
	public Spectrums getSpectrum() {
		return spectrum;
	}

	public void setSpectrum(Spectrums spectrum) {
		this.spectrum = spectrum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
