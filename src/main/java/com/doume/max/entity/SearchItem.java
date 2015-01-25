package com.doume.max.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SearchItem extends BaseEntity{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long oid;
	private String keyWoard;
	private String mediaId;
	private Integer weight;
	private String tarType;
	private Long tarId;
	private Long mask;
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Long getTarId() {
		return tarId;
	}
	public void setTarId(Long tarId) {
		this.tarId = tarId;
	}
	public Long getMask() {
		return mask;
	}
	public void setMask(Long mask) {
		this.mask = mask;
	}
	public String getTarType() {
		return tarType;
	}
	public void setTarType(String tarType) {
		this.tarType = tarType;
	}
	public String getKeyWoard() {
		return keyWoard;
	}
	public void setKeyWoard(String keyWoard) {
		this.keyWoard = keyWoard;
	}
	
}
