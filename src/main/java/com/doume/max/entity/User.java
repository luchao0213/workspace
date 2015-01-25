package com.doume.max.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.doume.max.cons.MUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("user")
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONE)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User extends BaseEntity {
	private static final long serialVersionUID = 1L;
	public static final Long ADMIN = 1L;
	public static final Long BUSINESS = 2L;
	public static final Long CUSTOMER = 4L;
	public static final Long TOURIST = 8L;
	public static final User SYSTEM;
	static {
		SYSTEM = new User();
		SYSTEM.setUserId(0L);
		SYSTEM.setUserName("MaiDou");
		SYSTEM.setOpenId("doume");
		SYSTEM.setUserType(ADMIN);
	}
	public static final Integer USER_LOCK = 1;
	public static final Integer USER_UNLOCK = 2;
	public static long LEVEL0 = 1024 * 1024;
	public static long LEVEL1 = 1024 * 1024 * 10;
	public static long LEVEL2 = 1024 * 1024 * 100;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long userId = null;
	@Column(unique = true)
	private String openId = null;
	@Pattern(regexp="[a-zA-Z0-9_]{4,30}")
	@Column(unique = true)
	private String userName;
	@Pattern(regexp=".{4,32}")
	private String password;
	private Long userType;
	private Integer locked;	
	private Date lastVisit;
	private String lastIp;
	public User()
	{
		userType = 0L;
		locked = USER_UNLOCK;
	}
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public void setPasswordMD5(String password) {
		this.password = MUtils.getMD5Code(password);
	}

	public Long getUserType() {
		return userType;
	}

	public void setUserType(Long userType) {
		this.userType = userType;
	}

	public Date getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

	public String getLastIp() {
		return lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	public void setAdmin()
	{
		userType|=ADMIN;
	}
	public void setBusiness()
	{
		userType|=BUSINESS;
	}
	public void setCustomer()
	{
		userType|=CUSTOMER;
	}
	@Transient
	public boolean isAdmin()
	{
		return (userType&ADMIN) != 0;
	}
	@Transient
	public boolean isBusiness()
	{
		return (userType&BUSINESS) != 0;
	}
	@Transient
	public boolean isCustomer()
	{
		return (userType&CUSTOMER) != 0;
	}
	@Transient
	public boolean isLocked() {
		return locked==USER_LOCK;
	}
	public void lock(){
		locked=USER_LOCK;
	}
	public void unlock(){
		locked=USER_UNLOCK;
	}
}
