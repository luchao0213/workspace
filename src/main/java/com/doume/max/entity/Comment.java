package com.doume.max.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;



@Entity
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Comment extends BaseEntity{
	private static final long serialVersionUID = 1L;
	public static final int VERYGOOD = 5;
	public static final int GOOD = 4;
	public static final int OK = 3;
	public static final int NOTBAD = 2;
	public static final int BAD = 1;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long cmmId;
	private Date cmmTime;
	private Long userId;
	private Long targetId;
	private String content;
	private Integer score;
	public Long getCmmId() {
		return cmmId;
	}
	public void setCmmId(Long cmmId) {
		this.cmmId = cmmId;
	}
	public Date getCmmTime() {
		return cmmTime;
	}
	public void setCmmTime(Date cmmTime) {
		this.cmmTime = cmmTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getTagId() {
		return targetId;
	}
	public void setTagId(Long tagId) {
		this.targetId = tagId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
}
