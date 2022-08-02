package com.ie.Pulse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ie.Pulse.entity.Brand;

public interface BrandRepo extends JpaRepository<Brand, Long>{
	
	public List<Brand> findByName(String name);
	public List<Brand> findByChannelid(int channelId);
	public Brand findById(long id);

}
