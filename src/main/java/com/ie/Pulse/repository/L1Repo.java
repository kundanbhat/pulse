package com.ie.Pulse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ie.Pulse.entity.L1;

@Repository
public interface L1Repo extends JpaRepository<L1, Long>{
	
	public List<L1> findByName(String name);

}
