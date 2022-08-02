package com.ie.Pulse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ie.Pulse.entity.MainDepartment;


@Repository
public interface MainDepartmentRepo extends JpaRepository<MainDepartment, Long>{
	
	public List<MainDepartment> findByName(String name);

}
