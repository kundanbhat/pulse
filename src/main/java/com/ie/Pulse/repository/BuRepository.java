package com.ie.Pulse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ie.Pulse.entity.BuRevenue;


@Repository
public interface BuRepository extends JpaRepository<BuRevenue, Long>{
	
	public List<BuRevenue> getBuRevenueByName(String name);
	

	

}
