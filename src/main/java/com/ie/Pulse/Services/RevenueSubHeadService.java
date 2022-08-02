package com.ie.Pulse.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ie.Pulse.entity.RevenueHead;
import com.ie.Pulse.entity.RevenueSubHeads;
import com.ie.Pulse.repository.SubRevenueHeadRepo;

@Service
public class RevenueSubHeadService {
	
	
	@Autowired
	private SubRevenueHeadRepo repo;
	
	public List<RevenueSubHeads> getRevenueSubHeadByName(String name,RevenueHead head)
	{
		return repo.findByNameAndRevenuehead(name,head);
	}
	
	public void save(RevenueSubHeads subhead)
	{
		repo.save(subhead);
	}
	

}
