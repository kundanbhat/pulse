<%@page import="java.util.List"%>
<%@page import="com.ie.Pulse.entity.HeadCountdto"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<table id="example1" class="table table-bordered table-striped">
	<thead>
	<tr>
		
		<th>Head</th>
		<th>Brand</th>
		<c:forEach items="${datelist}" var="date">
				<th ><fmt:formatDate pattern="MMMyy"
							value="${date}" /></th>
			</c:forEach>
	</tr>		
	</thead>
	<tbody>
	<%
		List<String> datestrList=(List<String>)request.getAttribute("datestrlist");
	    Map<String,Map<String,Map<String, Map<String,Double>>>> headbrandMap=(Map<String,Map<String,Map<String, Map<String,Double>>>>)request.getAttribute("headbrandMap");
	    Map<String,Map<String,Map<String,Double>>> sumheadMap=(Map<String,Map<String,Map<String,Double>>>)request.getAttribute("sumheadMap");
	    Map<String,Map<String,Double>> buTotalMap=(Map<String,Map<String,Double>>)request.getAttribute("butotalMap");
		for(Map.Entry<String,Map<String,Map<String,Map<String,Double>>>>  bumap : headbrandMap.entrySet())
		{
			
			if(buTotalMap.containsKey(bumap.getKey()))
			{
				Map<String,Double> butotMonthList=buTotalMap.get(bumap.getKey());
				%>
				<tr onclick="opentrs('<%=bumap.getKey()%>')" style="background-color: #343a3f;color: white" >
				
				<td><%=bumap.getKey()%></td>
				<td>&nbsp;</td>
				
			<% 		
				for(String str:datestrList)
				{
					if(butotMonthList.containsKey(str))
					{
						request.setAttribute("value", butotMonthList.get(str));	
		%>				
						<td><fmt:formatNumber
						value="${value}" type="number"
						pattern="##,##,###" /></td>
		<% 					
					}
					else
					{
		%>
						<td>&nbsp;</td>
		<% 				
					}
				}
			
	%>
		</tr>
		
				
			<%
				
				
			
			
			Map<String,Map<String,Map<String,Double>>> headmap=bumap.getValue();
			if(headmap!=null)
			{
			for(Map.Entry<String,Map<String,Map<String,Double>>>  headbrandMapitr : headmap.entrySet())
			{
			
			Map<String,Map<String,Double>> butotalmap=sumheadMap.get(bumap.getKey());
			Map<String,Double> monthTotalList=butotalmap.get(headbrandMapitr.getKey());			
			if(monthTotalList!=null)
			{
	%>
		<tr onclick="opentrs('<%=headbrandMapitr.getKey()%><%=bumap.getKey() %>')"   class="<%=bumap.getKey() %>" style="display:none;  background-color: rgb(60, 94, 121);color: white" >
		
		<td><%=headbrandMapitr.getKey()%></td>
		<td>&nbsp;</td>
		
	<% 		
				for(String str:datestrList)
				{
					if(monthTotalList.containsKey(str))
					{
						request.setAttribute("value", monthTotalList.get(str));	
		%>				
						<td><fmt:formatNumber
						value="${value}" type="number"
						pattern="##,##,###" /></td>
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
				Map<String, Map<String,Double>> brandmap  =headbrandMapitr.getValue();
				if(brandmap!=null)
				{
					for(Map.Entry<String,Map<String,Double>>  brandmapitr : brandmap.entrySet())
					{
		     %>
		     			<tr style="display: none;" class="<%=headbrandMapitr.getKey()%><%=bumap.getKey() %>">
		     			
		     			<td>&nbsp;</td>
		     				<td><%=brandmapitr.getKey() %></td>
		     				
		     <% 	
		     			 Map<String,Double> monthList=brandmapitr.getValue();
		     for(String str:datestrList)
				{
					if(monthList.containsKey(str))
					{
						request.setAttribute("value", monthList.get(str));	
		%>				
						<td><fmt:formatNumber
						value="${value}" type="number"
						pattern="##,##,###" /></td>
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
			}
			}
			}
	
		}
	
	%>
	</tbody>
</table>	
								