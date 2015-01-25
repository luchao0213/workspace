package com.doume.max.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Administrator extends BaseEntity{
	private static final long serialVersionUID = 1L;
	@Id
	private Long userId;
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Business> blist ;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Set<Business> getBlist() {
		return blist;
	}
	public void addBusiness(Business business) {
		if(blist==null)
		{
			blist = new HashSet<Business>();
		}
		blist.add(business);
	}
	public void removeBusiness(Business business) {
		if(blist!=null)
			blist.remove(business);
	}
}
