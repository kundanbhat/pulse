package com.ie.Pulse.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ie.Pulse.entity.Brand;
import com.ie.Pulse.entity.PageView;
import com.ie.Pulse.repository.PageViewRepo;

@Service
public class PageViewService {

	@Autowired
	private PageViewRepo repo;
	
	public List<PageView> getPageView(Brand brand,Date date)
	{
		return repo.findByBrandAndDate(brand, date);
	}
	public void save(PageView pageView)
	{
		repo.save(pageView);
	}
	
}
