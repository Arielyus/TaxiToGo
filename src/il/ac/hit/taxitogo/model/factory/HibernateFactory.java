package il.ac.hit.taxitogo.model.factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * <p>
 * Class for session factory purpose
 * </p>
 * 
 * @version 22.01.2015
 * @author Victor Zhinzherov
 * @author Lior Kizer
 * @author Ariel Yusupov
 */

public class HibernateFactory
{
	private SessionFactory factory = null;
	private static HibernateFactory hibernateFactory = null;

	/**
	 * <p>
	 * Class Constructor [Empty]
	 * </p>
	 */
	public HibernateFactory()
	{
		// Creating a new session factory
		this.factory = new AnnotationConfiguration().configure().buildSessionFactory();
	}

	/**
	 * <p>
	 * Constructs a new singleton SessionFactory
	 * </p>
	 * 
	 * @return <code>FactoryDAO</code> - SessionFactory single instance
	 */
	public static HibernateFactory getInstance()
	{
		if (hibernateFactory == null)
		{
			hibernateFactory = new HibernateFactory();
		}
		return hibernateFactory;
	}

	/**
	 * <p>
	 * Get the SessionFactory single instance
	 * </p>
	 * 
	 * @return <code>SessionFactory</code> - SessionFactory single instance
	 */
	public SessionFactory getFactory()
	{
		return factory;
	}
}