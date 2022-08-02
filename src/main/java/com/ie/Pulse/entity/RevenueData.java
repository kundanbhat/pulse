package com.ie.Pulse.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RevenueData {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	
	@ManyToOne
	@JoinColumn(name="subhead_id")
	private RevenueSubHeads revenueSubHead;
	
	@ManyToOne
	@JoinColumn(name="head_id")
	private RevenueHead   revenuehead;
	
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand ;
	
	
	@ManyToOne
	@JoinColumn(name="bu_id")
	private BuRevenue burevenue;
	
	
	
	

	public BuRevenue getBurevenue() {
		return burevenue;
	}

	public void setBurevenue(BuRevenue burevenue) {
		this.burevenue = burevenue;
	}

	public RevenueHead getRevenuehead() {
		return revenuehead;
	}

	public void setRevenuehead(RevenueHead revenuehead) {
		this.revenuehead = revenuehead;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	private Date date;
	
	private double value;
	
	private float usdToInr;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RevenueSubHeads getRevenueSubHead() {
		return revenueSubHead;
	}

	public void setRevenueSubHead(RevenueSubHeads revenueSubHead) {
		this.revenueSubHead = revenueSubHead;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public float getUsdToInr() {
		return usdToInr;
	}

	public void setUsdToInr(float usdToInr) {
		this.usdToInr = usdToInr;
	}
	
	
	
	
	
}
