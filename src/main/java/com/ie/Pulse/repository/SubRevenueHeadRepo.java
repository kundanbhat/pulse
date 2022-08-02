package com.ie.Pulse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ie.Pulse.entity.RevenueHead;
import com.ie.Pulse.entity.RevenueSubHeads;

@Repository
public interface SubRevenueHeadRepo extends JpaRepository<RevenueSubHeads, Long>{
	
	public List<RevenueSubHeads> findByNameAndRevenuehead(String name,RevenueHead head);

}
