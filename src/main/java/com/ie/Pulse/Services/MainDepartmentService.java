package com.ie.Pulse.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ie.Pulse.entity.MainDepartment;
import com.ie.Pulse.repository.MainDepartmentRepo;

@Service
public class MainDepartmentService {

	@Autowired
	private MainDepartmentRepo repo;
	
	public List<MainDepartment> getMainDepartmentList(String name)
	{
		return repo.findByName(name);
	}
	
	public void save(MainDepartment mdepartment)
	{
		repo.save(mdepartment);
	}
	
	
}
