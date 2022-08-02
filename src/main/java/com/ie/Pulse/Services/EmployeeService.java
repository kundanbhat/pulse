package com.ie.Pulse.Services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ie.Pulse.entity.Employee;
import com.ie.Pulse.repository.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo repo;
	
	public List<Employee> getEmployeeList(String empcode,Date date )
	{
		return repo.findByEmpcodeAndDate(empcode,date);
	}
	public List<Employee> getEmployeeList(String empcode)
	{
		return repo.findByEmpcode(empcode);
	}
	public Employee save(Employee emp)
	{
		return repo.save(emp);
	}
	
	public List<Object[]> getEmployeesBasedOnBrandWith100Percent(Date startDate,Date endDate) 
	{
		return repo.getHeadCountReportDailyWiseWith100Percent(startDate, endDate);
	}
	
	
	public List<Object[]> getEmployeesBasedOnBrandWithLessThan100Percent(Date startDate,Date endDate) 
	{
		return repo.getHeadCountReportDailyWiseWithLessThan100Percent(startDate, endDate);
	}
	
	public List<Object[]> getallDatesBetweenTwoDatesMonthWise(Date startDate,Date endDate)
	{
		return repo.getDistinctDatesbetweenTwoDates(startDate, endDate);
	}
	
	
	public List<Object[]> getuniqueemployeeFromIntersection(Date date)
	{
		return repo.getUniqueEmployeeFromIntersection(date);
	}
	
	public List<Object[]> getuniqueemployee(Date date)
	{
		return repo.getUniqueEmployee(date);
	}
	
	public List<Employee> getEmployeeBetweenTwoDate(Date startDate,Date endDate)
	{
		return repo.findByDateGreaterThanEqualAndDateLessThanEqualOrderByIdAsc(startDate, endDate);
	}
	
	public Map<String, Double> getEmployeeHeadCount(Date date)
	{
		List<Object[]> empPercentList=repo.getEmployeeCountForMonth(date);
		Map<String, Double> empPercentMap=new HashMap<>();
		double sum=0;
		for(Object[] obj:empPercentList)
		{
			empPercentMap.put(obj[0].toString(), Double.parseDouble(obj[1].toString()));
			sum=sum+Double.parseDouble(obj[1].toString());
		}
		empPercentMap.put("total", sum);
		return empPercentMap;
	}
	
	
}
