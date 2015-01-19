package il.ac.hit.taxitogo.controller.jersey;

import java.util.Collection;

import il.ac.hit.taxitogo.model.exception.TaxiToGoException;
import il.ac.hit.taxitogo.model.taxicar.MySQLTaxiCarDAO;
import il.ac.hit.taxitogo.model.taxicar.TaxiCar;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * <p>
 * Class that represent Taxi car XML file. Using this class we describe our 
 * taxi car/s in XML format
 * 
 * @version 22.01.2015
 * @author Victor Zhinzherov
 * @author Lior Kizer
 * @author Ariel Yusupov
 */

@Path("xml")
public class GetXml {

	/**
	 * <p>
	 * This method return an XML list of all the taxi car drivers. 
	 * </p>
	 *        
	 * @return - An XML list of all the taxi car drivers
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Collection<TaxiCar> getXmlFromList() 
	{
		Collection<TaxiCar> taxiCarsList = null;

		try 
		{
			// Getting a list of all taxi cars and drivers from the database
			taxiCarsList = MySQLTaxiCarDAO.getInstance().getTaxiCars();
		} 
		catch (TaxiToGoException exceptionEvent) 
		{
			exceptionEvent.printStackTrace();
		}

		return taxiCarsList;
	}

	/**
	 * <p>
	 * This method return an XML of a single taxi car and drive by it's plate number.
	 * </p>
	 * 
	 * @param plate_number
	 *            <code>int</code> - Taxi car plate number         
	 * @return - A single XML node of a taxi car and drive
	 */
	@GET
	@Path("{plate_number}")
	@Produces(MediaType.APPLICATION_XML)
	public TaxiCar getXmlFromTaxiCar(@PathParam("plate_number") int plate_number) 
	{
		TaxiCar taxiCar = null;
		try 
		{
			// Getting a specific taxi car from the database by it's plate number
			taxiCar = MySQLTaxiCarDAO.getInstance().getTaxiCar(plate_number);
		} 
		catch (TaxiToGoException exceptionEvent) 
		{
			exceptionEvent.printStackTrace();
		}

		return taxiCar;
	}
}