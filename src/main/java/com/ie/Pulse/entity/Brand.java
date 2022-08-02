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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;




@Entity
public class Brand {
	
	
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
	
	private int channelid;
	
	
	public int getChannelid() {
		return channelid;
	}

	public void setChannelid(int channelid) {
		this.channelid = channelid;
	}

	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "brand_expense",
        joinColumns = { @JoinColumn(name = "brand_id", referencedColumnName = "id") }, 
        inverseJoinColumns = { @JoinColumn(name = "expense_id", referencedColumnName = "id") }
    )
	private  Set<ExpenseData> expenseData= new HashSet<>();
	
	
	@OneToMany(mappedBy = "brand")
	private List<BrandEmployee> brandEmployeeList=new ArrayList<>();

	public Set<ExpenseData> getExpenseData() {
		return expenseData;
	}

	public void setExpenseData(Set<ExpenseData> expenseData) {
		this.expenseData = expenseData;
	}

	public List<BrandEmployee> getBrandEmployeeList() {
		return brandEmployeeList;
	}

	public void setBrandEmployeeList(List<BrandEmployee> brandEmployeeList) {
		this.brandEmployeeList = brandEmployeeList;
	}
	
	
	@OneToMany(mappedBy = "brand")
	private List<PageView> pageViewList=new ArrayList<>();

	public List<PageView> getPageViewList() {
		return pageViewList;
	}

	public void setPageViewList(List<PageView> pageViewList) {
		this.pageViewList = pageViewList;
	} 
	
	
	@OneToMany(mappedBy = "brand")
	private List<Actual> actualList=new ArrayList<>();

	public List<Actual> getActualList() {
		return actualList;
	}

	public void setActualList(List<Actual> actualList) {
		this.actualList = actualList;
	}
	
	
	@OneToMany(mappedBy = "brand")
	private List<RevenueData> revenueDataList=new ArrayList<>();

	public List<RevenueData> getRevenueDataList() {
		return revenueDataList;
	}

	public void setRevenueDataList(List<RevenueData> revenueDataList) {
		this.revenueDataList = revenueDataList;
	}
	
	
	@ManyToMany(mappedBy = "brands")
    private Set<Users> users = new HashSet<>();

	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}
	

}
