<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %> 
<%@ page import="java.util.*" %>
<%@ page import="java.util.*, il.ac.hit.taxitogo.model.taxicompany.*" %>

<% 
	// Getting the request context path
	String context = request.getContextPath();
%>
       
<!DOCTYPE html>
<html>
	<head>
		<title>Update form</title>
			
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
			<div class="header_02">Update taxi companies</div>
				<p>Update taxis companies info in TaxiToGo database</p>
			<div class="cleaner"></div>
		</div>
		
	<% 				
		@SuppressWarnings("unchecked")
		// Getting a list of all taxi cars companies
		Collection<TaxiCompany> companiesNames = (Collection<TaxiCompany>) request.getAttribute("companyList");
	%>
		
		<div class="body body-s">
			<!-- Update Form -->
			<form id="sky-form" class="sky-form">
				<header>Update form</header>
					
				<fieldset>
					<!-- Company name -->
					<section>
						<label class="select"><i class="icon-append fa fa-car"></i>
							<select name="companyname" id="companyname">
								<option value="0" selected disabled>Company name</option>
								<!-- Taxi cars custom tag -->		
								<%@ taglib tagdir="/WEB-INF/tags" prefix="customtag" %>
								<customtag:companiesnames companiesNamesList="<%=companiesNames%>"></customtag:companiesnames>
							</select>
						</label>
					</section>	
						
					<!-- Company address -->
					<section>
						<label class="label"></label> <label class="textarea"> 
						<i class="icon-append fa fa-map-marker"></i> <textarea rows="3"
							name="companyaddress" id="companyaddress" placeholder="Company address"></textarea>
						</label>
					</section>
				</fieldset>
					
				<!-- Update button -->
				<footer>
					<button type="submit" class="button" id="updateButton">Update</button>
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
		<script type="text/javascript" src="<%=context%>/js/admin_company_update_ajax.js"></script>
	</body>
</html>