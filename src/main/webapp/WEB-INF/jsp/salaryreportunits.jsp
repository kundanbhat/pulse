<%@page import="com.ie.Pulse.entity.Brand"%>
<%@page import="java.util.List"%>
<%@page import="com.ie.Pulse.entity.HeadCountdto"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<table id="example1" class="table table-bordered table-striped">
	<thead>
		<tr style="color: white;background-color: #526580;">
			<th class="col-1">EmpCode</th>
			<c:forEach items="${dates}" var="date">
				<th class="col-"${fn:length(brands)} colspan="${fn:length(brands)}" style="text-align: center;">${date}</th>
			</c:forEach>
		</tr>
		<%
		List<String> dateList=(List<String>)request.getAttribute("dates");
		List<Brand> brands=(List<Brand>)request.getAttribute("brands");
		
		
		
		
		%>
		
		<tr>
			<th class="col-1">&nbsp;</th>
			<c:forEach items="${dates}" var="date">
				<c:forEach items="${brands}" var="brand">
					<th class="col-1" colspan="1" >${brand.name}</th>
				</c:forEach>
		 </c:forEach>	
		</tr>
		<%
			Map<String, Map<String, Map<String, Double>>> salaryDivision=(Map<String, Map<String, Map<String, Double>>>)request.getAttribute("salaryDivision");
			for (Map.Entry<String, Map<String, Map<String, Double>>> entry : salaryDivision.entrySet())
			{
		%>
		<tr>
			<td><%=entry.getKey() %></td>
		
		<%		Map<String, Map<String, Double>> dateMap=salaryDivision.get(entry.getKey());
				for(String dateKey:dateList)
				{
					Map<String, Double> brandMap=dateMap.get(dateKey);
					for(Brand brand:brands)
					{
						if(brandMap!=null &&  brandMap.containsKey(brand.getName()))
						{
			%>
				<td><%=brandMap.get(brand.getName()) %></td>
			<% 					
							}
							else
							{
			%>
					<td>&nbsp;</td>
			<% 				
							
						}
						
						
					}
					
					
				}
				
			}
		%>
		</tr>
		<tr style="background-color: #c9e1f6">
		<td><b>Total</b></td>
		<%
		Map<String, Map<String, Double>> totalMap=(Map<String, Map<String, Double>>)request.getAttribute("total");
			for(String date:dateList)
			{
				if(totalMap.containsKey(date))
				{
					Map<String, Double> totalMonth=totalMap.get(date);
					
					for(Brand brand:brands)
					{
						if(totalMonth.containsKey(brand.getName()))
						{
		%>
		
						<td><%=totalMonth.get(brand.getName()) %></td>
		<% 					
						}
						else
						{
		%>
						<td>&nbsp;</td>	
		<% 
							
						}
					}
				}
				
				
				
			}
		
		%>
		
		
		</tr>
		
	</thead>


</table>