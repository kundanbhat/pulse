package com.ie.Pulse.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class BusinessUnit {
	
	
	
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
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "businessunit_expense",
        joinColumns = { @JoinColumn(name = "bu_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "expense_id") }
    )
    Set<ExpenseData> expenseList= new HashSet<>();
	
	@OneToMany(mappedBy = "buunit")
	private List<BusinessUnitEmployee> businessEmployeeUnitList=new ArrayList<>();


	public Set<ExpenseData> getExpenseList() {
		return expenseList;
	}


	public void setExpenseList(Set<ExpenseData> expenseList) {
		this.expenseList = expenseList;
	}


	public List<BusinessUnitEmployee> getBusinessEmployeeUnitList() {
		return businessEmployeeUnitList;
	}


	public void setBusinessEmployeeUnitList(List<BusinessUnitEmployee> businessEmployeeUnitList) {
		this.businessEmployeeUnitList = businessEmployeeUnitList;
	}
	
	
	
	
//	@ManyToMany(cascade = { CascadeType.ALL })
//    @JoinTable(
//        name = "businessunit_employee",
//        joinColumns = { @JoinColumn(name = "bu_id") }, 
//        inverseJoinColumns = { @JoinColumn(name = "employee_id") }
//    )
//    Set<Employee> employeeList= new HashSet<>();
	
	

}
