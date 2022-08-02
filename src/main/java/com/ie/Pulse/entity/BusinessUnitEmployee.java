package com.ie.Pulse.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BusinessUnitEmployee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	@ManyToOne
	@JoinColumn(name="businessunit_id")
	private BusinessUnit buunit;
	
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public BusinessUnit getBuunit() {
		return buunit;
	}


	public void setBuunit(BusinessUnit buunit) {
		this.buunit = buunit;
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


	private double percent;
	

}
