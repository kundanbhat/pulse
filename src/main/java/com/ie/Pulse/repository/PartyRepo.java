package com.ie.Pulse.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ie.Pulse.entity.Party;

@Repository
public interface PartyRepo  extends JpaRepository<Party, Long>{

	
	
	List<Party> findByName(String name);
	
}
