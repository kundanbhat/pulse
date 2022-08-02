package com.ie.Pulse.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ie.Pulse.entity.Brand;
import com.ie.Pulse.entity.RevenueData;
import com.ie.Pulse.entity.RevenueHead;
import com.ie.Pulse.entity.RevenueSubHeads;
import com.ie.Pulse.repository.RevenueDataRepo;

@Service
public class RevenueDataService {

	@Autowired
	private RevenueDataRepo repo;
	
	
	public List<RevenueData> getRevenueData(RevenueSubHeads subhead,RevenueHead head,Brand brand,Date date )
	{
		return repo.findByRevenueSubHeadAndRevenueheadAndBrandAndDate(subhead, head, brand, date);
	}
	public void save(RevenueData data)
	{
		repo.save(data);
	}
	public List<RevenueData> getRevenueData(Date startdate,Date  endDate)
	{
		return repo.findByDateGreaterThanEqualAndDateLessThanEqual(startdate, endDate);
	}
	public List<Object[]> getAccumulatedRevenueData(Date startDate,Date endDate)
	{
		return repo.getAccumulateRevenueData(startDate, endDate);
	}
	
	public List<Object[]> getAccumulatedRevenueDataBasedonBrand(Date startdate,Date enddate)
	{
		return repo.getExpenseData(startdate, enddate);
	}
	
	
	public List<Object[]> getRevenueDataAccumulated (Date startdate,Date enddate,long brandId)
	{
		return repo.getRevenueDataCumulationBetweenTwoDates(startdate, enddate, brandId);
	}
	
	
	
}
