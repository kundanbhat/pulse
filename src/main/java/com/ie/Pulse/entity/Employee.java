package com.ie.Pulse.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

@Entity
public class Employee {

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
	private String empcode;

	public String getEmpcode() {
		return empcode;
	}

	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}
	
	
	@OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="manager_id")
    private Employee manager;

    @OneToMany(mappedBy="manager")
    private Set<Employee> subordinates = new HashSet<Employee>();
    
    
    private Date doj;
    private Date lwd;
    
    
    @ManyToOne
    @JoinColumn(name="subdepartment_id")
    private SubDepartment subdepartment;
    
    private double ctc;
    private String empType;
    private Date contractExpiryDate;
    
    
//    @ManyToMany(mappedBy = "employeeList")
//	@Cascade(org.hibernate.annotations.CascadeType.ALL)
//    private Set<Brand> brands = new HashSet<>();
    
    
    @OneToMany(mappedBy = "employee")
    private List<BrandEmployee> brandEmployeeList=new ArrayList<>();
    
    
    @OneToMany(mappedBy = "employee")
  	@Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<BusinessUnitEmployee> businessUnitEmployee = new HashSet<>();

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Set<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Set<Employee> subordinates) {
		this.subordinates = subordinates;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public Date getLwd() {
		return lwd;
	}

	public void setLwd(Date lwd) {
		this.lwd = lwd;
	}

	public SubDepartment getSubdepartment() {
		return subdepartment;
	}

	public void setSubdepartment(SubDepartment subdepartment) {
		this.subdepartment = subdepartment;
	}

	public double getCtc() {
		return ctc;
	}

	public void setCtc(double ctc) {
		this.ctc = ctc;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public Date getContractExpiryDate() {
		return contractExpiryDate;
	}

	public void setContractExpiryDate(Date contractExpiryDate) {
		this.contractExpiryDate = contractExpiryDate;
	}

	

	public List<BrandEmployee> getBrandEmployeeList() {
		return brandEmployeeList;
	}

	public void setBrandEmployeeList(List<BrandEmployee> brandEmployeeList) {
		this.brandEmployeeList = brandEmployeeList;
	}

	private Date date;

	public Set<BusinessUnitEmployee> getBusinessUnitEmployee() {
		return businessUnitEmployee;
	}

	public void setBusinessUnitEmployee(Set<BusinessUnitEmployee> businessUnitEmployee) {
		this.businessUnitEmployee = businessUnitEmployee;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
    
    
    
    
    
    
    
    
    
    
	
	
}
