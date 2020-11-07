package com.search_service_es.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hardware {

	private String audioJack;

	
	private String gps;

	private String  battery;

	
	public String isAudioJack() {
		return audioJack;
	}

	public void setAudioJack(String audioJack) {
		this.audioJack = audioJack;
	}

	public String isGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

	public String getBattery() {
		return battery;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	

	public Hardware() {
	}

	@Override
	public String toString() {
		return "Hardware{" + "audioJack=" + audioJack + " \"battery=\" + battery + \", gps='" + gps +  '\'' +  '}';
	}
}