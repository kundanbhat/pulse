package com.ie.Pulse.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ie.Pulse.entity.BuRevenue;
import com.ie.Pulse.repository.BuRepository;

@Service
public class BuRevenueService {

	@Autowired
	BuRepository repo;
	
	
	public BuRevenue getBuRevenueById(long id)
	{
		return  repo.findById(id).get();
		
	}
	
	public void save(BuRevenue rev)
	{
		repo.save(rev);
	}

	public List<BuRevenue> getBuByName(String name)
	{
		return repo.getBuRevenueByName(name);
	}
	
	
}
