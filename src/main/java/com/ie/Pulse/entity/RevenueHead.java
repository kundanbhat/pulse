package com.ie.Pulse.entity;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class RevenueHead {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	@OneToMany(mappedBy = "revenuehead")
	private List<RevenueSubHeads> subHeadList=new ArrayList<>();
	
	@OneToMany(mappedBy = "revenuehead")
	private List<RevenueData> revenueDataList=new ArrayList<>();
	

	public List<RevenueData> getRevenueDataList() {
		return revenueDataList;
	}

	public void setRevenueDataList(List<RevenueData> revenueDataList) {
		this.revenueDataList = revenueDataList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RevenueSubHeads> getSubHeadList() {
		return subHeadList;
	}

	public void setSubHeadList(List<RevenueSubHeads> subHeadList) {
		this.subHeadList = subHeadList;
	}
	
	@ManyToOne
	@JoinColumn(name="bu_id")
	private BuRevenue burevenue;


	public BuRevenue getBurevenue() {
		return burevenue;
	}

	public void setBurevenue(BuRevenue burevenue) {
		this.burevenue = burevenue;
	}
	
	
	
	
	
	
}
