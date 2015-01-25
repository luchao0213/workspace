package com.doume.max.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrdersItem extends BaseEntity{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long oid;
	@ManyToOne
	@JoinColumn(name="ordersId",referencedColumnName="ordersId")
	private Orders orders;
	@ManyToOne
	@JoinColumn(name="goodsId",referencedColumnName="goodsId")
	private Goods goods;
	private Boolean isUsingCredit = false;
	private Integer count;
	
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Integer getValue() {
		return count;
	}
	public void setValue(Integer value) {
		this.count = value;
	}
	public Boolean isUsingCredit() {
		return isUsingCredit;
	}
	public void usingCredit() {
		this.isUsingCredit = true;
	}
	public void useMoney()
	{
		this.isUsingCredit = false;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	
	
}
