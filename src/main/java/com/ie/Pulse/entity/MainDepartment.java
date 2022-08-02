package com.ie.Pulse.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MainDepartment {
	
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
	
	@OneToMany(mappedBy = "mainDepartment")
	private List<SubDepartment> subDepartmentList=new ArrayList<>();

	public List<SubDepartment> getSubDepartmentList() {
		return subDepartmentList;
	}

	public void setSubDepartmentList(List<SubDepartment> subDepartmentList) {
		this.subDepartmentList = subDepartmentList;
	}

}
