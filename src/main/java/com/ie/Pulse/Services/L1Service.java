package com.ie.Pulse.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ie.Pulse.entity.L1;
import com.ie.Pulse.repository.L1Repo;




@Service
public class L1Service {
	
	
	@Autowired
	private L1Repo l1repo;
	
	
	public List<L1> checkL1ExistInDatabase(String l1)
	{
		return l1repo.findByName(l1);
	}
	
	public void save(L1 l1)
	{
		l1repo.save(l1);
	}
	
	
	
	

}
