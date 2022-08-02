package com.ie.Pulse.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ie.Pulse.entity.Brand;
import com.ie.Pulse.repository.BrandRepo;

@Service
public class BrandService {

	@Autowired
	private BrandRepo brandRepo;
	
	public List<Brand> getBrandForName(String name)
	{
		 return brandRepo.findByName(name);
	}
	
	public void save(Brand brand)
	{
		brandRepo.save(brand);
	}
	public List<Brand> getAllBrands()
	{
		return brandRepo.findAll();
	}
	
	public List<Brand> getBrandsForChannelId(int channelId)
	{
		return brandRepo.findByChannelid(channelId);
	}
	public Brand getBrandById(long id)
	{
		return brandRepo.findById(id);
	}
	
	
}
