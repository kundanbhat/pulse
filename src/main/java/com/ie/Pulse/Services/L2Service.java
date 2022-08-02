package com.ie.Pulse.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ie.Pulse.entity.L2;
import com.ie.Pulse.repository.L2Repo;

@Service
public class L2Service {

	
	@Autowired
	private L2Repo l2repo;
	
	
	public List<L2> checkL2ExistInDatabase(String name)
	{
		
		return l2repo.findByName(name);
	}
	
	public void save(L2 l2)
	{
		l2repo.save(l2);
	}
	
	public L2 getL2(long id)
	{
		return l2repo.findById(id).get();
	}
	
	
	
}
