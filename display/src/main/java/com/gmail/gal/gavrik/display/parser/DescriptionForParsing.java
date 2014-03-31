package com.gmail.gal.gavrik.display.parser;

public class DescriptionForParsing {

	private boolean	isString;
	private String	description;

	public DescriptionForParsing(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double parseFrequency() {
		Double frequency = null;
		if (description != null) {
			description = description.trim();
			String sFrequency = null;

			if (description.contains("-")) {
				if (description.contains(";")
						&& (description.indexOf("-") > description.indexOf(";"))) {
					this.description = description.substring(description.indexOf(";") + 1);
				}
				sFrequency = description.substring(0, description.indexOf("-"));

				
				sFrequency = sFrequency.trim();

			}

			if (sFrequency != null) {
				try {
					sFrequency = sFrequency.replace(",", ".");
					frequency = Double.parseDouble(sFrequency);
					this.description = description.substring(description.indexOf("-") + 1);
					this.description.trim();
					isString = false;
				} catch (NumberFormatException e) {
					frequency = null;
					isString = true;
				}
			}
		}
		System.out.println(" frequency: " + frequency);
		System.out.println(description);
		return frequency;
	}

	public Double parseAmplitude() {
		Double amplitude = null;
		if (description != null) {
			description = description.trim();
			String sAmplitude = null;
			
			int indexForSub = 0;

			if (description.contains("/")) {
				if (description.contains(";")) {
					if (description.indexOf("/") < description.indexOf(";")) {
						sAmplitude = description.substring(0, description.indexOf("/"));
						sAmplitude = sAmplitude.trim();
						indexForSub = description.indexOf("/") + 1;
					} else {
						sAmplitude = description.substring(0, description.indexOf(";"));
						sAmplitude = sAmplitude.trim();
						indexForSub = description.indexOf(";") + 1;
					}
				} else {
					sAmplitude = description.substring(0, description.indexOf("/"));
					sAmplitude = sAmplitude.trim();
					indexForSub = description.indexOf("/") + 1;
				}
			} else {
				if (description.contains(";")) {
					sAmplitude = description.substring(0, description.indexOf(";"));
					sAmplitude = sAmplitude.trim();
					indexForSub = description.indexOf(";") + 1;
				} else {
					sAmplitude = description.substring(0);
					sAmplitude = sAmplitude.trim();
					indexForSub = description.length();
				}
			}

			if (sAmplitude != null) {
				try {
					sAmplitude = sAmplitude.replace(",", ".");
					amplitude = Double.parseDouble(sAmplitude);
					this.description = description.substring(indexForSub);
					this.description.trim();
					isString = false;
				} catch (NumberFormatException e) {
					amplitude = null;
					isString = true;
				}
			}
		}

		System.out.println(" amplitude: " + amplitude);
		System.out.println(description);
		return amplitude;
	}
	
	public Double parseNoise() {
		Double noise = null;
		if (description != null) {
			description = description.trim();
			String sNoise = null;

			int indexForSub = 0;
			
			if (description.contains(";")) {
				if (description.contains("-")) {
					if (description.indexOf(";") < description.indexOf("-")) {
						sNoise = description.substring(0, description.indexOf(";"));
						indexForSub = description.indexOf(";") + 1;
					}
				} else {						
					sNoise = description.substring(0, description.indexOf(";"));
					indexForSub = description.indexOf(";") + 1;
				}
			} else {
				sNoise = description.substring(0);
				indexForSub = description.length();
			}

			if (sNoise != null) {
				try {
					sNoise = sNoise.replace(",", ".");
					sNoise = sNoise.trim();
					noise = Double.parseDouble(sNoise);
					this.description = description.substring(indexForSub);
					this.description.trim();
				} catch (NumberFormatException e) {
					noise = null;
				}
			}
		}
		System.out.println(" noise: " + noise);
		System.out.println(description);
		return noise;
	}

	public boolean isString() {
		return isString;
	}

}
