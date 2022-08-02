package com.ie.Pulse.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ie.Pulse.entity.Actual;
import com.ie.Pulse.entity.Brand;
import com.ie.Pulse.entity.Party;

public interface ActualRepo extends JpaRepository<Actual, Long>{
	
	
	public List<Actual> findByBrandAndPartyAndDate(Brand brand,Party party,Date date);

}
