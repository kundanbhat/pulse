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
public class SubDepartment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;

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

	
	@OneToMany(mappedBy = "subdepartment")
	private List<Employee> empList=new ArrayList<>();

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	
	
	@ManyToOne
	@JoinColumn(name="main_department_id")
	private MainDepartment mainDepartment;

	public MainDepartment getMainDepartment() {
		return mainDepartment;
	}

	public void setMainDepartment(MainDepartment mainDepartment) {
		this.mainDepartment = mainDepartment;
	}
	

}
