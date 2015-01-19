package il.ac.hit.taxitogo.model.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import il.ac.hit.taxitogo.model.exception.TaxiToGoException;
import il.ac.hit.taxitogo.model.factory.HibernateFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * <p>
 * MySQLUserDAO provides an API for accessing Taxi car user database.<br>
 * This class implement with Hibernate.
 * </p>
 * 
 * @version 22.01.2015
 * @author Victor Zhinzherov
 * @author Lior Kizer
 * @author Ariel Yusupov
 */
public class MySQLUserDAO implements IUserDAO
{
	private static MessageDigest digester;
	private SessionFactory factory = null;
	private static MySQLUserDAO dao = null;

	/**
	 * <p>
	 * Class Constructor [Empty].
	 * </p>
	 */
	private MySQLUserDAO()
	{
		try
		{
			digester = MessageDigest.getInstance("MD5");
		} 
		catch (NoSuchAlgorithmException exceptionEvent)
		{
			exceptionEvent.printStackTrace();
		}

		// Getting a single instance of a session factory
		factory = HibernateFactory.getInstance().getFactory();
	}

	/**
	 * <p>
	 * Constructs a new Singleton SessionFactory.
	 * </p>
	 * 
	 * @return <code>MySQLUserDAO</code> - MySQLUserDAO single instance
	 */
	public static MySQLUserDAO getInstance()
	{
		if (dao == null)
		{
			dao = new MySQLUserDAO();
		}
		return dao;
	}

	/**
	 * <p>
	 * This method is used to add a new user into the database
	 * that was implemented by Hibernate.
	 * </p>
	 * 
	 * @param userToAdd
	 *            <code>User</code> - User object
	 * @return <code>void</code>
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	@Override
	public void addUser(User userToAdd) throws TaxiToGoException
	{
		Session session = null;
		Transaction transaction = null;

		try
		{
			session = factory.openSession();
			transaction = session.beginTransaction();
			
			// User's password will be stored as encrypted inside the database
			String encriptedPassword = MD5Encryption(userToAdd.getPassword());
			
			// User to add (with encrypted password) into the database
			User encriptedUserToAdd = new User(userToAdd.getUsername(), encriptedPassword);

			// Adding a new user into the database
			session.save(encriptedUserToAdd);
			transaction.commit();
		}
		catch (HibernateException exceptionEvent)
		{
			if (transaction != null)
			{
				transaction.rollback();
			}

			// Throwing an exception if there was a problem to add a new user into the database
			throw new TaxiToGoException("There was an error to add the user: " 
				+ userToAdd.getUsername(), exceptionEvent);
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
	 * This method is a boolean method that is used when the user is trying to connect as an admin. 
	 * It checks if a certain user exist in the database and if the given password matches the user's 
	 * encrypted password that is stored inside the database that was implemented by Hibernate.
	 * </p>
	 * 
	 * @param userToCheck
	 *            - User object
	 * @return <code>boolean</code> True if user is exist inside the database and the given password
	 * 				 match the encrypted password that is stored inside the database. False if it's otherwise.
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	@Override
	public boolean isUserExist(User userToCheck) throws TaxiToGoException
	{
		Session session = null;

		try
		{
			session = factory.openSession();
			session.beginTransaction();

			// User to check
			User encryptedUserToCheck = (User) session.get(User.class, userToCheck.getUsername());

			// Does the user exist inside the database?
			if (encryptedUserToCheck == null)
			{	
				return false;
			}

			String encryptedPassword = MD5Encryption(userToCheck.getPassword());
			
			/* If the user exist inside the database, check if his encrypted password inside 
			   the database match the given password */
			if (encryptedUserToCheck.getPassword().equals(encryptedPassword))
			{
				return true;
			} 
			else
			{
				return false;
			}
		}
		catch (HibernateException exceptionEvent)
		{
			if (session.getTransaction() != null)
			{
				session.getTransaction().rollback();
			}
		}
		finally
		{	// releasing resources
			if (session != null)
			{
				session.close();
			}
		}
		return false;
	}

	/**
	 * <p>
	 * This method is used to delete the user from the database
	 * that was implemented by Hibernate
	 * </p>
	 * 
	 * @param usernameToDelete
	 *            <code>String</code> - User name
	 * @return <code>void</code>
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	@Override
	public void deleteUser(String usernameToDelete) throws TaxiToGoException
	{
		Session session = null;
		Transaction transaction = null;

		try
		{
			session = factory.openSession();
			transaction = session.beginTransaction();

			// Searching for a specific user inside the database by it's name
			User userToDelete = (User) session.get(User.class, usernameToDelete);

			// Deleting the specific user from the database
			session.delete(userToDelete);
			transaction.commit();
		}
		catch (HibernateException exceptionEvent)
		{
			if (transaction != null)
			{
				transaction.rollback();
			}

			// Throwing an exception if there was a problem to delete the user from the database
			throw new TaxiToGoException("There was an error to delete user: " 
					+ usernameToDelete, exceptionEvent);
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
	 * This method is used to encrypt the used password.<br>
	 * The encryption is implemented by MD5-Encryption.<br>
	 * This method implemented by Hibernate.
	 * </p>
	 * 
	 * @param passwordToEncrpyt
	 *            <code>String</code> - User name
	 * @return <code>void</code>
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	private String MD5Encryption(String passwordToEncrpyt) throws IllegalArgumentException
	{
		// Password validation
		if (passwordToEncrpyt == null || passwordToEncrpyt.length() == 0)
		{
			throw new IllegalArgumentException("String to encrypt cannot be null or zero length");
		}

		digester.update(passwordToEncrpyt.getBytes());
		byte[] hash = digester.digest();
		StringBuffer hexString = new StringBuffer();

		for (int i = 0; i < hash.length; i++)
		{
			if ((0xff & hash[i]) < 0x10)
			{
				hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
			} 
			else
			{
				hexString.append(Integer.toHexString(0xFF & hash[i]));
			}
		}
		return hexString.toString();
	}
}