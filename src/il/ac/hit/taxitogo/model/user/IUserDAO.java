package il.ac.hit.taxitogo.model.user;

import il.ac.hit.taxitogo.model.exception.TaxiToGoException;

/**
 * <p>
 * An Interface class for user DAO purpose.
 * </p>
 * 
 * @version 22.01.2015
 * @author Victor Zhinzherov
 * @author Lior Kizer
 * @author Ariel Yusupov
 */
public interface IUserDAO
{
	/**
	 * <p>
	 * This method is used to add a new user into the database.
	 * </p>
	 * 
	 * @param userToAdd
	 *            <code>User</code> - User object
	 * @return <code>void</code>
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	public void addUser(User userToAdd) throws TaxiToGoException;

	/**
	 * <p>
	 * This method is a boolean method that used when the user is trying to connect as an admin. It checks 
	 * if a certain user exist in the database and if the given password matches the user's encrypted 
	 * password that is stored inside the database
	 * </p>
	 * 
	 * @param userToCheck
	 *            - User object
	 * @return <code>boolean</code> True if user is exist inside the database and the given password
	 * 				 match the encrypted password that is stored inside the database. False if it's otherwise.
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	public boolean isUserExist(User userToCheck) throws TaxiToGoException;

	/**
	 * <p>
	 * This method is used to delete the user from the database.
	 * </p>
	 * 
	 * @param usernameToDelete
	 *            <code>String</code> - User name
	 * @return <code>void</code>
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	public void deleteUser(String usernameToDelete) throws TaxiToGoException;
}