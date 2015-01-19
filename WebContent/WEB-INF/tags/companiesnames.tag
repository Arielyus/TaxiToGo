<%@ tag language="java" %>
<%@ tag pageEncoding="UTF-8" %> 
<%@ tag import="java.util.*" %>
<%@ tag import="il.ac.hit.taxitogo.model.taxicompany.*" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="customtag" %>
<%@ attribute name="companiesNamesList" required="true" type="java.util.Collection" %>

<% 				
	@SuppressWarnings("unchecked")
	Collection<TaxiCompany> taxiCarsCompanies = (Collection<TaxiCompany>) companiesNamesList;
	Iterator<TaxiCompany> iterator = taxiCarsCompanies.iterator();
						
	// Searching the taxi car companies list
	while (iterator.hasNext()) 
	{
		TaxiCompany taxiCarCompany = (TaxiCompany) iterator.next();		
%>	
		<option value="<%=taxiCarCompany.getCompanyName()%>"><%=taxiCarCompany.getCompanyName()%></option>
<% 
	}
%>
