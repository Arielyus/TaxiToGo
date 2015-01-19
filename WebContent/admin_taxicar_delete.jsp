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
		<title>Delete form</title>
			
		<!-- CSS -->
		<link href="<%=context%>/css/font-awesome.css" rel="stylesheet">
		<link href="<%=context%>/css/bootstrap.min.css" rel="stylesheet">		
		<link href="<%=context%>/css/stylish-portfolio.css" rel="stylesheet">	
		<link href="<%=context%>/css/templatemo_style.css" rel="stylesheet">
		<link href="<%=context%>/css/driver-list.css" rel="stylesheet">
		<link href="<%=context%>/css/sky-forms.css" rel="stylesheet">
		<link href="<%=context%>/css/sky-forms-green.css" rel="stylesheet">
		<link href="<%=context%>/css/form-style.css" rel="stylesheet">
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
			<div class="header_02">Delete taxi cars and drivers</div>
				<p>Delete existing taxis and drivers from TaxiToGo database</p>
			<div class="cleaner"></div>
		</div>
		
		<% 
			@SuppressWarnings("unchecked")
			// Getting a list of all taxi cars and drivers
			Collection<TaxiCar> taxiCarsToDisplay = (Collection<TaxiCar>)request.getAttribute("taxicarsList"); 
		%>
		
		<div class="body body-s">
			<!-- Update Form -->
			<form id="sky-form" class="sky-form">
				<header>Delete form</header>
					
				<fieldset>
					<!-- Taxi car plate number -->
					<section>
						<label class="select"><i class="icon-append fa fa-car"></i>
							<select name="taxiplate" id="taxiplate">
								<option value="0" selected disabled>Plate number</option>
								<!-- Taxi cars custom tag -->
								<%@ taglib tagdir="/WEB-INF/tags" prefix="customtag" %>
								<customtag:platenumbers taxiCarsList="<%=taxiCarsToDisplay%>"></customtag:platenumbers>
							</select>
						</label>
					</section>	
						
					<!-- Taxi car classification -->
					<section>
						<label class="select"><i class="icon-append fa fa-car"></i>
							<select name="classification" id="classification" disabled>
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
					</section>	
						
					<!-- Driver name -->
					<section>
						<label class="input"> <i class="icon-append fa fa-user"></i>
							<input type="text" disabled name="drivername" id="drivername" placeholder="Driver full name">
						</label>
					</section>
		
					<!-- Company name -->
					<section>
						<label class="input"> <i class="icon-append fa fa-briefcase"></i>
							<input type="text" disabled name="companyname" id="companyname" placeholder="Company name">
						</label>
					</section>
						
					<!-- Driver rating -->
					<section>
						<div class="rating">
							<input type="radio" disabled name="quality" value="5" id="quality-5">
							<label for="quality-5"><i class="fa fa-star"></i></label> 
								
							<input type="radio" disabled name="quality" value="4" id="quality-4"> 
							<label for="quality-4"><i class="fa fa-star"></i></label> 
								
							<input type="radio" disabled name="quality" value="3" id="quality-3"> 
							<label for="quality-3"><i class="fa fa-star"></i></label> 
								
							<input type="radio" disabled name="quality" value="2" id="quality-2"> 
							<label for="quality-2"><i class="fa fa-star"></i></label> 
								
							<input type="radio" disabled name="quality" value="1" id="quality-1"> 
							<label for="quality-1"><i class="fa fa-star"></i></label>
							Driver rating
						</div>
					</section>
						
					<!-- Taxi car latitude coordinate -->
					<section>
						<label class="input"> <i class="icon-append fa fa fa-arrows-h"></i>
							<input type="text" disabled name="latitude" id="latitude" placeholder="Latitude">
						</label>
					</section>
						
					<!-- Taxi car longitude coordinate -->
					<section>
						<label class="input"> <i class="icon-append fa fa fa-arrows-v"></i>
							<input type="text" disabled name="longitude" id="longitude" placeholder="Longitude">
						</label>
					</section>
						
					<!-- Driver end shift -->
					<section>
						<label class="input"> <i class="icon-append fa fa-clock-o"></i>
							<input type="text" disabled name="workinghours" id="workinghours" placeholder="End shift">
						</label>
					</section>
				</fieldset>
					
				<!-- Update button -->
				<footer>
					<button type="submit" class="button" id="updateButton">Delete</button>
				</footer>
			</form>
		</div>
			
		<!-- AJAX result message -->
		<div id="ajax_message"><p id="result"></p></div>
		<div class="margin_bottom_20"></div>
			
		<!-- Footer -->
		<div id="templatemo_footer_wrapper">
		<div id="templatemo_footer"></div></div>
		
		<!-- Javascript -->
		<script type="text/javascript" src="<%=context%>/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=context%>/js/jquery.form.min.js"></script>
		<script type="text/javascript" src="<%=context%>/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="<%=context%>/js/jquery.modal.js"></script>
		<script type="text/javascript" src="<%=context%>/js/admin_taxicar_delete_ajax.js"></script>			
	</body>
</html>