package com.ie.Pulse.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ie.Pulse.entity.RevenueHead;
import com.ie.Pulse.repository.RevenueHeadRepo;

@Service
public class RevenueHeadService {
	
	
	@Autowired
	RevenueHeadRepo repo;
	
	
	public List<RevenueHead> getRevenueHeadByName(String name)
	{
		return repo.findByName(name);
	}
	
	public void save(RevenueHead head)
	{
		repo.save(head);
	}
	public RevenueHead getRevenueDateById(long id)
	{
		return repo.findById(id);
	}

}
