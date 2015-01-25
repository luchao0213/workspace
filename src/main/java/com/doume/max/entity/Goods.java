package com.doume.max.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
@Entity
public class Goods extends BaseEntity{
	private static final long serialVersionUID = 1L;
	public static final Long ID_USER_LEVELUP = 1L;
	public static final Long ALL=0xffffffffffffffffL;
	public static final Long NONE=0x0L;
	public static final Goods UNIT;
	static {
		UNIT = new Goods();
		UNIT.setSellerId(0L);
		UNIT.setScoreCount(0);
		UNIT.setGoodsId(0L);
		UNIT.setGoodsName("VIRTUAL_GOODS");
		UNIT.setGoodsType(0L);
		UNIT.setPrice(1);
		UNIT.setRetCredit(1);
		UNIT.setCreditPrice(1);
	}
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long goodsId;			
	private Long goodsType;		
	private String goodsName;
	private String description;
	private Long sellerId;
	private Integer price;
	private Integer creditPrice;
	private Integer retCredit;
	@Transient
	private CommonsMultipartFile media;
	private String mediaId;
	private Long locked = NONE;
	private Integer selledCount = 0;
	private Integer scoreCount = 0;
	private Integer score = 0;
	public Goods(){
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public Long getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(Long goodsType) {
		this.goodsType = goodsType;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getCreditPrice() {
		return creditPrice;
	}
	public void setCreditPrice(Integer creditPrice) {
		this.creditPrice = creditPrice;
	}
	public Integer getRetCredit() {
		return retCredit;
	}
	public void setRetCredit(Integer retCredit) {
		this.retCredit = retCredit;
	}
	public CommonsMultipartFile getMedia() {
		return media;
	}
	public void setMedia(CommonsMultipartFile media) {
		this.media = media;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getSelledCount() {
		return selledCount;
	}
	public void setSelledCount(Integer selledCount) {
		this.selledCount = selledCount;
	}
	public Integer getScoreCount() {
		return scoreCount;
	}
	public void setScoreCount(Integer scoreCount) {
		this.scoreCount = scoreCount;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public double getAvgScore()
	{
		return ((double)score)/scoreCount;
	}
	public Long getLocked() {
		return locked;
	}
	public void setLocked(Long locked) {
		this.locked = locked;
	}
	@Transient
	public boolean isLocked()
	{
		return this.locked != NONE;
	}
	public void lock()
	{
		locked = goodsType;
		goodsType = NONE;
	}
	public void unlock()
	{
		if(locked!=NONE)
		{
			goodsType = locked;
			locked = NONE;
		}
	}
}
