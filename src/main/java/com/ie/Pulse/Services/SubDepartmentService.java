package com.ie.Pulse.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ie.Pulse.entity.SubDepartment;
import com.ie.Pulse.repository.SubDepartmentRepo;

@Service
public class SubDepartmentService {

	
	@Autowired
	private SubDepartmentRepo repo;
	
	
	public List<SubDepartment> getSubDepartmentList(String name)
	{
		return repo.findByName(name);
	}
	public void save(SubDepartment subdepartment)
	{
		repo.save(subdepartment);
	}
	
}
