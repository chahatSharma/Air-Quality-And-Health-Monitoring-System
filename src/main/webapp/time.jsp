<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*" %>
<%
	response.setHeader("Pragma", "No-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-cache");

	Date date = new Date();
	String newDate = new SimpleDateFormat("YYYY-MM-dd").format(date);
	out.println(newDate);
	%>
