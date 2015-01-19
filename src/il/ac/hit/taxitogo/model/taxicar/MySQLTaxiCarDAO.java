package il.ac.hit.taxitogo.model.taxicar;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import il.ac.hit.taxitogo.model.exception.TaxiToGoException;
import il.ac.hit.taxitogo.model.factory.HibernateFactory;
import il.ac.hit.taxitogo.model.taxicompany.MySQLTaxiCompanyDAO;
import il.ac.hit.taxitogo.model.taxicompany.TaxiCompany;

/**
 * <p>
 * MySQLTaxiCarDAO provides an API for accessing Taxi car and driver 
 * database using Hibernate.
 * </p>
 * 
 * @version 22.01.2015
 * @author Victor Zhinzherov
 * @author Lior Kizer
 * @author Ariel Yusupov
 */
public class MySQLTaxiCarDAO implements ITaxiCarDAO
{
	private SessionFactory factory = null;
	private static MySQLTaxiCarDAO dao = null;

	/**
	 * <p>
	 * Class Constructor [Empty]
	 * </p>
	 */
	private MySQLTaxiCarDAO()
	{
		// Getting a single instance of a session factory
		factory = HibernateFactory.getInstance().getFactory();
	}

	/**
	 * <p>
	 * Constructs a new singleton SessionFactory
	 * </p>
	 * 
	 * @return <code>MySQLTaxiCarDAO</code> - MySQLTaxiCarDAO single instance
	 */
	public static MySQLTaxiCarDAO getInstance()
	{
		if (dao == null)
		{
			dao = new MySQLTaxiCarDAO();
		}
		return dao;
	}

	/**
	 * <p>
	 * This method is used to add a new taxi car and driver into the database that
	 * was implemented by Hibernate <br>
	 * </p>
	 * 
	 * @param taxiCar
	 *            <code>TaxiCar</code> - Taxi car and driver object
	 * @return <code>void</code>
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	@Override
	public void addTaxiCar(TaxiCar taxiCarToAdd) throws TaxiToGoException
	{
		Session session = null;
		Transaction transaction = null;

		/* Before adding a new taxi car and drver into the database, check if it's 
		   taxi company already exist in the database */
		if (!MySQLTaxiCompanyDAO.getInstance().isCompanyExists(
				taxiCarToAdd.getTaxiCompanyName()))
		{	
			/* If a taxi company doesn't exist in the database, add it 
			   into the database */
			MySQLTaxiCompanyDAO.getInstance().addTaxiCompany(
					new TaxiCompany(taxiCarToAdd.getTaxiCompanyName()));
		}

