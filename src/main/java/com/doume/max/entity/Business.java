package com.doume.max.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
public class Business extends BaseEntity{
	private static final long serialVersionUID = 1L;
	public static final Long ALL = 0xFFFFFFFFFFFFFFFFL;
	public static final Long NONE = 0L;
	@Id
	private Long userId;
	private String bName;
	private Long bType;
	private Long locked = NONE;
	private Long capacity;
	private Long used;
	private Integer ppm;
	private Integer balance;
	private String information;
	private String addr;
	private String phoneno;
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private BHomePage home = new BHomePage();
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private Location location = new Location();
	private Integer weight;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
		home.setUserId(this.userId);
		location.setUserId(this.userId);
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	public Long getbType() {
		return bType;
	}
	public void setbType(Long bType) {
		this.bType = bType;
	}
	
	public Long getCapacity() {
		return capacity;
	}
	public void setCapacity(Long capacity) {
		this.capacity = capacity;
	}
	public Long getUsed() {
		return used;
	}
	public void setUsed(Long used) {
		this.used = used;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public BHomePage getHome() {
		return home;
	}
	public void setHome(BHomePage home) {
		this.home = home;
		this.home.setUserId(this.userId);
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
		this.location.setUserId(userId);
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	@Transient
	public String authorityInfo() {
		return "{userId:" + userId +",capcity:" + capacity +",used:" + used + "}";
	}
	public Integer getPpm() {
		return ppm;
	}
	public void setPpm(Integer ppm) {
		this.ppm = ppm;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public Long getLocked() {
		return locked;
	}
	@Transient
	public boolean isLocked()
	{
		return locked != NONE;
	}
	
	public void setLocked(Long locked) {
		this.locked = locked;
	}
	public void lock()
	{
		locked = bType;
		bType = NONE;
	}
	public void unlock()
	{
		if(locked!=NONE)
		{
			bType = locked;
			this.locked = NONE;
		}
	}
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass().equals(Business.class)){
			Business ob = (Business)obj;
			EqualsBuilder eb = new EqualsBuilder();
			eb.append(this.getAddr(), ob.getAddr())
			.append(this.getBalance(), ob.getBalance())
			.append(this.getbName(), ob.getbName())
			.append(this.getbType(), ob.getbType())
			.append(this.getCapacity(), ob.getCapacity())
			.append(this.getHome(), ob.getHome())
			.append(this.getInformation(), ob.getInformation())
			.append(this.getLocation(), ob.getLocation())
			.append(this.getLocked(), ob.getLocked())
			.append(this.getPhoneno(), ob.getPhoneno())
			.append(this.getPpm(), ob.getPpm())
			.append(this.getUserId(), ob.getUserId())
			.append(this.getUsed(), ob.getUsed())
			.append(this.getWeight(),ob.getWeight());
			return eb.isEquals();
		}
		return false;
	}
	@Override
	public int hashCode(){
		return this.getUserId()==null?0:this.getUserId().hashCode();
	}
}
