package com.ie.Pulse;

import java.util.List;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import com.ie.Pulse.Services.BuRevenueService;
import com.ie.Pulse.entity.BuRevenue;

import com.ie.Pulse.util.Util;





@SpringBootApplication
public class PulseApplication
{
	
	public static void main(String[] args) {
		SpringApplication.run(PulseApplication.class, args);
		//AddBuDataInitial();
	}
	
	
	@Bean
    public CommandLineRunner demoData(BuRevenueService revenueService) {
		return args -> { 

			AddBuDataInitial(revenueService);
        };
		
    }
	
	
	
	
//	@Autowired
//	static BuRevenueService revenueService;
//	
	public static void AddBuDataInitial(BuRevenueService revenueService)
	{
		for(String s:Util.buRevenueList)
		{
			List<BuRevenue> list=revenueService.getBuByName(s);
			BuRevenue buRev=null;
			if(list!=null && list.size()>0)
			{
				buRev=list.get(0);
			}
			else
			{
				buRev=new BuRevenue();
			}
			buRev.setName(s);
			revenueService.save(buRev);
		}
		
	}
	

}

