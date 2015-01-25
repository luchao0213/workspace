package com.doume.max.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
public class News extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Long ALL = 0x7fffffffffffffffL;
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long newsId;
	private Integer updateInterval;
	private Long newsType;
	private Date newsDate;
	private String title;
	private String content;		
	private String mediaId;
	@Transient
	private CommonsMultipartFile file;
	public Long getNewsId() {
		return newsId;
	}
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
	public Integer getUpdateInterval() {
		return updateInterval;
	}
	public void setUpdateInterval(Integer updateInterval) {
		this.updateInterval = updateInterval;
	}
	public Long getNewsType() {
		return newsType;
	}
	public void setNewsType(Long newsType) {
		this.newsType = newsType;
	}
	public Date getNewsDate() {
		return newsDate;
	}
	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
}
