<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %> 
	
<!DOCTYPE html>
<html>
<%
	// Getting the "status" attribute value
	String status = (String)session.getAttribute("status");
	
	// If the user is connected as an ADMIN
	if (status != null) 
	{
%>
		<head>
			<title>Taxi To Go</title>
					
			<!-- CSS -->
			<link href="css/bootstrap.min.css" rel="stylesheet">
			<link href="css/stylish-portfolio.css" rel="stylesheet">
			<link href="css/font-awesome.min.css" rel="stylesheet">
		</head>
	
		<body>
			<!-- Actions -->
			<section id="services" class="services bg-primary"
				style="background-color: rgb(24, 116, 205)">
				<div class="container">
					<div class="row text-center">
						<!-- Home button -->
						<div id="homebutton">
							<a href="index.html">
								<button type="submit" class="btn btn-primary"><i class="fa fa-home">
								</i> Home</button>
							</a>
						</div>
								
						<!-- Logout button -->
						<div>
							<form action="/TaxiToGo/TaxiToGoController/logout" method="POST">
								<button type="submit" class="btn btn-primary"><i class="fa fa-sign-out">
								</i> Logout</button>
							</form>
						</div>
								
						<div class="col-lg-10 col-lg-offset-1">
							<h2>Admin Actions</h2>
							<hr class="mid">
							<div class="row">
										
								<!-- Action 1: Show taxis and drivers list -->
								<div class="col-md-4 col-sm-6">
									<div class="service-item">
												
										<!-- Action icon -->
										<span class="fa-stack fa-4x"> 
											<i class="fa fa-circle fa-stack-2x"></i> 
											<i class="fa fa-table fa-stack-1x text-primary"></i>
										</span>
												
										<!-- Action title -->
										<h4>
											<strong>Show taxis and drivers list</strong>
										</h4>
												
										<!-- Action description -->
										<p>Show Taxis and Drivers info from TaxiToGo database</p>
												
										<!-- Action form -->
										<form action="/TaxiToGo/TaxiToGoController/taxicar-admin-list" method="POST">
											<button type="submit" class="btn btn-default btn-lg">
												<span aria-hidden="true"></span>Click Here
											</button>
										</form>
									</div>
								</div>
				
								<!-- Action 2: Add new taxis and drivers -->
								<div class="col-md-4 col-sm-6">
									<div class="service-item">
											
										<!-- Action icon -->
										<span class="fa-stack fa-4x"> 
											<i class="fa fa-circle fa-stack-2x"></i> 
											<i class="fa fa-taxi fa-stack-1x text-primary"></i>
										</span>
												
										<!-- Action title -->
										<h4>
											<strong>Add new taxis and drivers</strong>
										</h4>
												
										<!-- Action description -->
										<p>Add new Taxis and Drivers into TaxiToGo database</p>
												
										<!-- Action page link -->
										<a href="admin_taxicar_add.html">
											<button type="submit" class="btn btn-default btn-lg">
												<span aria-hidden="true"></span>Click Here
											</button>
										</a>
									</div>
								</div>
				
								<!-- Action 3: Update taxis and drivers -->
								<div class="col-md-4 col-sm-6">
									<div class="service-item">
											
										<!-- Action icon -->
										<span class="fa-stack fa-4x"> 
											<i class="fa fa-circle fa-stack-2x"></i> 
											<i class="fa fa-pencil-square-o fa-stack-1x text-primary"></i>
										</span>
												
										<!-- Action title -->
										<h4>
											<strong>Update taxis and drivers</strong>
										</h4>
												
										<!-- Action description -->
										<p>Update Taxis and Drivers info in TaxiToGo database</p>
												
										<!-- Actions form -->	
										<form action="/TaxiToGo/TaxiToGoController/taxicar-update-form" method="POST">
											<button type="submit" class="btn btn-default btn-lg">
												<span aria-hidden="true"></span>Click Here
											</button>
										</form>
									</div>
								</div>
										
								<!-- Action 4: Show taxis and drivers companies list -->
								<div class="col-md-4 col-sm-6">
									<div class="service-item">
											
										<!-- Action icon -->
										<span class="fa-stack fa-4x"> 
											<i class="fa fa-circle fa-stack-2x"></i> 
											<i class="fa fa-list-alt fa-stack-1x text-primary"></i>
										</span>
												
										<!-- Action title -->
										<h4>
											<strong>Show companies list</strong>
										</h4>
												
										<!-- Action description -->
										<p>Show companies info from TaxiToGo database</p>
												
										<!-- Action form -->
										<form action="/TaxiToGo/TaxiToGoController/companies-list"
											method="POST">
											<button type="submit" class="btn btn-default btn-lg">
												<span aria-hidden="true"></span>Click Here
											</button>
										</form>
									</div>
								</div>
										
								<!-- Action 5: Add new companies -->
								<div class="col-md-4 col-sm-6">
									<div class="service-item">
											
										<!-- Action icon -->
										<span class="fa-stack fa-4x"> 
											<i class="fa fa-circle fa-stack-2x"></i> 
											<i class="fa fa-briefcase fa-stack-1x text-primary"></i>
										</span>
												
										<!-- Action title -->
										<h4>
											<strong>Add new companies</strong>
										</h4>
										
										<!-- Action description -->
										<p>Add new company into TaxiToGo database</p>
												
										<!-- Action page link -->
										<a href="admin_company_add.html">
											<button type="submit" class="btn btn-default btn-lg">
												<span aria-hidden="true"></span>Click Here
											</button>
										</a>
									</div>
								</div>
										
								<!-- Action 6: Update companies -->
								<div class="col-md-4 col-sm-6">
									<div class="service-item">
											
										<!-- Action icon -->
										<span class="fa-stack fa-4x"> 
											<i class="fa fa-circle fa-stack-2x"></i> 
											<i class="fa fa-pencil-square-o fa-stack-1x text-primary"></i>
										</span>
										
										<!-- Action title -->
										<h4>
											<strong>Update companies</strong>
										</h4>
												
										<!-- Action description -->
										<p>Update companies info in TaxiToGo database</p>
												
										<!-- Action form -->	
										<form action="/TaxiToGo/TaxiToGoController/company-update-form" method="POST">
											<button type="submit" class="btn btn-default btn-lg">
												<span aria-hidden="true"></span>Click Here
											</button>
										</form>
									</div>
								</div>
										
								<!-- Action 7: Session Listener -->
								<div class="col-md-4 col-sm-6">
									<div class="service-item">
												
										<!-- Action icon -->
										<span class="fa-stack fa-4x"> 
											<i class="fa fa-circle fa-stack-2x"></i> 
											<i class="fa fa-columns fa-stack-1x text-primary"></i>
										</span>
												
										<!-- Action title -->
										<h4>
											<strong>Http Sessions list</strong>
										</h4>
												
										<!-- Action description -->
										<p>Check how many http sessions are exist in Taxi To Go website</p>
												
										<!-- Action page link -->
										<a href="admin_sessions_list.jsp">
											<button type="submit" class="btn btn-default btn-lg">
												<span aria-hidden="true"></span>Click Here
											</button>
										</a>
									</div>
								</div>
										
								<!-- Action 8: Delete taxis and drivers -->
								<div class="col-md-4 col-sm-6">
									<div class="service-item">
											
										<!-- Action icon -->
										<span class="fa-stack fa-4x"> 
											<i class="fa fa-circle fa-stack-2x"></i> 
											<i class="fa fa-trash-o fa-stack-1x text-primary"></i>
										</span>
												
										<!-- Action title -->
										<h4>
											<strong>Delete taxis and drivers</strong>
										</h4>
												
										<!-- Action description -->
										<p>Delete Taxis and Drivers from TaxiToGo database</p>
												
										<!-- Action form -->	
										<form action="/TaxiToGo/TaxiToGoController/taxicar-delete-form" method="POST">
											<button type="submit" class="btn btn-default btn-lg">
												<span aria-hidden="true"></span>Click Here
											</button>
										</form>
									</div>
								</div>
										
								<!-- Action 9: Delete companies -->
								<div class="col-md-4 col-sm-6">
									<div class="service-item">
												
										<!-- Action icon -->
										<span class="fa-stack fa-4x"> 
											<i class="fa fa-circle fa-stack-2x"></i> 
											<i class="fa fa-trash-o fa-stack-1x text-primary"></i>
										</span>
												
										<!-- Action title -->
										<h4>
											<strong>Delete companies</strong>
										</h4>
												
										<!-- Action description -->
										<p>Delete Taxis companies from TaxiToGo database</p>
												
										<!-- Action form -->	
										<form action="/TaxiToGo/TaxiToGoController/company-delete-form" method="POST">
											<button type="submit" class="btn btn-default btn-lg">
												<span aria-hidden="true"></span>Click Here
											</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
<% 
			}
			else // If the user doesn't connected as an ADMIN 
			{ 
%>
				<head>	
					<title>Error page</title>
					
					<!-- CSS -->
					<link href="css/font-awesome.css" rel="stylesheet">
					<link href="css/bootstrap.min.css" rel="stylesheet">		
					<link href="css/stylish-portfolio.css" rel="stylesheet">	
					<link href="css/templatemo_style.css" rel="stylesheet">
					<link href="css/driver-list.css" rel="stylesheet">
				</head>
					
				<body>
					<div id="templatemo_header_wrapper">
						<!-- Main Logo -->
						<div id="logo">
							<!-- Main Logo Button -->
							<a href="index.html">
			 					<img src="images/logo-1.png" height="100" width="310">
							</a>
						</div>
							
						<!-- Home Button -->
						<div id="homebutton1">
							<a href="index.html">
								<button type="submit" class="btn btn-link btn-lg"><i class="fa fa-home"></i> Home</button>
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
						<div class="header_02">Error: Access Denied</div>
							<p class="em_text">In order to access administrator page you have to log in</p>
						<div class="cleaner"></div>
					</div>
							
					<!-- Footer -->
					<div class="margin_bottom_30"></div>
					<div class="margin_bottom_60"></div>
					<div id="templatemo_footer_wrapper">
					<div id="templatemo_footer"></div></div>
<% 
			} 
%>
	</body>
</html>