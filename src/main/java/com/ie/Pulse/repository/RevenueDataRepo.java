package com.ie.Pulse.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ie.Pulse.entity.Brand;
import com.ie.Pulse.entity.RevenueData;
import com.ie.Pulse.entity.RevenueHead;
import com.ie.Pulse.entity.RevenueSubHeads;

@Repository
public interface RevenueDataRepo extends JpaRepository<RevenueData, Long>{

	public List<RevenueData> findByRevenueSubHeadAndRevenueheadAndBrandAndDate(RevenueSubHeads subhead,RevenueHead head,Brand brand,Date date);
	public List<RevenueData> findByDateGreaterThanEqualAndDateLessThanEqual(Date startDate,Date endDate);
	
	@Query("SELECT   burevenue.id,revenuehead.id,brand.id,DATE_FORMAT(date, '%Y-%m') ,sum(value)  FROM RevenueData where date>=:startdate and date<=:enddate  group by  burevenue.id,revenuehead.id,brand.id,DATE_FORMAT(date, '%Y-%m') ")
	public List<Object[]> getAccumulateRevenueData(Date startdate,Date enddate);
	
	
	@Query("select brand.id,DATE_FORMAT(date, '%Y-%m'),sum(value) from RevenueData  where date>=:startdate and date<=:enddate group by brand_id,DATE_FORMAT(date, '%Y-%m')")
	public List<Object[]> getExpenseData(Date startdate,Date enddate);
	
	
	@Query("SELECT   burevenue.id,sum(value)  FROM RevenueData where date>=:startdate and date<=:enddate and brand.id=:brandId group by burevenue.id")
	public List<Object[]> getRevenueDataCumulationBetweenTwoDates(Date startdate,Date enddate,long brandId);
	
}
