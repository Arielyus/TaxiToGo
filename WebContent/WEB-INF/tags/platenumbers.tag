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
						
	// Searching the taxi car list
	while (iterator.hasNext()) 
	{
		TaxiCar taxiCar = (TaxiCar) iterator.next();		
%>	
		<option value="<%=taxiCar.getTaxiPlateNumber()%>"><%=taxiCar.getTaxiPlateNumber()%></option>
<% 
 	}
%>