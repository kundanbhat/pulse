<%@page import="com.ie.Pulse.entity.Brand"%>
<%@page import="java.util.List"%>
<%@page import="com.ie.Pulse.entity.HeadCountdto"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>




<%
String dateStr=(String)request.getAttribute("dateStr");
String revenuestr=(String)request.getAttribute("revenuestr");
String expenseStr=(String)request.getAttribute("expenseStr");
String pnl=(String)request.getAttribute("pnl");
String comrevenueStr=(String)request.getAttribute("comrevenueStr");
String comexpenseStr=(String)request.getAttribute("comexpenseStr");
String compnl=(String)request.getAttribute("compnl");
String firstbrand=(String)request.getAttribute("firstbrand");
String secondbrand=(String)request.getAttribute("secondbrand");

String revenuewfStr=(String)request.getAttribute("revenuewfStr");
String expensewfStr=(String)request.getAttribute("expensewfStr");
String pl=(String)request.getAttribute("pl");



%>

<%=dateStr%>#<%=revenuestr%>#<%=expenseStr%>#<%=pnl%>@@@@<%=comrevenueStr%>#<%=comexpenseStr %>#<%=compnl%>@@@@<%=firstbrand %>vs<%=secondbrand %>@@@@<%=revenuewfStr %>#<%=expensewfStr %>#<%=pl %>



