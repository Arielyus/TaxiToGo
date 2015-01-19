package il.ac.hit.taxitogo.model.taxicompany;

import il.ac.hit.taxitogo.model.exception.*;
import il.ac.hit.taxitogo.model.factory.HibernateFactory;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * <p>
 * MySQLTaxiCompanyDAO provides an API for accessing Taxi car company database
 * using Hibernate
 * </p>
 * 
 * @version 22.01.2015
 * @author Victor Zhinzherov
 * @author Lior Kizer
 * @author Ariel Yusupov
 */
public class MySQLTaxiCompanyDAO implements ITaxiCompanyDAO
{
	private SessionFactory factory = null;
	private static MySQLTaxiCompanyDAO dao = null;

	/**
	 * <p>
	 * Class Constructor [Empty]
	 * </p>
	 */
	private MySQLTaxiCompanyDAO()
	{
		// Getting a single instance of a session factory
		factory = HibernateFactory.getInstance().getFactory();
	}

	/**
	 * <p>
	 * Constructs a new Singleton SessionFactory
	 * </p>
	 * 
	 * @return <code>MySQLTaxiCompanyDAO</code> - MySQLTaxiCompanyDAO single instance
	 */
	public static MySQLTaxiCompanyDAO getInstance()
	{
		if (dao == null)
		{
			dao = new MySQLTaxiCompanyDAO();
		}
		return dao;
	}

	/**
	 * <p>
	 * This method is used to add a new taxi car company into the database
	 * that was implemented by Hibernate.
	 * </p>
	 * 
	 * @param taxiCompanyToAdd
	 *            <code>TaxiCompany</code> - Taxi company object
	 * @return <code>void</code>
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	@Override
	public void addTaxiCompany(TaxiCompany taxiCompanyToAdd) throws TaxiToGoException
	{
		Session session = null;
		Transaction transaction = null;

		try
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			
			// Adding a new taxi company into the database
			session.save(taxiCompanyToAdd);
			transaction.commit();
		}
		catch (HibernateException exceptionEvent)
		{
			if (transaction != null)
			{
				transaction.rollback();
			}

			// Throwing an exception if there was a problem to add a new taxi company into the database
			throw new TaxiToGoException( "There was an error to add the taxi company: " 
				+ taxiCompanyToAdd.getCompanyName(), exceptionEvent);
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
	 * This method is used to get taxi car company from the database
	 * that was implemented by Hibernate.
	 * </p>
	 * 
	 * @param taxiCompanyName
	 *            <code>String</code> - Taxi car company name
	 * @return <code>TaxiCompany</code> - Taxi car company object
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	@Override
	public TaxiCompany getTaxiCompany(String taxiCompanyName) throws TaxiToGoException
	{
		Session session = null;

		try
		{
			session = factory.openSession();
			session.beginTransaction();
			
			// Searching for a specific taxi company inside the database by it's name
			TaxiCompany taxiCompany = (TaxiCompany) session.get(TaxiCompany.class, taxiCompanyName);

			/* Checks if a given taxi company
			   exist in the database */
			if (taxiCompany != null)
			{
				return taxiCompany;
			}

