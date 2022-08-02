<%@page import="java.util.Set"%>
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
			<th class="col-1">Date</th>
			<c:forEach items="${brands}" var="brand">
					<th class="col-1" colspan="1" >${brand.name}</th>
			</c:forEach>
		</tr>
		</thead>
		<tbody>
		<%
		List<String> dateList=(List<String>)request.getAttribute("dates");
		Set<Brand> brands=(Set<Brand>)request.getAttribute("brands");
		
		
		
		
		%>
		
		
		
		
		
		
		<%
		Map<String, Map<String, Double>> totalMap=(Map<String, Map<String, Double>>)request.getAttribute("total");
			for(String date:dateList)
			{
		%>
		
			<tr >
				<td><b><%=date %></b></td>
		<% 		
				
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
		%>
			</tr>
		<%		
				
				
			}
		
		%>
		
		
		
		
	</tbody>


</table>