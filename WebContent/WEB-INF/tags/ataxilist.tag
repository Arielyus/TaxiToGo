<%@ tag language="java" %>
<%@ tag pageEncoding="UTF-8" %> 
<%@ tag import="java.util.*" %>
<%@ tag import="il.ac.hit.taxitogo.model.taxicar.*" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="customtag" %>
<%@ attribute name="taxiCarsList" required="true" type="java.util.Collection" %>

<% 
	@SuppressWarnings("unchecked")
	Collection<TaxiCar> taxiCars = (Collection<TaxiCar>) taxiCarsList;
	Iterator<TaxiCar> iterator = taxiCars.iterator();
	String context = request.getContextPath();
	
	// Searching the taxi car list
	while (iterator.hasNext()) 
	{
		TaxiCar taxiCar = (TaxiCar) iterator.next();
%>
		<li class="price_block">
			<div class="price">					
				<!-- Drivers photos -->
				<div class="price_figure">
					<img src="<%=context%>/images/driverimages/<%=taxiCar.getDriverName()+".jpg"%>" 
						alt="" border=3 height=120 width=120>
				</div>
			</div>
									
			<!-- Drivers info list -->
			<ul class="features">
				<li><%=taxiCar.getTaxiPlateNumber()%></li>
				<li><%=taxiCar.getTaxiClassification()%></li>
				<li><%=taxiCar.getDriverName()%></li>
				<li><%=taxiCar.getTaxiCompanyName()%></li>
				<li><%=taxiCar.getWorkingHours()%></li>
				<li>
					<!-- Driver rating stars -->
					<% 
						// Displaying the driver rating stars
						for(int i=0; i < taxiCar.getTaxiDriverRating(); ++i) 
					   	{ 
					%>
						  <img src="<%=context%>/images/star-2.png" height=16 width=16>
					<% 
						} 
					%>
				</li>
			</ul>
		</li>
<% 
	} 
%>