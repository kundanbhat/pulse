package com.ie.Pulse.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ie.Pulse.entity.Brand;
import com.ie.Pulse.entity.PageView;

public interface PageViewRepo extends JpaRepository<PageView, Long>{

	public List<PageView> findByBrandAndDate(Brand brand, Date date);
	
	
	
}
