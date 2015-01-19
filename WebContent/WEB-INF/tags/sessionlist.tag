<%@ tag language="java" %>
<%@ tag pageEncoding="UTF-8" %> 
<%@ tag import="java.util.*" %>
<%@ tag import="il.ac.hit.taxitogo.model.taxicompany.*" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="customtag" %>
<%@ attribute name="sessionsList" required="true" type="java.util.LinkedList" %>

<%  
	@SuppressWarnings("unchecked")
	LinkedList<HttpSession> list = (LinkedList<HttpSession>) sessionsList;
	String context = request.getContextPath();
	
	// Check the http session list value doesn't null
	if (list != null) 
	{
		// Check the http session list is empty
		if (list.isEmpty()) 
		{
			out.println("There are no HttpSession running at the moment");
		}
		else // If the http session list doesn't empty
		{
%>	
			<!-- Sessions list -->
			<div class="margin_bottom_20"></div>
				<div id="column_w810">
					<table class="table table-striped table-bordered">
						<thead>
							<tr class="info">
								<th>Session ID</th>
								<th>Session Key</th>
								<th>Session Value</th>
							</tr>
						</thead>
<%
						HttpSession currentSession = null;
						String sessionID = null;
						Enumeration<String> enumeration = null;
						Iterator<HttpSession> listIterator = list.iterator();
						
						while (listIterator.hasNext()) 
						{
							currentSession = listIterator.next();
							
							// Getting the current session id parameter
							sessionID = currentSession.getId();
							
							enumeration = currentSession.getAttributeNames();
	
							while (enumeration.hasMoreElements()) 
							{
								// Getting the current session key and value parameters
								String sessionKey = enumeration.nextElement();
								String sessionValue = (currentSession.getAttribute(sessionKey)).toString();
%>
								<tbody>
									<!-- Sessions info -->
									<tr>
										<td><%=sessionID%></td>
										<td><%=sessionKey%></td>
										<td><%=sessionValue%></td>
									</tr>
								<tbody>
<% 
						  	} 
						 }
		}
	}
%>
					</table>
					<br> <br>
				</div>