package il.ac.hit.taxitogo.model.user;

/**
 * <p>
 * Class that represent User object. Using this class we describe our taxi car
 * users.
 * </p>
 * 
 * @version 22.01.2015
 * @author Victor Zhinzherov
 * @author Lior Kizer
 * @author Ariel Yusupov
 * 
 */

public class User
{
	private String username;
	private String password;

	/**
	 * <p>
	 * Class Constructor [Empty]. Create new user.
	 * </p>
	 */
	public User()
	{
	}

	/**
	 * <p>
	 * Class Constructor. Create new user.
	 * </p>
	 * 
	 * @param username
	 *            <code>String</code> - User name
	 * @param password
	 *            <code>String</code> - User password
	 */
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}

	/**
	 * <p>
	 * This method used to get the user name.
	 * </p>
	 * 
	 * @return <code>String</code> - User name
	 * 
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * <p>
	 * This method used to set the user name.
	 * </p>
	 *
	 * @param username
	 *            <code>String</code> - User name
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * <p>
	 * This method used to get the user password.
	 * </p>
	 * 
	 * @return <code>String</code> - User password
	 * 
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * <p>
	 * This method used to set the user password.
	 * </p>
	 *
	 * @param password
	 *            <code>String</code> - User password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
}