		try
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			
			// Adding a new taxi car and driver into the database
			session.save(taxiCarToAdd);
			transaction.commit();
		}
		catch (HibernateException exceptionEvent)
		{	
			if (transaction != null)
			{
				transaction.rollback();
			}

			/* Throwing an exception if there was a problem to add a new taxi car 
				into the database */
			throw new TaxiToGoException("There was an error to add the taxi car: " 
					+ taxiCarToAdd.getTaxiPlateNumber(), exceptionEvent);
		}
		finally
		{	
			if (session != null)
			{	// releasing resources
				session.close();
			}
		}
	}

	/**
	 * <p>
	 * This method is used to get a specific taxi car from the database that was 
	 * implemented by Hibernate. <br>
	 * </p>
	 * 
	 * @param taxiNumberPlate
	 *            <code>int</code> - Taxi car plate number
	 * @return <code>TaxiCar</code> - Taxi car and driver object
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	@Override
	public TaxiCar getTaxiCar(int taxiNumberPlate) throws TaxiToGoException
	{
		Session session = null;

		try
		{
			session = factory.openSession();
			session.beginTransaction();
			
			/* Searching for a specific taxi car and driver inside the database by 
			   it's plate number */
			TaxiCar taxicar = (TaxiCar) session.get(TaxiCar.class,taxiNumberPlate);

			/* Checks if a given taxi car and
			   driver exist in the database */
			if (taxicar != null)
			{
				return taxicar;
			}

			// Throwing an exception if the given taxi car and driver doesn't exist inside the database
			throw new TaxiToGoException("The given taxi number plate: " + taxiNumberPlate + " doesn't exist!");
		}
		catch (HibernateException exceptionEvent)
		{
			if (session.getTransaction() != null)
			{
				session.getTransaction().rollback();
			}

			/* Throwing an exception if there was a problem to get the given 
			   taxi car and driver from the database */
			throw new TaxiToGoException("There was an error to get the taxi car", 
					exceptionEvent);
		}
		finally
		{	
			if (session != null)
			{	// releasing resources
				session.close();
			}
		}
	}

	/**
	 * <p>
	 * This method is used to get a five of the most closest taxi cars and driver in a list, 
	 * from the database by users latitude and longitude coordinates.<br>
	 * This method implemented by Hibernate.
	 * </p>
	 * 
	 * @param latitude
	 *            <code>double</code> - Taxi car latitude coordinate
	 * @param longitude
	 *            <code>double</code> - Taxi car longitude coordinate
	 * @return <code>void</code>
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	@Override
	public Collection<TaxiCar> getFiveClosestTaxiCars(double latitude, 
			double longitude) throws TaxiToGoException
	{
		Session session = null;

		try
		{
			session = factory.openSession();
			session.beginTransaction();
			
			/* Creating an HQL query that orders all the taxi cars by their 
			   destination from the users current location in ascended order */
			String query = "FROM TaxiCar "
					+ "ORDER BY (6371*2*ASIN(SQRT(POWER(SIN((:taxiLatitude-abs(Latitude))*pi()/180/2),2)+"
					+ "COS(:taxiLatitude*pi()/180)*COS(abs(Latitude)*pi()/180)*"
					+ "POWER(SIN((:taxiLongitude-Longitude)*pi()/180/2),2))))*1000 ASC";

			Query queryTaxiCars = session.createQuery(query);

			// Setting parameters inside the HQL query
			queryTaxiCars.setParameter("taxiLatitude", latitude);
			queryTaxiCars.setParameter("taxiLongitude", longitude);

			// Setting the HQL query result to maximum of 5
			queryTaxiCars.setMaxResults(5);
			@SuppressWarnings("unchecked")
			
			/* Getting five of the most closest taxi cars and driver to users
			   current location from the query result */
			List<TaxiCar> taxiCars = queryTaxiCars.list();

			return taxiCars;
		}
		catch (HibernateException exceptionEvent)
		{
			if (session.getTransaction() != null)
			{
				session.getTransaction().rollback();
			}

			// Throwing an exception if there was a problem to get the closes taxi cars and drivers list
			throw new TaxiToGoException("There was an error to get taxi cars list", exceptionEvent);
		}
		finally
		{	// releasing resources
			if (session != null)
			{
				session.close();
			}
		}
	}

	/**
	 * <p>
	 * This method is used to get taxi cars and drivers list from the database
	 * that was implemented by Hibernate.
	 * </p>
	 * 
	 * @return <code>Collection</code> - List of all taxi cars and driver
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	@Override
	public Collection<TaxiCar> getTaxiCars() throws TaxiToGoException
	{
		Session session = null;

		try
		{
			session = factory.openSession();
			session.beginTransaction();

			@SuppressWarnings("unchecked")
			// Getting the list of all taxi cars from the database
			List<TaxiCar> taxiCar = session.createQuery("FROM TaxiCar").list();

			return taxiCar;
		}
		catch (HibernateException exceptionEvent)
		{
			if (session.getTransaction() != null)
			{
				session.getTransaction().rollback();
			}
			
			// Throwing an exception if there was a problem to get the list of all taxi cars from the database
			throw new TaxiToGoException("There was an error to get Taxi car list", exceptionEvent);
		}
		finally
		{	
			if (session != null)
			{	// releasing resources
				session.close();
			}
		}
	}

	/**
	 * <p>
	 * This method is used to delete taxi car from the database that was 
	 * implemented by Hibernate.
	 * </p>
	 * @param taxiNumberPlate
	 *            <code>int</code> - Taxi car plate number
	 * @return <code>boolean</code> - True if taxi car and driver was deleted. False if
	 *         taxi car and driver wasn't deleted.
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	@Override
	public boolean deleteTaxiCar(int taxiNumberPlate) throws TaxiToGoException
	{
		Session session = null;
		Transaction transaction = null;

		try
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			
			// Searching for a specific taxi car and driver inside the database by it's plate number
			TaxiCar taxiCarToDelete = (TaxiCar) session.get(TaxiCar.class, taxiNumberPlate);

			if (taxiCarToDelete != null)
			{
				// Deleting the specific taxi car and driver from the database
				session.delete(taxiCarToDelete);
				transaction.commit();
				
				// Return true if the taxi car and driver was successfully deleted
				return true;
			}
			else
			{
				// Return false if the taxi car and driver wasn't deleted
				return false;
			}

		}
		catch (HibernateException exceptionEvent)
		{
			if (transaction != null)
			{
				transaction.rollback();
			}

			// Throwing an exception if there was a problem to delete the taxi car from the database
			throw new TaxiToGoException("There was an error to delete Taxi car with ID: "
					+ taxiNumberPlate, exceptionEvent);
		}
		finally
		{	
			if (session != null)
			{	// releasing resources
				session.close();
			}
		}
	}

	/**
	 * <p>
	 * This method is used to update an existing taxi car and driver info in the database
	 * that was implemented by Hibernate.
	 * </p>
	 * 
	 * @param taxiCarToUpdate
	 *            <code>TaxiCar</code> - Taxi car and driver object
	 * @return <code>void</code>
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	@Override
	public void updateTaxiCar(TaxiCar taxiCarToUpdate) throws TaxiToGoException
	{
		Session session = null;
		Transaction transaction = null;

		try
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			
			// Updating the specific taxi car and driver inside the database
			session.update(taxiCarToUpdate);
			transaction.commit();
		}
		catch (HibernateException exceptionEvent)
		{
			if (transaction != null)
			{
				transaction.rollback();
			}

			/* Throwing an exception if there was a problem to update the taxi car and 
			   driver inside the database */
			throw new TaxiToGoException("There was an error to update the Taxi car: " 
				+ taxiCarToUpdate.getTaxiPlateNumber(), exceptionEvent);
		}
		finally
		{	
			if (session != null)
			{	// releasing resources
				session.close();
			}
		}
	}
}