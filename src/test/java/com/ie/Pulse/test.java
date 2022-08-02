package com.ie.Pulse;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
public class test {
	public static void main(String[] args) {        
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM", Locale.ENGLISH);
	    YearMonth startDate = YearMonth.parse("2022-01", formatter);
	    YearMonth endDate = YearMonth.parse("2022-12", formatter);

	    while(startDate.isBefore(endDate)) {
	        System.out.println(startDate.format(formatter));
	        startDate = startDate.plusMonths(1);
	    }
	}
}
