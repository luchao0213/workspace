package com.doume.max.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Credit extends BaseEntity{
	private static final long serialVersionUID = 1L;
	@Id
	private Long sellerId;		
	@Id
	private Long buyerId;
	private Integer value = 0;
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public Long getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	
	public Credit clone()
	{
		Credit credit = new Credit();
		credit.setBuyerId(buyerId);
		credit.setSellerId(sellerId);
		credit.setValue(value);
		return credit;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass().equals(Credit.class)){
			Credit c = (Credit) obj;
			return c.getSellerId().equals(getSellerId())&&c.getBuyerId().equals(getBuyerId())&&c.getValue().equals(getValue());
		}
		return false;
	}
	@Override
	public int hashCode() {
		return (sellerId==null?sellerId.hashCode():0)*31 + (buyerId==null?buyerId.hashCode():0);
	}
}
