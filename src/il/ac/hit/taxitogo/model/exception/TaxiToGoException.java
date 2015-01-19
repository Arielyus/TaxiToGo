package il.ac.hit.taxitogo.model.exception;

/**
 * <p>
 * This is an Exception class that handles the connection problem to the
 * database.<br>
 * This class extends the Exception class.
 * </p>
 * 
 * @version 22.01.2015
 * @author Victor Zhinzherov
 * @author Lior Kizer
 * @author Ariel Yusupov
 */
@SuppressWarnings("serial")
public class TaxiToGoException extends Exception
{
	/**
	 * <p>
	 * Constructs a new exception with the specified detail message
	 * </p>
	 * 
	 * @param exceptionMessage
	 *            <code>String</code> - Exception message
	 */
	public TaxiToGoException(String exceptionMessage)
	{
		super(exceptionMessage);
	}

	/**
	 * <p>
	 * Constructs a new exception with the specified detail message and
     * cause.
     *
	 * </p>
	 * 
	 * @param exceptionMessage
	 *            <code>String</code> - Exception message
	 * @param throwableEvent
	 *            <code>Throwable</code> - Exception event
	 */
	public TaxiToGoException(String exceptionMessage, Throwable throwableEvent)
	{
		super(exceptionMessage, throwableEvent);
	}
}