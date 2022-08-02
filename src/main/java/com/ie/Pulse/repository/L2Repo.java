package com.ie.Pulse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ie.Pulse.entity.L2;

public interface L2Repo extends JpaRepository<L2, Long>{

	public List<L2> findByName(String name);
	
}
