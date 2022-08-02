package com.ie.Pulse.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BuRevenue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	@OneToMany(mappedBy = "burevenue")
	private List<RevenueHead> headList=new ArrayList<>();
	
	
	@OneToMany(mappedBy = "burevenue")
	private List<RevenueData> dataList=new ArrayList<>();
	
	

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

	public List<RevenueHead> getHeadList() {
		return headList;
	}

	public void setHeadList(List<RevenueHead> headList) {
		this.headList = headList;
	}
	
	
	

	
	
	
	
}
