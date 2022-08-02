<%@page import="java.util.List"%>
<%@page import="com.ie.Pulse.entity.HeadCountdto"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<table id="example1" class="table table-bordered table-striped">
	<thead>
		<tr>

			<th>Head</th>
			<c:forEach items="${datelist}" var="date">
				<th><fmt:formatDate pattern="MMMyy" value="${date}" /></th>
			</c:forEach>
		</tr>
	</thead>
	<tbody>
		<%
		List<String> datestrList=(List<String>)request.getAttribute("datestrlist");
		List<String> brandlist=(List<String>)request.getAttribute("brandlist");
		Map<String, Map<String,Double>> brandTotal=(Map<String, Map<String,Double>>) request.getAttribute("brandTotal");
		Map<String,Map<String,Map<String, Map<String,Double>>>>headTotal=(Map<String,Map<String,Map<String, Map<String,Double>>>>) request.getAttribute("headTotal");
		Map<String,Map<String, Map<String,Map<String,Map<String,Double>>>>> revenueMap=(Map<String,Map<String, Map<String,Map<String,Map<String,Double>>>>>)request.getAttribute("revenuemap");
		Map<String,Map<String, Map<String,Double>>> buTotal=(Map<String,Map<String, Map<String,Double>>>)request.getAttribute("butotal");
		for(String brand:brandlist)
		{
			Map<String,Double> monthTotalList=brandTotal.get(brand);
			if(monthTotalList!=null)
			{
	%>
		<tr onclick="opentrs('<%=brand%>')"
			style="background-color: #343a3f; color: white">
			<td><%=brand%></td>
			<% 		
				for(String str:datestrList)
				{
					if(monthTotalList.containsKey(str))
					{
						request.setAttribute("value", monthTotalList.get(str));	
		%>
			<td><fmt:formatNumber value="${value}" type="number"
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
			
		 Map<String,Map<String,Map<String,Map<String,Double>>>> bumap=revenueMap.get(brand);
		 if(bumap!=null)
		 {	 
			 for(Map.Entry<String,Map<String,Map<String,Map<String,Double>>>>  bumapitr : bumap.entrySet())
			 {
				 Map<String, Map<String,Double>> buTotalMap=buTotal.get(brand);
				 if(buTotalMap!=null)
				 {
					 Map<String,Double> monthBuTotal=buTotalMap.get(bumapitr.getKey());
					 %>
		<tr onclick="opentrs('<%=bumapitr.getKey()%><%=brand%>')"
			class="<%=brand%>"
			style="display: none; background-color: rgb(60, 94, 121); color: white">
			<td><%=bumapitr.getKey()%></td>
			<% 	
					for(String str:datestrList)
					{
						if(monthBuTotal.containsKey(str))
						{
							request.setAttribute("value", monthBuTotal.get(str));	
			%>
			<td><fmt:formatNumber value="${value}" type="number"
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
					 
					 
				 
				 
				 
	%>


		<%
		 Map<String, Map<String, Map<String,Double>>> butotalMap=headTotal.get(brand);
			if(butotalMap!=null)
			{
				
				
				
					
					Map<String,Map<String,Double>> headmap1=butotalMap.get(bumapitr.getKey());
					for(Map.Entry<String,Map<String,Double>> headmapitr:headmap1.entrySet())
					{
					
					
	%>
		<tr
			style="display: none; background-color: rgb(108, 117, 125); color: white"
			class="<%=bumapitr.getKey()%><%=brand %>"
			onclick="opentrs('<%=brand%><%=headmapitr.getKey()%>')">
			<td><%=headmapitr.getKey()%></td>
			<% 					
					Map<String,Double> monthheadList=headmapitr.getValue();
					for(String str:datestrList)
					{
						if(monthheadList.containsKey(str))
						{
						request.setAttribute("value", monthheadList.get(str));		
	%>
			<td><fmt:formatNumber value="${value}" type="number"
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
			   Map<String,Map<String,Map<String,Double>>> headmap=bumapitr.getValue();
				if(headmap!=null)
				{
					if(headmap.containsKey(headmapitr.getKey()))
					{
						Map<String,Map<String,Double>> subheadmap=headmap.get(headmapitr.getKey());
						if(subheadmap!=null)
						{
							for(Map.Entry<String,Map<String,Double>>  monthmapitr : subheadmap.entrySet())
							{
								Map<String,Double> monthValue=monthmapitr.getValue();
								 
								%>
		<tr style="display: none;" class="<%=brand%><%=headmapitr.getKey()%>">
			<th><%=monthmapitr.getKey() %></th>



			<% 				
													double sum=0;
												    for(String str:datestrList)
													{
												    	if(monthValue.containsKey(str))
												    	{
												    		request.setAttribute("value", monthValue.get(str));		
								%>
			<td><fmt:formatNumber value="${value}" type="number"
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
												    request.setAttribute("sum", sum);
								
								%>
		</tr>
		<% 				 
							}
							
							
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
