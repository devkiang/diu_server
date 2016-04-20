package com.appserver.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long cid;
	private String content;
	private String date;//时间戳
	private String title;
	private Users user;
	private String superCid;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid = cid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="user")
	@ManyToOne(fetch=FetchType.LAZY)
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public String getSuperCid() {
		return superCid;
	}
	public void setSuperCid(String superCid) {
		this.superCid = superCid;
	}
}
