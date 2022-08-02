package com.ie.Pulse.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Party {
	
	
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
	
	@OneToMany(mappedBy = "party")
	private List<ExpenseData> expenseList=new ArrayList<ExpenseData>();

	public List<ExpenseData> getExpenseList() {
		return expenseList;
	}

	public void setExpenseList(List<ExpenseData> expenseList) {
		this.expenseList = expenseList;
	}
	
	@OneToMany(mappedBy = "party")
	private List<Actual> actualList=new ArrayList<>();

	public List<Actual> getActualList() {
		return actualList;
	}

	public void setActualList(List<Actual> actualList) {
		this.actualList = actualList;
	}

}
