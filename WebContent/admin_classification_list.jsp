<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %> 
<%@ page import="java.util.*" %>
<%@ page import="il.ac.hit.taxitogo.model.taxicar.*" %>
	
<% 
	// Getting the request context path
	String context = request.getContextPath();
%>

<!DOCTYPE html>
<html>
	<head>
		<title>Taxi cars list</title>
			
		<!-- CSS -->
		<link href="<%=context%>/css/bootstrap.min.css" rel="stylesheet">		
		<link href="<%=context%>/css/stylish-portfolio.css" rel="stylesheet">	
		<link href="<%=context%>/css/templatemo_style.css" rel="stylesheet">
		<link href="<%=context%>/css/driver-list.css" rel="stylesheet">
		<link href="<%=context%>/css/font-awesome.min.css" rel="stylesheet">	
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
			
		<div id="column_w800">
			<div class="margin_bottom_20"></div>
			<!-- Title -->
			<div class="header_02">Taxis and drivers list</div>
				<p>See all the info that can help you to choose your favorite</p><br>
			<div class="cleaner"></div>

			<form action="/TaxiToGo/TaxiToGoController/classification-admin-list" method="POST">
				<label class="select">
				<!-- Taxi cars classification -->
					<select id="classification" name="classification" class="form-control input-sm" onchange="this.form.submit()">
						<option value="0" selected disabled>Classification</option>
						<option value="CUV">CUV</option>
						<option value="SUV">SUV</option>
						<option value="MPV">MPV</option>
						<option value="Sedan">Sedan</option>
						<option value="Minibus">Minibus</option>
						<option value="Microvan">Microvan</option>
						<option value="Limousine">Limousine</option>
						<option value="Hatchback">Hatchback</option>
						<option value="Station wagon">Station wagon</option>
					</select>
				</label>
			</form>
		
		<%	
			@SuppressWarnings("unchecked")
		
			// Getting a list of all taxi cars and drivers
			Collection<TaxiCar> taxiCars = (Collection<TaxiCar>)request.getAttribute("taxicarsList");
			
			// Getting the classification car type
			String classification = (String)request.getAttribute("classification");	
		%>
		
			<!-- Taxi cars list -->
			<ul class="list_table">
				<!-- Taxi cars custom tag -->
				<%@ taglib tagdir="/WEB-INF/tags" prefix="customtag" %>
					<customtag:aclassification
						taxiCarsList="<%=taxiCars%>" 
						classificationType="<%=classification%>">
				</customtag:aclassification>
			</ul>
						
		<br><br>
		</div>

		<!-- Footer -->
		<div class="cleaner"></div>
		<div id="templatemo_footer_wrapper">
		<div id="templatemo_footer"></div></div>
				
		<!-- Javascript -->
		<script type="text/javascript" src="<%=context%>/js/jquery-1.11.2.min.js"></script>
		<script type="text/javascript" src="<%=context%>/js/prefixfree.min.js"></script>
	</body>
</html>