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
public class RevenueSubHeads {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	
	@ManyToOne
	@JoinColumn(name = "revenue_id")
	private RevenueHead revenuehead;


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


	public RevenueHead getRevenuehead() {
		return revenuehead;
	}


	public void setRevenuehead(RevenueHead revenuehead) {
		this.revenuehead = revenuehead;
	}
	
	
	@OneToMany(mappedBy = "revenueSubHead")
	private List<RevenueData> revenueDataList=new ArrayList<>();
	

}
