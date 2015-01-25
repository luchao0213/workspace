package com.doume.max.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Location extends BaseEntity{
	private static final long serialVersionUID = 1L;
	@Id
	private Long userId;
	private Double lng;
	private Double lat;
	private Double distance;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
}
