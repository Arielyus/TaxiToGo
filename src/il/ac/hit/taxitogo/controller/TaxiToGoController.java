package il.ac.hit.taxitogo.controller;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import il.ac.hit.taxitogo.model.exception.TaxiToGoException;
import il.ac.hit.taxitogo.model.taxicar.*;
import il.ac.hit.taxitogo.model.taxicompany.*;
import il.ac.hit.taxitogo.model.user.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.google.gson.Gson;

/**
 * @version 22.01.2015
 * @author Victor Zhinzherov
 * @author Lior Kizer
 * @author Ariel Yusupov
 */

@WebServlet(urlPatterns = {"/TaxiToGoController/*"}, loadOnStartup = 1)
public class TaxiToGoController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private Logger logger;
	
	/**
	 * <p>
	 * Called by the servlet container to indicate to a servlet that the servlet
	 * is being placed into service.<br>
	 * 
	 * The servlet container calls the init method after instantiating the
	 * servlet.<br>
	 * 
	 * The init method must complete successfully before the servlet can 
	 * receive any requests.<br>
	 *
	 * The web container also initializes the property file.
	 * </p>
	 */
	@Override
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
		
		// Creating the log file
		logger = Logger.getLogger("actionsLogs.log");
		
		// Setting all property configurations from the properties file
		PropertyConfigurator.configure("/Users/victorzhinzherov/Documents/Eclipse Projects1/TaxiToGo/"
				+ "src/logger.properties");
		
		// Each time the server will restart, a log message will be written into the log file
		logger.info("[Server]: server was successfully initialized" + "\n----------------------------"
				+ "----------------------------------------------------------");
	}
	
	/**
	 * <p>
	 * Called by the servlet container to indicate to a servlet that the servlet
	 * is being taken out of service.<br>
	 *
	 * This method is only called once all threads within the servlet's service 
	 * method have exited or after a timeout period has passed.<br>
	 * 
	 * After the servlet container calls this method, it will not call the 
	 * service method again on this servlet.<br>
	 * </p>
	 */
	@Override
	public void destroy()
	{
		// Each time the server will stop, a log message will be written into the log file
		logger.info("[Server]: server was taken out of service\n");
	}
	
	/**
	 * <p>
	 * Called by the server (via the service method) to allow a servlet to
	 * handle a GET request. Each GET request is described as URL ending. 
	 * </p>
	 * <p>
	 * <i>/closest-taxicar-list</i> - This URL ending deals the user request to 
	 * displays five of the most closest taxi cars, based on user's current location.
	 * </p>
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{	
		String path = request.getPathInfo();
		RequestDispatcher dispatcher = null; 

		// Displaying to a user a list of five of the most closest taxi cars based on his current location
		if (path.endsWith("closest-taxicar-list")) 
		{
			// Getting the parameters of the latitude and longitude from the request
			double latitude = Double.parseDouble(request.getParameter("latitude"));
			double longitude = Double.parseDouble(request.getParameter("longitude"));
			
			try 
			{
				// Setting the attribute with the list of 5 of the most closest taxi cars as a value
				request.setAttribute("closest_taxicars_list", MySQLTaxiCarDAO.getInstance().
						getFiveClosestTaxiCars(latitude, longitude));
				
				// Info log message
				logger.info("[User]: a list of five closest taxi cars was displayed based on user current "
						+ "location coordinates [" + latitude + "], [" + longitude + "]");
				
				dispatcher = request.getRequestDispatcher("/user_closest_list.jsp");
				dispatcher.forward(request, response);
			}
			catch (TaxiToGoException exceptionEvent) 
			{
				// Setting an exception attribute with the exception event as a value
				request.setAttribute("exception", exceptionEvent);
				
				// Error log message
				logger.error("[User]: taxi cars and drivers list was NOT displayed");
				
				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}
		}
	}
	
	/**
	 * <p>
	 * Called by the server (via the service method) to allow a servlet to
	 * handle a POST request. Each POST request is described as URL's ending. 
	 * </p>
	 * 
	 * <p><i>/taxicar-list</i> - This URL ending deals the user request to display 
	 * a list of all the taxi cars and drivers.<br>
	 * 
	 * <i>/taxicar-admin-list</i> - This URL ending deals the admin request to display 
	 * a list of all taxi cars and drivers.<br>
	 * 
	 * <i>/classification-list</i> - This URL ending deals the user request to display 
	 * a list of all taxi cars and drivers by car classification type.<br>
	 * 
	 * <i>/classification-admin-list</i> - This URL ending deals the admin request to display 
	 * a list of all taxi cars and drivers by car classification type.<br>
	 * 
	 * <i>/companies-list</i> - This URL ending deals the admin request to display 
	 * a list of all taxi cars companies.<br>
	 * 
	 * <i>/taxicar-add-favorite</i> - This URL ending deals the user request to add a 
	 * taxi car and driver to user's favorites list.<br>
	 * 
	 * <i>/taxicar-delete-favorite</i> - This URL ending deals the user request to delete
	 * a taxi car and driver from user's favorites list.<br>
	 *  
	 * <i>/taxicar-information</i> - This URL ending deals the admin request to retrieve 
	 * an information of a specific taxi car from the database.<br>
	 * 
	 * <i>/company-information</i> - This URL ending deals the admin request to retrieve
	 * an information of a specific taxi car company from the database.<br>
	 *  
	 * <i>/taxicar-add-action</i> - This URL ending deals the admin request to add a new
	 * taxi car and driver into the database.<br>
	 * 
	 * <i>/taxicar-update-form</i> - This URL ending deals the admin request to be
	 * redirected into the Taxi car and driver update form.<br>
	 * 
	 * <i>/company-update-form</i> - This URL ending deals the admin request to be
	 * redirected into the Taxi car company update form.<br>
	 * 
	 * <i>/taxicar-delete-form</i> - This URL ending deals the admin request to be
	 * redirected into the Taxi car and driver delete form.<br>
	 * 
	 * <i>/company-delete-form</i> - This URL ending deals the admin request to be
	 * redirected into the Taxi car company delete form.<br>
	 * 
	 * <i>/taxicar-update-action</i> - This URL ending deals the admin request to update
	 * an existing taxi car and driver inside the database.<br>
	 * 
	 * <i>/company-add-action</i> - This URL ending deals the admin request to add a new
	 * taxi car company into the database.<br>
	 * 
	 * <i>/company-update-action</i> - This URL ending deals the admin request to update an
	 * existing taxi car company inside the database.<br>
	 * 
	 * <i>/taxicar-delete-action</i> - This URL ending deals the admin request to delete an
	 * existing taxi car and driver from the database.<br>
	 * 
	 * <i>/company-delete-action</i> - This URL ending deals the admin request to delete an
	 * existing taxi car company from the database.<br>
	 * 
	 * <i>/login-action</i> - This URL ending deals the user request to be redirected into
	 * the log in form.<br>
	 * 
	 * <i>/admin-login</i> - This URL ending deals the user request to log in into the
	 * admin section.<br>
	 * 
	 * <i>/logout</i> - This URL ending deals the admin request to logged out from the
	 * admin section.<br></p>
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String path = request.getPathInfo();
		RequestDispatcher dispatcher = null; 
		
		// Displaying to user a list of all the taxi cars and drivers
		if (path.endsWith("taxicar-list")) 
		{
			try 
			{
				// Setting the attribute with the list of all taxi cars and drivers as a value
				request.setAttribute("taxicarsList", MySQLTaxiCarDAO.getInstance().getTaxiCars());
				
				// Info log message
				logger.info("[User]: a list of all taxi cars and drivers was displayed");
				
				dispatcher = request.getRequestDispatcher("/user_taxicar_list.jsp");
				dispatcher.forward(request, response);
			}
			catch (TaxiToGoException exceptionEvent) 
			{
				// Setting an exception attribute with the exception event as a value
				request.setAttribute("exception", exceptionEvent);
				
				// Error log message
				logger.error("[User]: a list of all taxi cars and drivers was NOT displayed");
				
				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}
		}
		// Displaying to ADMIN a list of all taxi cars and drivers
		else if (path.endsWith("taxicar-admin-list")) 
		{
			try 
			{	
				// Setting the attribute with the list of all taxi cars and drivers as a value
				request.setAttribute("taxicarsList", MySQLTaxiCarDAO.getInstance().getTaxiCars());
				
				// Info log message
				logger.info("[Admin]: a list of all taxi cars and drivers was displayed");
				
				dispatcher = request.getRequestDispatcher("/admin_taxicar_list.jsp");
				dispatcher.forward(request, response);
			}
			catch (TaxiToGoException exceptionEvent) 
			{
				// Setting an exception attribute with the exception event as a value
				request.setAttribute("exception", exceptionEvent);
				
				// Error log message
				logger.error("[Admin]: a list of all taxi cars and drivers was NOT displayed");
				
				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}
		}
		// Displaying to user a list of all taxi cars and drivers by car classification type 
		else if (path.endsWith("classification-list")) 
		{
			response.setContentType("text/plain");
			
			// Getting the parameter of the classification car type from the request
			String classification = request.getParameter("classification");
			
			try 
			{
				// Setting the attribute with the list of all taxi cars and drivers as a value
				request.setAttribute("taxicarsList", MySQLTaxiCarDAO.getInstance().getTaxiCars());
				
				// Setting the attribute with the classification car type as a value
				request.setAttribute("classification", classification);
				
				// Info log message
				logger.info("[User]: a list of all \"" + classification + "\" cars was displayed");
				
				dispatcher = request.getRequestDispatcher("/user_classification_list.jsp");
				dispatcher.forward(request, response);
				
			} 
			catch (TaxiToGoException exceptionEvent) 
			{
				// Setting an exception attribute with the exception event as a value
				request.setAttribute("exception", exceptionEvent);
				
				// Error log message
				logger.error("[User]: a list of all \"" + classification + "\" cars was NOT displayed");
				
				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}
		}
		// Displaying to ADMIN a list of all taxi cars and drivers by car classification type 
		else if (path.endsWith("classification-admin-list")) 
		{
			response.setContentType("text/plain");
			
			// Getting the parameter of the classification car type from the request
			String classification = request.getParameter("classification");
			
			try 
			{
				// Setting the attribute with the list of all taxi cars and drivers as a value
				request.setAttribute("taxicarsList", MySQLTaxiCarDAO.getInstance().getTaxiCars());
				
				// Setting the attribute with the classification car type as a value
				request.setAttribute("classification", classification);
				
				// Info log message
				logger.info("[Admin]: a list of all \"" + classification + "\" cars was displayed");
				
				dispatcher = request.getRequestDispatcher("/admin_classification_list.jsp");
				dispatcher.forward(request, response);
			} 
			catch (TaxiToGoException exceptionEvent) 
			{
				// Setting an exception attribute with the exception event as a value
				request.setAttribute("exception", exceptionEvent);
				
				// Error log message
				logger.error("[Admin]: a list of all \"" + classification + "\" cars was NOT displayed");
				
				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}
		}
		// Displaying to ADMIN a list of all taxi cars companies
		else if (path.endsWith("companies-list")) 
		{
			try 
			{	
				// Setting the attribute with the list of all taxi car companies as a value
				request.setAttribute("companiesList", MySQLTaxiCompanyDAO.getInstance().getTaxiCompanys());
				
				// Info log message
				logger.info("[ADMIN]: a list of all taxi cars companies was displayed");
				
				dispatcher = request.getRequestDispatcher("/admin_companies_list.jsp");
				dispatcher.forward(request, response);
			}
			catch (TaxiToGoException exceptionEvent) 
			{
				// Setting an exception attribute with the exception event as a value
				request.setAttribute("exception", exceptionEvent);
				
				// Error log message
				logger.error("[ADMIN]: a list of all taxi cars companies was NOT displayed");
				
				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}
		}
		// Adding taxi car and driver to user's favorites list
		else if (path.endsWith("taxicar-add-favorite")) 
		{
			String timeWorkingHours = null;
			LocalTime endShift = null;
			LocalTime startShift = null;
			HttpSession session = request.getSession();
			
			// Getting the parameter of the taxi car plate from the request
			int taxiId = Integer.parseInt(request.getParameter("plate"));
			
			// Setting sessions maximum of inactive interval
			session.setMaxInactiveInterval(6000);
			
			// Checks if the "favorites" attribute is already exist or if it's empty
			if (session.getAttribute("favorites") == null) 
			{
				// Setting the "favorites" attribute with an empty array list 
				session.setAttribute("favorites", new CopyOnWriteArrayList<TaxiCar>());
			}
				
			@SuppressWarnings("unchecked")
			// Getting the favorites list
			Collection<TaxiCar> favorites = (Collection<TaxiCar>) session.getAttribute("favorites");
			
			try 
			{
				Iterator<TaxiCar> iterator = favorites.iterator();
				TaxiCar taxiCarToCheck = MySQLTaxiCarDAO.getInstance().getTaxiCar(taxiId);
				String notEqual = "Doesn't exist";
				
				// Searching if the taxi car is already exist in the favorites list
				while (iterator.hasNext()) 
				{
					TaxiCar taxiCar = (TaxiCar) iterator.next();
					
					// Checks if the given taxi car is already exist inside the favorites list
					if (taxiCar.getTaxiPlateNumber() == taxiCarToCheck.getTaxiPlateNumber()) 
					{
						notEqual = "Exist";
					}	
				}
				
				if (notEqual.equals("Doesn't exist")) 
				{
					timeWorkingHours = taxiCarToCheck.getWorkingHours();
					endShift = LocalTime.parse(timeWorkingHours);
					startShift = endShift.plus((long) -8.0, ChronoUnit.HOURS);
					
					// Checks if the taxi car driver is still working
					if (startShift.compareTo(LocalTime.now()) < 0 && endShift.compareTo(LocalTime.now()) > 0) 
					{
						// Add the taxi car and driver into the favorites list
						favorites.add(MySQLTaxiCarDAO.getInstance().getTaxiCar(taxiId));
						
						// Info log message
						logger.info("[User]: taxi car [" + taxiId + "] was added to user's favorites list");
					}
				}
			} 
			catch (TaxiToGoException exceptionEvent) 
			{
				// Setting an exception attribute with the exception event as a value
				request.setAttribute("exception", exceptionEvent);
				
				// Error log message
				logger.error("[User]: taxi car [" + taxiId + "] was NOT added to user's favorites list");
				
				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}
		}
		// Deleting taxi car and driver from user's favorites list
		else if (path.endsWith("taxicar-delete-favorite")) 
		{
			HttpSession session = request.getSession();
			
			// Getting the parameter of the taxi car plate number from the request
			int taxiId = Integer.parseInt(request.getParameter("plate"));
			
			// Setting sessions maximum inactive interval
			session.setMaxInactiveInterval(6000);
				
			// Checks if the "favorites" attribute is already exist or if it's empty
			if (session.getAttribute("favorites") == null) 
			{
				// Setting the "favorites" attribute with an empty array list 
				session.setAttribute("favorites", new CopyOnWriteArrayList<TaxiCar>());
			}
				
			@SuppressWarnings("unchecked")			
			Collection<TaxiCar> favorites =	(Collection<TaxiCar>) session.getAttribute("favorites");
		
			// Searching for the taxi car to remove from the favorites list
			for (TaxiCar taxicar : favorites) 
			{
				if (taxicar.getTaxiPlateNumber() == taxiId) 
				{			
					// Removing the taxi car from the favorites list
					favorites.remove(taxicar);
					
					// Info log message
					logger.info("[User]: taxi car [" + taxiId + "] was removed from user's favorites list");
				}	
			}
		}
		// Retrieving an information of a specific taxi car from the database
		else if (path.endsWith("taxicar-information")) 
		{
			response.setContentType("application/json");
			
			// Getting the parameter of the taxi car plate number from the request
			int taxiplate = Integer.parseInt(request.getParameter("taxiplate"));	
			
			try 
			{
				// Getting the taxi car and driver by it's plate number
				TaxiCar taxicar = MySQLTaxiCarDAO.getInstance().getTaxiCar(taxiplate);
				
				// Info log message
				logger.info("[Admin]: retrieved [" + taxicar.getTaxiPlateNumber() + "] taxi car information");
				
				response.getWriter().write(new Gson().toJson(taxicar));
			} 
			catch (TaxiToGoException exceptionEvent) 
			{
				// Setting an exception attribute with the exception event as a value
				request.setAttribute("exception", exceptionEvent);
				
				// Error log message
				logger.info("[Admin]: could NOT retrieved a taxi car information");
				
				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}
		}
		// Retrieving an information of a specific taxi car company from the database
		else if (path.endsWith("company-information")) 
		{
			response.setContentType("application/json");
			
			// Getting the parameter of the taxi car company name from the request
			String companyName = request.getParameter("companyName");	
			
			try 
			{
				// Getting the taxi car company by it's name
				TaxiCompany taxicarCompany = MySQLTaxiCompanyDAO.getInstance().getTaxiCompany(companyName);
				
				// Info log message
				logger.info("[Admin]: retrieved [" + taxicarCompany.getCompanyName() + "] company information");
				
				response.getWriter().write(new Gson().toJson(taxicarCompany));
			} 
			catch (TaxiToGoException exceptionEvent) 
			{
				// Setting an exception attribute with the exception event as a value
				request.setAttribute("exception", exceptionEvent);
				
				// Error log message
				logger.info("[Admin]: could NOT retrieved a company information");
				
				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}
		}
		// Adding a new taxi car and driver to database [ADMIN]
		else if (path.endsWith("taxicar-add-action")) 
		{
			response.setContentType("text/plain");
			
			// Getting the parameters of the taxi car and driver from the request
			int taxiPlateNumber = Integer.parseInt(request.getParameter("plate"));
			String driverName = request.getParameter("drivername");
			String taxiCompanyName = request.getParameter("companyname");
			int taxiDriverRating = Integer.parseInt(request.getParameter("rating"));
			String classification = request.getParameter("classification");
			double latitude = Double.parseDouble(request.getParameter("latitude"));
			double longitude = Double.parseDouble(request.getParameter("longitude"));
			String workingHours = request.getParameter("workinghours");
			
			// Taxi car to add
			TaxiCar taxiCarToAdd = new TaxiCar(taxiCompanyName, taxiPlateNumber,
					driverName, taxiDriverRating, classification, latitude,
					longitude, workingHours);
			try 
			{
				// Adding the taxi car into the database
				MySQLTaxiCarDAO.getInstance().addTaxiCar(taxiCarToAdd);
				
				// Info log message
				logger.info("[Admin]: taxi car [" + taxiPlateNumber + "] was added into the database");
				
				response.getWriter().print("added");
			} 
			catch (TaxiToGoException exceptionEvent) 
			{
				// Setting an exception attribute with the exception event as a value
				request.setAttribute("exception", exceptionEvent);
				
				// Error log message
				logger.error("[Admin]: taxi car [" + taxiPlateNumber + "] was NOT added into the database");
				
				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}	
		}
		// Taxi car and driver update form [ADMIN]
		else if (path.endsWith("taxicar-update-form")) 
		{
			try 
			{
				// Setting the attribute with the list of all taxi cars and drivers as a value
				request.setAttribute("taxicarsList", MySQLTaxiCarDAO.getInstance().getTaxiCars());
				
				// Info log message
				logger.info("[ADMIN]: entered into the taxi car update form");

				dispatcher = request.getRequestDispatcher("/admin_taxicar_update.jsp");
				dispatcher.forward(request, response);
			}
			catch (TaxiToGoException exceptionEvent) 
			{
				// Setting an exception attribute with the exception event as a value
				request.setAttribute("exception", exceptionEvent);
				
				// Error log message
				logger.error("[ADMIN]: wasn't able to enter into the taxi car and driver update form");
				
				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}
		}
		// Taxi car company update form [ADMIN]
		else if (path.endsWith("company-update-form")) 
		{
			try 
			{
				// Setting the attribute with the list of all taxi companies as a value
				request.setAttribute("companyList", MySQLTaxiCompanyDAO.getInstance().getTaxiCompanys());
				
				// Info log message
				logger.info("[ADMIN]: entered into the taxi car company update form");
				
				dispatcher = request.getRequestDispatcher("/admin_company_update.jsp");
				dispatcher.forward(request, response);
			}
			catch (TaxiToGoException exceptionEvent) 
			{
				// Setting an exception attribute with the exception event as a value
				request.setAttribute("exception", exceptionEvent);
				
				// Error log message
				logger.error("[ADMIN]: wasn't able to enter into the taxi car company update form");
				
				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}
		}
		// Taxi car and driver delete form [ADMIN]
		else if (path.endsWith("taxicar-delete-form")) 
		{
			try 
			{
				// Setting the attribute with the list of all taxi cars and drivers as a value
				request.setAttribute("taxicarsList", MySQLTaxiCarDAO.getInstance().getTaxiCars());
				
				// Info log message
				logger.info("[ADMIN]: entered into the taxi car and driver delete form");

				dispatcher = request.getRequestDispatcher("/admin_taxicar_delete.jsp");
				dispatcher.forward(request, response);
			}
			catch (TaxiToGoException exceptionEvent) 
			{
				// Setting an exception attribute with the exception event as a value
				request.setAttribute("exception", exceptionEvent);
				
				// Error log message
				logger.error("[ADMIN]: wasn't able to enter into the taxi car and driver delete form");

				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}
		}
		// Taxi car company delete form [ADMIN]
		else if (path.endsWith("company-delete-form")) 
		{
			try 
			{
				// Setting the attribute with the list of all taxi companies as a value
				request.setAttribute("companyList", MySQLTaxiCompanyDAO.getInstance().getTaxiCompanys());
				
				// Info log message
				logger.info("[ADMIN]: entered into the taxi car company delete form");

				dispatcher = request.getRequestDispatcher("/admin_company_delete.jsp");
				dispatcher.forward(request, response);
			}
			catch (TaxiToGoException exceptionEvent) 
			{
				// Setting an exception attribute with the exception event as a value
				request.setAttribute("exception", exceptionEvent);
				
				// Error log message
				logger.error("[ADMIN]: wasn't able to enter into the taxi car company delete form");

				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}
		}
		// Updating existing taxi car and driver in the database [ADMIN]
		else if (path.endsWith("/taxicar-update-action")) 
		{
			response.setContentType("text/plain");
			
			// Getting the parameters of the taxi car and driver from the request
			int taxiPlateNumber = Integer.parseInt(request.getParameter("plate"));
			String driverName = request.getParameter("drivername");
			String taxiCompanyName = request.getParameter("companyname");
			int taxiDriverRating = Integer.parseInt(request.getParameter("rating"));
			String classification = request.getParameter("classification");
			double latitude = Double.parseDouble(request.getParameter("latitude"));
			double longitude = Double.parseDouble(request.getParameter("longitude"));
			String workingHours = request.getParameter("workinghours");
					
			// Taxi car to update
			TaxiCar taxiCarToUpdate = new TaxiCar(taxiCompanyName, taxiPlateNumber,
					driverName, taxiDriverRating, classification, latitude,
					longitude, workingHours);
			try 
			{
				// Checking if the taxi car company is already exist in the database
				if (!MySQLTaxiCompanyDAO.getInstance().isCompanyExists(taxiCompanyName))
				{
					TaxiCompany taxiCompany = new TaxiCompany(taxiCompanyName);
					
					// Adding a new taxi car company into the database
					MySQLTaxiCompanyDAO.getInstance().addTaxiCompany(taxiCompany);
					
					// Info log message
					logger.info("[Admin]: taxi car [" + taxiCompanyName + "] company was added automatically"
							+ " during taxi car update process");
				}
				
				// Updating an existing taxi car and driver
				MySQLTaxiCarDAO.getInstance().updateTaxiCar(taxiCarToUpdate);
				
				// Info log message
				logger.info("[Admin]: taxi car [" + taxiPlateNumber + "] info was updated");
				
				response.getWriter().print("updated");
			} 
			catch (TaxiToGoException exceptionEvent) 
			{
				// Setting an exception attribute with the exception event as a value
				request.setAttribute("exception", exceptionEvent);
				
				// Error log message
				logger.error("[Admin]: taxi car [" + taxiPlateNumber + "] info was NOT updated");
				
				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}	
		}
		// Adding a new taxi car company to the database [ADMIN]
		else if (path.endsWith("company-add-action")) 
		{
			response.setContentType("text/plain");
			
			// Getting the parameters of the taxi company from the request
			String companyName = request.getParameter("companyname");
			String companyAddress = request.getParameter("companyaddress");		
			TaxiCompany companyToAdd = new TaxiCompany(companyName, companyAddress);
			
			try 
			{
				// Adding a new taxi company into the database
				MySQLTaxiCompanyDAO.getInstance().addTaxiCompany(companyToAdd);
				
				// Info log message
				logger.info("[Admin]: company \"" + companyName + "\" was added into the database");
				
				response.getWriter().print("added");
			} 
			catch (TaxiToGoException exceptionEvent) 
			{
				// Setting an exception attribute with the exception event as a value
				request.setAttribute("exception", exceptionEvent);
				
				// Error log message
				logger.error("[Admin]: company \"" + companyName + "\" was NOT added into the database");
				
				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}	
		}
		// Updating an existing taxi car company in the database [ADMIN]
		else if (path.endsWith("company-update-action")) 
		{
			response.setContentType("text/plain");
			
			// Getting the parameters of the taxi company from the request
			String companyName = request.getParameter("companyname");
			String companyAddress = request.getParameter("companyaddress");
			TaxiCompany companyToUpdate = new TaxiCompany(companyName, companyAddress);
			
			try 
			{
				// Updating an existing taxi company in the database
				MySQLTaxiCompanyDAO.getInstance().updateTaxiCompany(companyToUpdate);
				
				// Info log message
				logger.info("[Admin]: company \"" + companyName + "\" info was updated");
				
				response.getWriter().print("updated");
			} 
			catch (TaxiToGoException exceptionEvent) 
			{
				// Setting an exception attribute with the exception event as a value
				request.setAttribute("exception", exceptionEvent);
				
				// Error log message
				logger.error("[Admin]: company \"" + companyName + "\" info was NOT updated");
				
				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}	
		}
		// Deleting an existing taxi car and driver from the database [ADMIN]
		else if (path.endsWith("taxicar-delete-action"))
		{
			response.setContentType("text/plain");
			
			// Getting the parameter of the taxi car and driver from the request
			int taxiPlateNumber = Integer.parseInt(request.getParameter("plate"));

			try 
			{	
				// Deleting an existing taxi car and driver from the database
				if (MySQLTaxiCarDAO.getInstance().deleteTaxiCar(taxiPlateNumber)) 
				{
					// Info log message
					logger.info("[Admin]: taxi car [" + taxiPlateNumber + "] was deleted from the database");
					
					response.getWriter().print("deleted");
				}
				else 
				{
					response.getWriter().print("not deleted");
				}
			} 
			catch (TaxiToGoException exceptionEvent) 
			{
				// Setting an exception attribute with the exception event as a value
				request.setAttribute("exception", exceptionEvent);
				
				// Error log message
				logger.error("[Admin]: taxi car [" + taxiPlateNumber + "] was NOT deleted from the database");
				
				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}	
		}
		// Deleting an existing taxi car company from the database [ADMIN]
		else if (path.endsWith("company-delete-action"))
		{
			response.setContentType("text/plain");
			
			// Getting the parameter of the taxi company from the request
			String companyName = request.getParameter("companyname");

			try 
			{
				if (MySQLTaxiCompanyDAO.getInstance().deleteTaxiCompany(companyName)) 
				{
					// Info log message
					logger.info("[Admin]: company \"" + companyName + "\" was deleted from the database");
					
					response.getWriter().print("deleted");
				}
				else // If the taxi car and driver wasn't deleted
				{
					response.getWriter().print("not deleted");
				}
			} 
			catch (TaxiToGoException exceptionEvent) 
			{
				// Setting an exception attribute with the exception event as a value
				request.setAttribute("exception", exceptionEvent);
				
				// Error log message
				logger.error("[Admin]: company \"" + companyName + "\" was NOT deleted from the database");
				
				dispatcher = request.getRequestDispatcher("/error.html");
				dispatcher.forward(request, response);
			}	
		}
		// Log in action
		else if (path.endsWith("login-action"))
		{
			response.setContentType("text/plain");
			
			// Getting the parameters of the user from the request
			String user = request.getParameter("user");
			String password = request.getParameter("password");
			String keepMeLogin = request.getParameter("keepLog");
			
			boolean isExist = false;

			// Checks if the given user and password aren't empty strings
			if (user != null && password != null) 
			{
				User userToLogin = new User (user, password);
				
				try 
				{	
					isExist = MySQLUserDAO.getInstance().isUserExist(userToLogin);
					
					// Checks if the given user does exist in the database
					if (isExist) 
					{
						// Checks if the user asked to be logged in a "Keep Me Log In" mode
						if (keepMeLogin != null) 
						{
							// Setting the attribute with the "keepMeLogIn" value
							request.getSession().setAttribute("status", "keepMeLogIn");
							
							// Info log message
							logger.info("[User]: " + user + " was logged in as Admin [keep me logged in mode: ON]");
						}
						else // Checks if the user asked to be logged in a "Don't Keep Me Log In" mode
						{
							// Setting the attribute with the "dontKeepMeLogIn" value
							request.getSession().setAttribute("status", "dontKeepMeLogIn");
							
							// Info log message
							logger.info("[User]: " + user + " was logged in as Admin [keep me logged in mode: OFF]");
						}
						
						response.getWriter().print("login");
					}
					else // If the user doesn't exist in the database
					{
						response.getWriter().print("logout");
						
						// Info log message
						logger.info("[User]: " + user + " was NOT able to logged in as Admin (Incorrect Email/Password Combination)");
					}
					
				} 
				catch (TaxiToGoException exceptionEvent) 
				{
					// Setting an exception attribute with the exception event as a value
					request.setAttribute("exception", exceptionEvent);
					
					// Error log message
					logger.error("[Admin]: " + user + " was NOT able to logg in as Admin");
					
					dispatcher = request.getRequestDispatcher("/error.html");
					dispatcher.forward(request, response);
				}
			}
		}
		// ADMIN log in action 
		else if (path.contains("admin-login")) 
		{
			// If the user doesn't connected as an ADMIN
			if ((request.getSession().getAttribute("status") == null)) 
			{
				response.sendRedirect(request.getContextPath() + "/admin_login.html");
			}
			// If the user is connected in a "Don't Keep Me Log In" mode 
			else if (request.getSession().getAttribute("status").equals("dontKeepMeLogIn")) 
			{
				response.sendRedirect(request.getContextPath() + "/admin_login.html");
			}
			else // if the user is connected in a "Keep Me Log In" mode 
			{
				response.sendRedirect(request.getContextPath() + "/admin_index.jsp");
			}
		}
		// ADMIN log out action
		else if (path.contains("logout")) 
		{
			// In a logout action the session will be invalidated
			request.getSession().invalidate();
			
			// Info log message
			logger.info("[Admin]: admin was logged out");
			
			// In a logout action the user will be redirected to the home page
			response.sendRedirect(request.getContextPath() + "/index.html");
		}
	}
}