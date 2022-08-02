package com.ie.Pulse.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BrandEmployee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	
	
	private double percent;



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public Brand getBrand() {
		return brand;
	}



	public void setBrand(Brand brand) {
		this.brand = brand;
	}



	public Employee getEmployee() {
		return employee;
	}



	public void setEmployee(Employee employee) {
		this.employee = employee;
	}



	public double getPercent() {
		return percent;
	}



	public void setPercent(double percent) {
		this.percent = percent;
	}
	
	
	
	
}
