package com.ie.Pulse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ie.Pulse.entity.RevenueHead;

@Repository
public interface RevenueHeadRepo extends JpaRepository<RevenueHead, Long>{
	
	public List<RevenueHead> findByName(String name);
	public RevenueHead findById(long id);

}
