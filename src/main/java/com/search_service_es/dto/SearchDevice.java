package com.search_service_es.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchDevice {
 


	@JsonProperty("brand")
    private String brand;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("picture")
    private String picture;

    @JsonProperty("sim")
    private String sim;

    @JsonProperty("resolution")
    private String resolution;

    @JsonProperty("release")
    private Release  Release;

    @JsonProperty("hardware")
    private Hardware Hardware;

   

    @JsonProperty("id")
    private Integer id;

    
    public String getBrand() {
 		return brand;
 	}



 	public void setBrand(String brand) {
 		this.brand = brand;
 	}



 	public String getPhone() {
 		return phone;
 	}



 	public void setPhone(String phone) {
 		this.phone = phone;
 	}



 	public String getPicture() {
 		return picture;
 	}



 	public void setPicture(String picture) {
 		this.picture = picture;
 	}



 	public String getSim() {
 		return sim;
 	}



 	public void setSim(String sim) {
 		this.sim = sim;
 	}



 	public String getResolution() {
 		return resolution;
 	}



 	public void setResolution(String resolution) {
 		this.resolution = resolution;
 	}



 	public Release getRelease() {
 		return Release;
 	}



 	public void setRelease(Release release) {
 		Release = release;
 	}



 	public Hardware getHardware() {
 		return Hardware;
 	}



 	public void setHardware(Hardware hardware) {
 		Hardware = hardware;
 	}



 	public Integer getId() {
 		return id;
 	}



 	public void setId(Integer id) {
 		this.id = id;
 	}

}
