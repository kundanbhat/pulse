package com.ie.Pulse.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import com.ie.Pulse.Services.BrandService;
import com.ie.Pulse.Services.PageViewService;
import com.ie.Pulse.entity.Brand;
import com.ie.Pulse.entity.PageView;

public class Util {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdfDateOnly = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat sdfMonth = new SimpleDateFormat("dd-MMM-yyyy");
	
	static SimpleDateFormat sdfDateMonthFormat = new SimpleDateFormat("MM/dd/yyyy");
	public static String[] monthNames={"","January", "February", "March","April","May","June","July","August","September","October","November", "December"};
	public static String[] buRevenueList= {"AD-SALES","EVENTS","SUBSCRIPTION"};
	
	
	@Autowired
	static RestTemplate restTemplate;
	
	
	
	public static void main(String[] args)
	{
		Date date=new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern("MM");
		String month=simpleDateFormat.format(date);
		simpleDateFormat.applyPattern("yyyy");
		String year=simpleDateFormat.format(date);
		System.out.println(year+"-"+month);
	      
	}
	
	
	public static String getYearMonth(Date date)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern("MM");
		String month=simpleDateFormat.format(date);
		simpleDateFormat.applyPattern("yyyy");
		String year=simpleDateFormat.format(date);
		return year+"-"+month;
		
		
	}
	
	
	public static String getMonthYear(Date date)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern("MMM");
		String month=simpleDateFormat.format(date);
		simpleDateFormat.applyPattern("yyyy");
		String year=simpleDateFormat.format(date);
		return month+"-"+year;
		
		
	}
	
	
	
	
	public static Date getDateWithStartTime()
	{
		Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
       
        now.set(Calendar.HOUR_OF_DAY, 0);
        return now.getTime();
	}
	
	
	public static Date getDateWithEndTime()
	{
		Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR, 23);
        now.set(Calendar.MINUTE, 59);
        now.set(Calendar.SECOND, 59);
       
        now.set(Calendar.HOUR_OF_DAY, 23);
        return now.getTime();
	}
	
	public static  Date yesterday() {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);
	    return cal.getTime();
	}
	
	
	public static Date convertStringToDateWithMonth(String strDate)
	{
		
		try {
			Date date=sdfDateOnly.parse(strDate+"-01");
			
		
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	
	public static Date convertStringToDateInYYYYMMDD(String strDate)
	{
		
		try {
			Date date=sdfDateOnly.parse(strDate);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	public static Date convertStringToDate(String strDate)
	{
		
		try {
			Date date=sdfMonth.parse(strDate);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//System.out.println("date exception is caught");
			return null;
		}
		
		
	}
	
	
	public static Date convertStringToDateStartWithMonth(String strDate)
	{
		
		try {
			Date date=sdfDateMonthFormat.parse(strDate);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//System.out.println("date exception is caught");
			return null;
		}
		
		
	}
	
	
	public static Date convertStringToDateFromMonthYear(String strDate)
	{
		
		try {
			Date date=sdfMonth.parse(strDate);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	public static Date getStartDateFromCurrentMonthAndYear()
	{
		
		try {
			Calendar calendar = getCalendarForNow();
	        calendar.set(Calendar.DAY_OF_MONTH,
	                calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
	        setTimeToBeginningOfDay(calendar);
			return calendar.getTime();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	private static Calendar getCalendarForNow() {
	    Calendar calendar = GregorianCalendar.getInstance();
	    calendar.setTime(new Date());
	    return calendar;
	}
	
	private static void setTimeToBeginningOfDay(Calendar calendar) {
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	}
	
	public static List<String> getListOfDateBetweenTwoDates(String startDate,String endDate){
		
        List<String> monthList=new ArrayList<String>();
        DateFormat formater = new SimpleDateFormat("MMM-yyyy");
        Calendar beginCalendar = Calendar.getInstance();
        Calendar finishCalendar = Calendar.getInstance();

        try {
            beginCalendar.setTime(convertStringToDate(startDate));
            finishCalendar.setTime(convertStringToDate(endDate));
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (beginCalendar.before(finishCalendar)) {
            // add one month to date per loop
            String date =     formater.format(beginCalendar.getTime()).toUpperCase();
            monthList.add(date);
            beginCalendar.add(Calendar.MONTH, 1);
        }
        return monthList;
	}
	
	public static Date getStartDayOfDate(Date date)
	{
		 Calendar c = Calendar.getInstance(); 
		 c.setTime(date);
		 c.set(Calendar.DAY_OF_MONTH, 1);
		 return c.getTime();
		 
		 
	}
	
	
	public static boolean isToday(Date date){
        Calendar today = Calendar.getInstance();
        Calendar specifiedDate  = Calendar.getInstance();
        specifiedDate.setTime(date);

        return today.get(Calendar.DAY_OF_MONTH) == specifiedDate.get(Calendar.DAY_OF_MONTH)
                &&  today.get(Calendar.MONTH) == specifiedDate.get(Calendar.MONTH)
                &&  today.get(Calendar.YEAR) == specifiedDate.get(Calendar.YEAR);
    }
	
	
	public static Date getDateMinusMonth(int month)
	{
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, -month);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
		
	}
	public static long getDifferenceDays(Date d1, Date d2) {
	    long diff = d2.getTime() - d1.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	
	public static int getNoOfDaysInMonth(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
	    return  cal.getActualMaximum(Calendar.DATE);
	     
	     
	}
	
	public static Date setLastDayOfMonth(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, getNoOfDaysInMonth(date));
	    return  cal.getTime();
	     
	     
	}
	public static Date setFirstDayOfMonth(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
	    return  cal.getTime();
	     
	     
	}
	
	
	public static List<String> getAllMonthsBetweenTwoDates(String startDateStr,String endDateStr)
	{
		List<String> allDates=new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM", Locale.ENGLISH);
		
	    YearMonth startDate = YearMonth.parse(startDateStr, formatter);
	    YearMonth endDate = YearMonth.parse(endDateStr, formatter);

	    while(startDate.isBefore(endDate)) {
	    	 allDates.add(  startDate.format(formatter));
	    	 startDate = startDate.plusMonths(1);
	       
	    }
	     allDates.add(endDateStr);
	     return allDates;
	}
	
	
	
	
	
	public void extracode()
	{
	try {
		
			PageViewService pageViewService=new PageViewService();
			BrandService brandService=new BrandService();
			HttpHeaders headers = new HttpHeaders();
			headers.set("API-KEY", "0355c2d801f6d0fee2f1977f14cc673d25d7335d2324f7e8a5db3bdfce04d0f5");
		    headers.set("origin","https://ua.indianexpress.com");
		    HttpEntity <Object> entity = new HttpEntity<Object>(headers);
		    String response= restTemplate.exchange("https://ua.indianexpress.com/api/reports/monthly/pv", HttpMethod.GET, entity, String.class).getBody();
		    JSONArray pageViewArr=new JSONArray(response);
			for(int i=0;i<pageViewArr.length();i++)
			{
				JSONObject jb = pageViewArr.getJSONObject(i);
				int year=jb.getInt("Year");
				JSONArray monthArr=jb.getJSONArray("MonthsList");
				for(int j=0;j<monthArr.length();j++)
				{
					JSONObject monthObj=monthArr.getJSONObject(j);
					int month=monthObj.getInt("Month");
					JSONArray pg=monthObj.getJSONArray("Channels");
					for(int k=0;k<pg.length();k++)
					{
						JSONObject pageObject=pg.getJSONObject(k);
						int pageview=pageObject.getInt("PageView");
						int channelId=pageObject.getInt("ChannelId");
						
						List<Brand> brandlist=brandService.getBrandsForChannelId(channelId);
						if(brandlist!=null && brandlist.size()>0)
						{
							Date date=Util.convertStringToDateStartWithMonth(year+"-"+month);
							List<PageView> pageViewList=pageViewService.getPageView (brandlist.get(0), date);
							PageView pageView=null;
							if(pageViewList!=null && pageViewList.size()>0)
							{
								 pageView=pageViewList.get(0);
							}
							else
							{
								pageView=new PageView();
							}
							pageView.setBrand(brandlist.get(0));
							pageView.setDate(date);
							pageView.setPageview(pageview);
							//pageViewService.save(pageView);
							
							
							
							
						}
					}
				}
			}
			
			
		}
		catch(Exception ex) {}
	}
	
	
	
	

	
	
	

}