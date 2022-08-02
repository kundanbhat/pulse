package com.ie.Pulse.entity;

import java.util.Date;

public class HeadCountdto {
	
	private Date date;
	private int countWith100Percent;
	public int getCountWith100Percent() {
		return countWith100Percent;
	}
	public void setCountWith100Percent(int countWith100Percent) {
		this.countWith100Percent = countWith100Percent;
	}
	public int getCountWithLessThan100Percent() {
		return countWithLessThan100Percent;
	}
	public void setCountWithLessThan100Percent(int countWithLessThan100Percent) {
		this.countWithLessThan100Percent = countWithLessThan100Percent;
	}
	private int countWithLessThan100Percent;
	private String brandName;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

}
