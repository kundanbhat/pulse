package com.ie.Pulse.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ie.Pulse.entity.BusinessUnit;
import com.ie.Pulse.entity.BusinessUnitEmployee;
import com.ie.Pulse.entity.Employee;
import com.ie.Pulse.repository.BusinessUnitEmployeeRepo;

@Service
public class BusinessUnitEmployeeService {

	@Autowired
	private BusinessUnitEmployeeRepo repo;
	
	
	public List<BusinessUnitEmployee> getBusinessUnitEmployee(BusinessUnit bunit,Employee employee)
	{
		return repo.findByBuunitAndEmployee(bunit, employee);
	}
	
	public void save(BusinessUnitEmployee buemp)
	{
		repo.save(buemp);
	}
	
	
}
