package com.ie.Pulse.Services;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ie.Pulse.entity.ExpenseData;
import com.ie.Pulse.entity.Party;
import com.ie.Pulse.repository.ExpenseRepo;
import com.ie.Pulse.util.Util;

@Service
public class ExpenseDataService {
	
	@Autowired 
	ExpenseRepo expenseRepo;
	
	
	public boolean checkPartyExistInDatabase(String partyName)
	{
		
		List<ExpenseData> expenseRepoList=expenseRepo.findByPartyName(partyName);
		if(expenseRepoList!=null && expenseRepoList.size()>0)
		{
			return true;
		}
		else 
			return false;
	}
	
	public List<ExpenseData> checkExpenseExistInDatabaseBefore(Party party,String date)
	{
		return expenseRepo.findByPartyAndDate(party, Util.convertStringToDate(date));
	}
	
	public void save(ExpenseData ed)
	{
		expenseRepo.save(ed);
	}
	
	public List<Object[]> getExpenseDateAggregatedByL1L2Vendor(Date startDate,Date endDate)
	{
		return expenseRepo.getcumulativeExpenseDataL1ForDatesBetween(startDate, endDate);
	}
	
	public List<ExpenseData> getDateBetweenStartDateAndEndDate(Date startDate,Date endDate){
		return expenseRepo.findByDateGreaterThanEqualAndDateLessThanEqual(startDate, endDate);
	}
	
	public Map<String, Double> getMonthWiseAccumulatedPageView(Date startDate,Date endDate)
	{
		List<Object[]> monthwisepageview=expenseRepo.getMonthWisePageView(startDate,endDate);
		Map<String, Double> accPageView=new HashMap<>();
		for(Object[] obj:monthwisepageview)
		{
			accPageView.put(obj[0].toString(), Double.valueOf(obj[1].toString()));
		}
		return accPageView;
		
	}
	
	

}
