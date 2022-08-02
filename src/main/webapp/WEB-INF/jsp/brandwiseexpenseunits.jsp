<%@page import="com.ie.Pulse.entity.Brand"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@page import="com.ie.Pulse.entity.HeadCountdto"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>




<table id="example1" class="table table-bordered table-striped">
	<thead>
		<tr style="
    color: white;
    background-color: #526580;
">

			<th class="col-1">Brand</th>
			<th class="col-1">L1</th>
			<th class="col-1">L2</th>
			<th class="col-1">VENDOR</th>
			<c:forEach items="${datelist}" var="date">
				<th ><fmt:formatDate pattern="MMMyy"
							value="${date}" /></th>
			</c:forEach>
			<th class="col-1">Total</th>
			
		</tr>
	</thead>
	<tbody>
		<%
			List<String> datestrList=(List<String>)request.getAttribute("datestrlist");
		    List<String> brandlist=( List<String>)request.getAttribute("brands");
		    Map<String,Map<String, Map<String,Map<String,Map<String,Double>>>>> brandexpenseMap=( Map<String,Map<String, Map<String,Map<String,Map<String,Double>>>>>)request.getAttribute("expensemap");
			for(Map.Entry<String,Map<String, Map<String,Map<String,Map<String,Double>>>>>  entry : brandexpenseMap.entrySet())
			{
				if(brandlist.contains(entry.getKey()))
				{
				Map<String,Map<String,Map<String,Map<String,Double>>>> l1map=brandexpenseMap.get(entry.getKey());
				for(Map.Entry<String,Map<String,Map<String,Map<String,Double>>>> l1mapItr:l1map.entrySet())
				{
					Map<String,Map<String,Map<String,Double>>> l2map=l1mapItr.getValue();
					for(Map.Entry<String,Map<String,Map<String,Double>>> l2mapItr:l2map.entrySet())
					{
						Map<String,Map<String,Double>> partyMap=l2mapItr.getValue();
						
						for(Map.Entry<String,Map<String,Double>> partMapItr:partyMap.entrySet())
						{
						
						
							Map<String,Double> monthValue=partMapItr.getValue();
								
		%>
					<tr>
								<td><%=entry.getKey() %></td>
								<td><%=l1mapItr.getKey() %></td>
								<td><%=l2mapItr.getKey() %></td>
								<td><%=partMapItr.getKey() %></td>
		
		<% 				
							double sum=0;
						    for(String str:datestrList)
							{
						    	if(monthValue.containsKey(str))
						    	{
						    		sum=sum+monthValue.get(str);
		%>				
									<td><%=monthValue.get(str) %></td>
								
							
									
								
		<% 						
								}
						    	else
						    	{
	   %>
	   							
	   								<td>&nbsp;</td>
	   <%					    		
						    	}
						    		
						    }
						    request.setAttribute("sum", sum);
		
		%>
					<td><fmt:formatNumber
									value="${sum}" pattern="#,##,###"
									groupingUsed="true" /></td>
					</tr>
		<% 
					}
					
				}
					
					
				}
				
				}
			}
		    
		   
		
		%>

	</tbody>

</table>




