<%@page import="java.util.Set"%>
<%@page import="com.ie.Pulse.entity.ProfitLoss"%>
<%@page import="com.ie.Pulse.entity.Brand"%>
<%@page import="java.util.List"%>
<%@page import="com.ie.Pulse.entity.HeadCountdto"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%
List<String> datestrList=(List<String>)request.getAttribute("datestrlist");
Set<Brand> brands=(Set<Brand>)request.getAttribute("brandlist");
Map<String, Map<String, ProfitLoss>> profitLoss =(Map<String, Map<String, ProfitLoss>>)request.getAttribute("profitloss");

%>


<table id="example1" class="table table-bordered table-striped">
	<thead>
	<tr>
		
		
		<th>Brand</th>
		<th>&nbsp;</th>
		<c:forEach items="${datelist}" var="date">
				<th ><fmt:formatDate pattern="MMMyy"
							value="${date}" /></th>
			</c:forEach>
		<th>Total</th>	
	</tr>		
	</thead>
	<tbody>
<% 	
for(Brand brand:brands)
{

	Map<String, ProfitLoss> profitLossMap =profitLoss.get(brand.getName());
	if(profitLossMap!=null)
	{
%>	
	
	
		<tr>
			<td rowspan="4" style="vertical-align : middle;text-align:center;" "><%=brand.getName()%></td> 
			<td>
			 	Revenue
			</td>
			<% 		
				double revenueSum=0;
				for(String str:datestrList)
				{
					if(profitLossMap.containsKey(str))
					{
						
						
					ProfitLoss pf=profitLossMap.get(str);
					revenueSum=revenueSum+pf.getRevenue();
					request.setAttribute("revenue", pf.getRevenue());	
			%>				
						<td><fmt:formatNumber
						value="${revenue}" type="number"
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
				request.setAttribute("revenueSum", revenueSum);
			
		%>
			<td><fmt:formatNumber
						value="${revenueSum}" type="number"
						pattern="##,##,###" /></td>
		</tr>
		
		<tr>
			<td>
			 Expense
			</td>
			<% 	
				double expenseSum=0;
				for(String str:datestrList)
				{
					if(profitLossMap.containsKey(str))
					{
						
						
					ProfitLoss pf=profitLossMap.get(str);
					expenseSum=expenseSum+pf.getExpense();
					request.setAttribute("expense", pf.getExpense());	
			%>				
						<td><fmt:formatNumber
						value="${expense}" type="number"
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
				request.setAttribute("expenseSum", expenseSum);
		%>
			
			<td><fmt:formatNumber
						value="${expenseSum}" type="number"
						pattern="##,##,###" /></td>
			
		</tr>
		<tr>
			<td>
			 Profit/Loss
			</td>
			<% 		
				for(String str:datestrList)
				{
					if(profitLossMap.containsKey(str))
					{
						
						
					ProfitLoss pf=profitLossMap.get(str);	
					request.setAttribute("profit", pf.getProfit());	
			%>				
						<td><fmt:formatNumber
						value="${profit}" type="number"
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
		
			double totalProfit=revenueSum-expenseSum;
			request.setAttribute("totalProfit", totalProfit);
		%>
		<td><fmt:formatNumber
						value="${totalProfit}" type="number"
						pattern="##,##,###" /></td>
		</tr>
		<tr>
			<td>
			 Profit %
			</td>
			<% 		
				for(String str:datestrList)
				{
					if(profitLossMap.containsKey(str))
					{
						
						
					ProfitLoss pf=profitLossMap.get(str);	
					request.setAttribute("profitPercent", pf.getProfitPercent());	
			%>				
						<td><fmt:formatNumber
						value="${profitPercent}" type="number"
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
			double profitPercentTotal=((revenueSum-expenseSum)/revenueSum)*100;
			request.setAttribute("profitPercentTotal", profitPercentTotal);
		%>
			<td><fmt:formatNumber
						value="${profitPercentTotal}" type="number"
						pattern="##,##,###" /></td>
		</tr>
<%
}
}
%>		
	
	</tbody>
</table>		
	