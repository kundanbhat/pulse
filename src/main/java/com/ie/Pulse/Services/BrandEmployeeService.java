package com.ie.Pulse.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ie.Pulse.entity.Brand;
import com.ie.Pulse.entity.BrandEmployee;
import com.ie.Pulse.entity.Employee;
import com.ie.Pulse.repository.BrandEmployeeRepo;

@Service
public class BrandEmployeeService {
	
	@Autowired
	private BrandEmployeeRepo repo;
	
	
	public List<BrandEmployee> getBrandEmployeeList(Brand brand,Employee employee)
	{
	 	return repo.findByBrandAndEmployee(brand, employee);
	}
	
	public void save(BrandEmployee brandEmployee)
	{
		repo.save(brandEmployee);
	}

	
	
}
