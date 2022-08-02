package com.ie.Pulse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ie.Pulse.entity.Brand;
import com.ie.Pulse.entity.BrandEmployee;
import com.ie.Pulse.entity.Employee;


@Repository
public interface BrandEmployeeRepo extends JpaRepository<BrandEmployee,Long>{
	
	public List<BrandEmployee> findByBrandAndEmployee(Brand brand,Employee employee);
	

	
	
}
