package com.search_service_es.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Release {

	private String announceDate;

	private int priceEur;

	public String getAnnounceDate() {
		return announceDate;
	}

	public void setAnnounceDate(String announceDate) {
		this.announceDate = announceDate;
	}

	public int getPriceEur() {
		return priceEur;
	}

	public void setPriceEur(int priceEur) {
		this.priceEur = priceEur;
	}

	public Release() {
	}

	@Override
	public String toString() {
		return "Release{" + "announceDate=" + announceDate + ", priceEur='" + priceEur + '\'' + '}';
	}
}