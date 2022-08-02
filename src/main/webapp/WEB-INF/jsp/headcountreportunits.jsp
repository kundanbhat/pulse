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

			<th class="col-1">Date</th>
			<c:forEach items="${brands}" var="brand">
				<th class="col-2" colspan="2" style="text-align: center">${brand.name}</th>
			</c:forEach>
			<th class="col-2" colspan="2" style="text-align: center">Total</th>
		</tr>
	</thead>
	<tbody>
		<tr style="background-color: #ccc;
">
			<td></td>
			<c:forEach items="${brands}" var="brand">
				<td class="col-1" colspan="1">100</td>
				<td class="col-1" colspan="1"><100</td>
			</c:forEach>
			<td class="col-1" colspan="1">100</td>
			<td class="col-1" colspan="1"><100</td>

		</tr>
		
			<c:forEach var="date" items="${dates}">
			<tr >
				<c:forEach var="datemap" items="${date}">
					<td class="col-1" colspan="1"><fmt:formatDate pattern="MMMyy"
							value="${datemap.value}" /></td>
					<c:set var="key" value="${datemap.key}" />
					<%
    				
    					Map<String, Map<String, HeadCountdto>> headCounts=(Map<String, Map<String, HeadCountdto>>)request.getAttribute("headcount");
    				    Map<String, HeadCountdto> headcountmap=headCounts.get(pageContext.getAttribute("key"));
    				%>
					<c:forEach items="${brands}" var="brand">
						<c:set var="brandKey" value="${brand.name}" />
						
						<%
							HeadCountdto hcdto=headcountmap.get(pageContext.getAttribute("brandKey"));
							if(hcdto!=null)
							{
    					%>
    						<td class="col-1" colspan="1"><%=hcdto.getCountWith100Percent() %></td>
							<td class="col-1" colspan="1"><%=hcdto.getCountWithLessThan100Percent() %></td>
    					<%
    						}
							else
							{
						%>
							<td class="col-1" colspan="1">&nbsp;</td>
							<td class="col-1" colspan="1">&nbsp;</td>
						<%
							}
						%>
    					
					</c:forEach>
					
					<%
							HeadCountdto hcdto=headcountmap.get("total");
					%>
						<td class="col-1" colspan="1"><%=hcdto.getCountWith100Percent() %></td>
						<td class="col-1" colspan="1"><%=hcdto.getCountWithLessThan100Percent() %></td>
					<% 
					
					%>



				</c:forEach>

			</c:forEach>
		

	</tbody>

</table>




