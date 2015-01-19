<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %> 
<%@ page import="java.util.*" %>
<%@ page import="il.ac.hit.taxitogo.model.taxicompany.*" %>

<% 
	// Getting the request context path
	String context = request.getContextPath();
%>
	
<!DOCTYPE html>
<html>
	<head>
		<title>Http sessions list</title>
			
		<!-- CSS -->
		<link href="<%=context%>/css/font-awesome.min.css" rel="stylesheet">
		<link href="<%=context%>/css/bootstrap.min.css" rel="stylesheet">
		<link href="<%=context%>/css/stylish-portfolio.css" rel="stylesheet">
		<link href="<%=context%>/css/templatemo_style.css" rel="stylesheet">
		<link href="<%=context%>/css/driver-list.css" rel="stylesheet">
	</head>
		
	<body>
		<div id="templatemo_header_wrapper">
			<!-- Main Logo -->
			<div id="logo">
				<!-- Main Logo Button -->
				<a href="<%=context%>/index.html">
 					<img src="<%=context%>/images/logo-1.png" height="90" width="300">
				</a>
			</div>
				
			<!-- Header Buttons -->
			<div id="homebutton2">
				<!-- Home Button -->
				<a href="<%=context%>/index.html">
					<button type="submit" class="btn btn-link btn-lg"><i class="fa fa-home"></i> Home</button>
				</a>
				<!-- Admin Button -->
				<a href="<%=context%>/admin_index.jsp">
					<button type="submit" class="btn btn-link btn-lg"><i class="fa fa-user"></i> Admin</button>
				</a>
			</div>
		</div>
		
		<!-- Banner -->
		<div id="templatemo_banner_wrapper">
			<div id="templatemo_banner"></div>
		</div>
		
		<!-- Title -->
		<div id="column_w800">
			<div class="margin_bottom_20"></div>
			<!-- Title -->
			<div class="header_02">Taxi cars http sessions list</div>
				<p>See all the info about Taxi To Go http sessions</p><br>
			<div class="cleaner"></div>
		</div>

	<%
		@SuppressWarnings("unchecked")
	
		// Getting a list of all http session events
		LinkedList<HttpSession> sessionsToDisplay = (LinkedList<HttpSession>) application.getAttribute("sessions");
	%>
		<!-- Taxi cars custom tag -->
		<%@ taglib tagdir="/WEB-INF/tags" prefix="customtag" %>
		<customtag:sessionlist sessionsList="<%=sessionsToDisplay%>"></customtag:sessionlist>
				
		<!-- Footer -->
		<div class="cleaner"></div>
		<div id="templatemo_footer_wrapper">
		<div id="templatemo_footer"></div></div>
	</body>
</html>