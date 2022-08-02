package com.ie.Pulse.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ie.Pulse.entity.Employee;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{
	
	public List<Employee> findByEmpcodeAndDate(String empCode,Date date);
	
	public List<Employee> findByEmpcode(String empCode);
	
	@Query("SELECT DATE_FORMAT(a.date, '%Y-%m'), count(*),c.name FROM Employee a join a.brandEmployeeList  b join b.brand c    where b.percent=100   and a.date>=:startDate   and a.date<=:endDate  group by b.brand.id,DATE_FORMAT(a.date, '%Y-%m') ")
	public List<Object[]> getHeadCountReportDailyWiseWith100Percent(Date startDate,Date endDate);
	
	
	@Query("SELECT DATE_FORMAT(a.date, '%Y-%m'), count(*),c.name FROM Employee a join a.brandEmployeeList  b join b.brand c    where b.percent<100   and a.date>=:startDate   and a.date<=:endDate  group by b.brand.id,DATE_FORMAT(a.date, '%Y-%m') ")
	public List<Object[]> getHeadCountReportDailyWiseWithLessThan100Percent(Date startDate,Date endDate);
	
	@Query("SELECT  distinct DATE_FORMAT(a.date, '%Y-%c-%d'),DATE_FORMAT(a.date, '%Y-%m') from Employee a where   a.date>=:startDate   and a.date<=:endDate   order by DATE_FORMAT(a.date, '%Y-%c-%d')")
	public List<Object[]> getDistinctDatesbetweenTwoDates(Date startDate,Date endDate);
	
	@Query("select count( distinct(a.id)) from Employee a join a.brandEmployeeList  b  where   b.percent<100 and a.date=:date")
	public List<Object[]> getUniqueEmployeeFromIntersection(Date date);
	
	@Query("select count(a.id) from Employee a join a.brandEmployeeList  b  where   b.percent=100 and a.date=:date")
	public List<Object[]> getUniqueEmployee(Date date);
	
	
	public List<Employee> findByDateGreaterThanEqualAndDateLessThanEqualOrderByIdAsc(Date startDate,Date endDate);
	
	
	@Query("SELECT b.brand.id,sum(b.percent)    FROM Employee a join a.brandEmployeeList  b  where a.date=:date group by b.brand.id")
	public List<Object[]> getEmployeeCountForMonth(Date date);
	

}