			// Throwing an exception if the given taxi company doesn't exist inside the database
			throw new TaxiToGoException("The given Taxi company name: "
					+ taxiCompanyName + " doesn't exist!");
		}
		catch (HibernateException exceptionEvent)
		{
			if (session.getTransaction() != null)
			{
				session.getTransaction().rollback();
			}

			// Throwing an exception if there was a problem to get the given taxi company from the database
			throw new TaxiToGoException("The given Taxi company name: "
					+ taxiCompanyName + " doesn't exist!", exceptionEvent);
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
	 * This method is used to get taxi cars companies list from the database
	 * that was implemented by Hibernate.
	 * </p>
	 * 
	 * @return <code>Collection</code> - Taxi cars companies list
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	@Override
	public Collection<TaxiCompany> getTaxiCompanys() throws TaxiToGoException
	{
		Session session = null;

		try
		{
			session = factory.openSession();
			session.beginTransaction();

			@SuppressWarnings("unchecked")
			// Getting the list of all taxi car companies from the database
			List<TaxiCompany> taxiCompany = session.createQuery("FROM TaxiCompany").list();

			return taxiCompany;
		}
		catch (HibernateException exceptionEvent)
		{
			if (session.getTransaction() != null)
			{
				session.getTransaction().rollback();
			}

			/* Throwing an exception if there was a problem to get the list of all taxi companies 
			   from the database */
			throw new TaxiToGoException("There was an error to get Taxi Companys list", exceptionEvent);
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
	 * This method is used to delete taxi car company from the database
	 * that was implemented by Hibernate.
	 * </p>
	 * 
	 * @param taxiCompanyName
	 *            <code>String</code> - Taxi car company name
	 * @return <code>boolean</code> - True if taxi car company was deleted.
	 *         False if taxi car company wasn't deleted.
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	@Override
	public boolean deleteTaxiCompany(String taxiCompanyName) throws TaxiToGoException
	{
		Session session = null;
		Transaction transaction = null;

		try
		{
			session = factory.openSession();
			transaction = session.beginTransaction();

			// Searching for a specific taxi company inside the database by it's name
			TaxiCompany taxiCompanyToDelete = (TaxiCompany) session.get(TaxiCompany.class, taxiCompanyName);

			if (taxiCompanyToDelete != null)
			{
				// HQL query that deletes the taxi car company from the database
				String queryToExecute = "DELETE FROM TaxiCar WHERE Company = :company_name";
				Query query = session.createQuery(queryToExecute);
				
				// Setting the parameter inside the HQL query
				query.setParameter("company_name", taxiCompanyName);
				
				// Executing the HQL query
				query.executeUpdate();
				session.delete(taxiCompanyToDelete);
				transaction.commit();
				
				/* Return true if the taxi car company was 
				   successfully deleted */
				return true;
			} 
			else
			{
				/* Return false if the taxi car
				   company wasn't deleted */
				return false;
			}
		}
		catch (HibernateException exceptionEvent)
		{
			if (transaction != null)
			{
				transaction.rollback();
			}

			// Throwing an exception if there was a problem to delete the taxi company from the database
			throw new TaxiToGoException("There was an error to delete Taxi company name: " 
				+ taxiCompanyName, exceptionEvent);
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
	 * This method is used to update an existing taxi car company info in the database
	 * that was implemented by Hibernate.
	 * </p>
	 * 
	 * @param taxiCompanyToUpdate
	 *            <code>TaxiCompany</code> - Taxi car company object
	 * @return <code>void</code>
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	@Override
	public void updateTaxiCompany(TaxiCompany taxiCompanyToUpdate) throws TaxiToGoException
	{
		Session session = null;
		Transaction transaction = null;

		try
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			
			// Updating the specific taxi company inside the database
			session.update(taxiCompanyToUpdate);
			transaction.commit();
		}
		catch (HibernateException exceptionEvent)
		{
			if (transaction != null)
			{
				transaction.rollback();
			}

			// Throwing an exception if there was a problem to update the taxi company inside the database
			throw new TaxiToGoException("There was an error to update the Taxi company name: " 
				+ taxiCompanyToUpdate.getCompanyName(), exceptionEvent);
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
	 * This method is used to check if a certain taxi car company exist in the database
	 * that was implemented by Hibernate.
	 * </p>
	 * 
	 * @param companyName
	 *            <code>String</code> - Taxi car company name
	 * @return <code>boolean</code> True if taxi car company is exist. False if
	 *         taxi car company doesn't exist.
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	@Override
	public boolean isCompanyExists(String companyName) throws TaxiToGoException
	{
		try
		{
			// Checks if the company exist in the database
			if (getTaxiCompany(companyName) != null)
			{
				return true;
			}
		}
		catch (TaxiToGoException exceptionEvent)
		{
			return false;
		}

		return false;
	}
}