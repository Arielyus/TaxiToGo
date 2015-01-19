<%@ tag language="java" %>
<%@ tag pageEncoding="UTF-8" %> 
<%@ tag import="java.util.*" %>
<%@ tag import="java.time.LocalTime" %>
<%@ tag import="java.time.temporal.ChronoUnit" %>
<%@ tag import="il.ac.hit.taxitogo.model.taxicar.*" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="customtag" %>
<%@ attribute name="favoritesTaxiCarsList" required="true" type="java.util.Collection" %>

<%  
	@SuppressWarnings("unchecked")
	Collection<TaxiCar> taxiCars = (Collection<TaxiCar>)favoritesTaxiCarsList;
	String context = request.getContextPath();
	String timeWorkingHours = null;
	LocalTime endShift = null;
	LocalTime startShift = null;

	// If the taxi cars favorites list ain't empty
	if (taxiCars != null)
	{
		Iterator<TaxiCar> iterator = taxiCars.iterator();
					
		// Searching the taxi car list
		while (iterator.hasNext()) 
		{
			TaxiCar taxiCar = (TaxiCar) iterator.next();
			timeWorkingHours = taxiCar.getWorkingHours();
			endShift = LocalTime.parse(timeWorkingHours);
			startShift = endShift.plus((long) -8.0 , ChronoUnit.HOURS);
			
			// Check if the taxi car driver is still working
			if ((startShift.compareTo(LocalTime.now()) < 0) && (endShift.compareTo(LocalTime.now()) > 0)) 
			{
%>
				<li class="price_block">
					<div class="price">			
						<!-- Drivers photos -->
						<div class="price_figure">
							<img src="images/driverimages/<%=taxiCar.getDriverName()+".jpg"%>" 
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
							       <img src="images/star-2.png" height=16 width=16>
							<% 
								} 
							%>
						</li>
					</ul>
										
					<div class="footer">
						<button type="submit" id="<%=taxiCar.getTaxiPlateNumber()%>" 
							class="btn btn-danger btn-s">Remove</button>
					</div>
				</li>
			<%  
			}
			else // If the taxi car driver finished his working shift
			{
				while(iterator.hasNext()) 
				{
					if (iterator.next().getTaxiPlateNumber() == taxiCar.getTaxiPlateNumber()) 
					{	
						// Deleting from the favorites list
						iterator.remove();	
					}	
				}					
			}
		}
	} 		%>