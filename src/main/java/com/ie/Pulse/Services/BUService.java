package com.ie.Pulse.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ie.Pulse.entity.BusinessUnit;
import com.ie.Pulse.repository.BURepo;

@Service
public class BUService {
	
	@Autowired
	private BURepo buRepo;
	
	
	public List<BusinessUnit> getBusinessUnitByName(String name)
	{
		return buRepo.findByName(name);
	}
	
	public void save(BusinessUnit buint)
	{
		buRepo.save(buint);
	}
	

}
