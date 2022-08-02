package com.ie.Pulse.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ie.Pulse.entity.ExpenseData;
import com.ie.Pulse.entity.Party;


public interface ExpenseRepo extends JpaRepository<ExpenseData, Long>{
	
	public List<ExpenseData> findByPartyName(String name);
	public List<ExpenseData> findByPartyNameAndDate(String name,Date date);
	public List<ExpenseData> findByPartyAndDate(Party party,Date date);
	public List<ExpenseData> findByDateGreaterThanEqualAndDateLessThanEqual(Date startdate,Date endDate);
	
	
	@Query("SELECT l2.id,party.id,DATE_FORMAT(date, '%Y-%m'), sum(value) from ExpenseData where date>:startDate and date<:endDate group by l2_id,party_id,DATE_FORMAT(date, '%Y-%m') order by party_id, DATE_FORMAT(date, '%Y-%m')")
    public List<Object[]> getcumulativeExpenseDataL1ForDatesBetween(Date startDate,Date endDate);
    
    @Query("SELECT DATE_FORMAT(date, '%Y-%m'),sum(pageview) FROM PageView where date>=:startDate and date<=:endDate  group by DATE_FORMAT(date, '%Y-%m') order by DATE_FORMAT(date, '%Y-%m')")
    public List<Object[]> getMonthWisePageView(Date startDate,Date endDate);

}
