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
public class L2 {
	
	
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
	
	@ManyToOne
	@JoinColumn(name="l1_id")
	private L1 l1;

	public L1 getL1() {
		return l1;
	}

	public void setL1(L1 l1) {
		this.l1 = l1;
	}
	
	
	@OneToMany(mappedBy = "l2")
	private List<ExpenseData> expenseDataList=new ArrayList<ExpenseData>();

	public List<ExpenseData> getExpenseDataList() {
		return expenseDataList;
	}

	public void setExpenseDataList(List<ExpenseData> expenseDataList) {
		this.expenseDataList = expenseDataList;
	}

}
