package il.ac.hit.taxitogo.controller.listener;

import java.util.LinkedList;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * <p>
 * Class that represent a Taxi car HTTP Session Listener.
 * 
 * @version 22.01.2015
 * @author Victor Zhinzherov
 * @author Lior Kizer
 * @author Ariel Yusupov
 */

/**
 * Application Lifecycle Listener implementation class SessionListiner
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener 
{
	/**
	 * Default constructor.
	 */
	public SessionListener() 
	{
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent arg0) 
	{
		HttpSession session = arg0.getSession();
		ServletContext application = session.getServletContext();
		
		// Checks if the session event is already exist or if it's value is null
		if (application.getAttribute("sessions") == null) 
		{
			// Creating a new http session events list
			application.setAttribute("sessions", new LinkedList<HttpSession>());
		}
		
		@SuppressWarnings("unchecked")
		LinkedList<HttpSession> list = (LinkedList<HttpSession>) application.getAttribute("sessions");
		list.add(session);
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent arg0) 
	{
		HttpSession session = arg0.getSession();
		ServletContext application = session.getServletContext();
		
		@SuppressWarnings("unchecked")
		// Getting the list of all http sessions events
		LinkedList<HttpSession> list = (LinkedList<HttpSession>) application.getAttribute("sessions");
		
		// Remove an existing http session event
		list.remove(session);
	}	
}