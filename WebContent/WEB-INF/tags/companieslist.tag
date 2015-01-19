<%@ tag language="java" %>
<%@ tag pageEncoding="UTF-8" %> 
<%@ tag import="java.util.*" %>
<%@ tag import="il.ac.hit.taxitogo.model.taxicompany.*" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="customtag" %>
<%@ attribute name="companiesList" required="true" type="java.util.Collection" %>

<%
	@SuppressWarnings("unchecked")
	Collection<TaxiCompany> taxiCompanies = (Collection<TaxiCompany>) companiesList;
	Iterator<TaxiCompany> iterator = taxiCompanies.iterator();
					
	// Searching the taxi car companies list
	while (iterator.hasNext()) 
	{
		TaxiCompany taxiCompany = (TaxiCompany) iterator.next();
%>
		<tbody>								
			<!-- Companies info list -->
			<tr>
				<td><%=taxiCompany.getCompanyName()%></td>
				<td><%=taxiCompany.getCompanyAddress()%></td>
			</tr>
		<tbody>
<% 
  	} 
%>