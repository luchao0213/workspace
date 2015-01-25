package com.doume.max.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage=CacheConcurrencyStrategy.NONE)
public class Message extends BaseEntity{
	private static final long serialVersionUID = 1L;
	public static final Integer ALL = 0xffffffff;
	public static final Integer UNREAD = 1;
	public static final Integer ACTUAL = 2;
	public static final Integer PERSISTENT = 4;
	public static final Integer SHARED = 8;
	public static final Integer TEMP = 16;
	public static final Integer TOUSER = 32;
	public static final Integer TOBUSINESS = 64;
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	protected Long msgId;
	protected Integer msgType = UNREAD;
	protected Date msgDate;
	protected Long receiverId;
	protected Long senderId;
	protected String content;
	
	public Long getMsgId() {
		return msgId;
	}
	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}
	public Integer getMsgType() {
		return msgType;
	}
	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}
	public void setUnread()
	{
		msgType |= UNREAD;
	}
	public void setRead()
	{
		msgType &= ~UNREAD;
	}
	public void setActual() {
		msgType |= ACTUAL;
	}
	public Date getMsgDate() {
		return msgDate;
	}
	public void setMsgDate(Date msgDate) {
		this.msgDate = msgDate;
	}
	public Long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	public Long getSenderId() {
		return senderId;
	}
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
