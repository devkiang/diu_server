package com.appserver.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;


@Entity
public class Goods implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	public long getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(long goodsID) {
		this.goodsID = goodsID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getReturnPrice() {
		return returnPrice;
	}
	public void setReturnPrice(String returnPrice) {
		this.returnPrice = returnPrice;
	}
	public String getVipPrice() {
		return vipPrice;
	}
	public void setVipPrice(String vipPrice) {
		this.vipPrice = vipPrice;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getBrowseNumber() {
		return browseNumber;
	}
	public void setBrowseNumber(String browseNumber) {
		this.browseNumber = browseNumber;
	}
	public String getSurplusNumber() {
		return surplusNumber;
	}
	public void setSurplusNumber(String surplusNumber) {
		this.surplusNumber = surplusNumber;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Column(length=100000)
	public String[] getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(String[] imageUrls) {
		this.imageUrls = imageUrls;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	private long   goodsID;
	private String name;
	private String price;
	private String returnPrice;
	private String vipPrice;
	private String synopsis;
	private String browseNumber;
	private String surplusNumber;
	private String[] imageUrls;
	private List<Comment> comments;




}
