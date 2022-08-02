package com.ie.Pulse.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ie.Pulse.entity.Actual;
import com.ie.Pulse.entity.Brand;
import com.ie.Pulse.entity.Party;
import com.ie.Pulse.repository.ActualRepo;

@Service
public class ActualService {

	@Autowired
	private ActualRepo repo;
	
	
	public List<Actual> getActuals(Brand brand,Party party,Date date)
	{
		return repo.findByBrandAndPartyAndDate(brand, party, date);
	}
	public void save(Actual actual)
	{
		repo.save(actual);
	}
	
}
