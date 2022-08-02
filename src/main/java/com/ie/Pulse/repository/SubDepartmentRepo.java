package com.ie.Pulse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.ie.Pulse.entity.SubDepartment;


@Repository
public interface SubDepartmentRepo extends JpaRepository<SubDepartment, Long>{

	
	public List<SubDepartment> findByName(String name);
}
