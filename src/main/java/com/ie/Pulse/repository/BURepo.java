package com.ie.Pulse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ie.Pulse.entity.BusinessUnit;


@Repository
public interface BURepo extends JpaRepository<BusinessUnit, Long>{

	
	public List<BusinessUnit> findByName(String name);
	
	
	
	
	
}
