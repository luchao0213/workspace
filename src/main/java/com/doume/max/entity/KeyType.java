package com.doume.max.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class KeyType extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long oid;
	private String keyClass;
	private Long keyValue;
	private String keyName;
	
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public String getKeyClass() {
		return keyClass;
	}
	public void setKeyClass(String keyClass) {
		this.keyClass = keyClass;
	}
	public Long getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(Long keyValue) {
		this.keyValue = keyValue;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	@Override
	public boolean equals(Object obj) {
		KeyType key=(KeyType)obj;
		if(this.keyClass.equals(key.getKeyClass())
				&& this.keyValue.equals(key.getKeyValue())
				&&this.keyName.equals(key.getKeyName()))
		{
			return true;
		}else
		{
			return false;
		}
	}
}
