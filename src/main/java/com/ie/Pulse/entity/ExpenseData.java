package com.ie.Pulse.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class ExpenseData {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	
	private String poc;
	private String teamName;
	private String purpose;
	@ManyToOne
	@JoinColumn(name="l2_id")
	private L2 l2;
	
	@ManyToMany(mappedBy = "expenseData")
	@Cascade(CascadeType.ALL)
    private Set<Brand> brands = new HashSet<>();
	
	
	@ManyToMany(mappedBy = "expenseList")
	@Cascade(CascadeType.ALL)
    private Set<BusinessUnit> bulist = new HashSet<>();
	
	private String allocation;
	private String billingCriteria;
	
	private double conversion;
	private String units;
	private double value;
	private Date date;



	

	public String getPoc() {
		return poc;
	}

	public void setPoc(String poc) {
		this.poc = poc;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public L2 getL2() {
		return l2;
	}

	public void setL2(L2 l2) {
		this.l2 = l2;
	}

	public Set<Brand> getBrands() {
		return brands;
	}

	public void setBrands(Set<Brand> brands) {
		this.brands = brands;
	}

	public Set<BusinessUnit> getBulist() {
		return bulist;
	}

	public void setBulist(Set<BusinessUnit> bulist) {
		this.bulist = bulist;
	}

	public String getAllocation() {
		return allocation;
	}

	public void setAllocation(String allocation) {
		this.allocation = allocation;
	}

	public String getBillingCriteria() {
		return billingCriteria;
	}

	public void setBillingCriteria(String billingCriteria) {
		this.billingCriteria = billingCriteria;
	}

	public double getConversion() {
		return conversion;
	}

	public void setConversion(double conversion) {
		this.conversion = conversion;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
		
	
	@ManyToOne
	@JoinColumn(name="party_id")
	private Party party;



	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}
	
		
	
}
