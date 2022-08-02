package com.ie.Pulse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ie.Pulse.entity.BusinessUnit;
import com.ie.Pulse.entity.BusinessUnitEmployee;
import com.ie.Pulse.entity.Employee;

public interface BusinessUnitEmployeeRepo extends JpaRepository<BusinessUnitEmployee, Long>{

	public List<BusinessUnitEmployee> findByBuunitAndEmployee(BusinessUnit bunit,Employee employee);
	
	
}
