package il.ac.hit.taxitogo.model.taxicompany;

/**
 * <p>
 * Class that represent Taxi Company object. Using this class we describe our
 * taxi car companies.
 * </p>
 * 
 * @version 22.01.2015
 * @author Victor Zhinzherov
 * @author Lior Kizer
 * @author Ariel Yusupov
 * 
 */
public class TaxiCompany
{
	private String companyName;
	private String companyAddress;

	/**
	 * <p>
	 * Class Constructor [Empty]. Creates a new taxi car company.
	 * </p>
	 * 
	 */
	public TaxiCompany()
	{
	}

	/**
	 * <p>
	 * Class Constructor. Creates a new taxi car company.
	 * </p>
	 * 
	 * @param companyName
	 *            <code>String</code> - Company name
	 */
	public TaxiCompany(String companyName)
	{
		this(companyName, null);
	}

	/**
	 * <p>
	 * Class Constructor.
	 * </p>
	 * 
	 * @param companyName
	 *            <code>String</code> - Company name
	 * @param companyAddress
	 *            <code>String</code> - Company address
	 */
	public TaxiCompany(String companyName, String companyAddress)
	{
		setCompanyName(companyName);
		setCompanyAddress(companyAddress);
	}

	/**
	 * <p>
	 * This method is used to get the taxi car company name.
	 * </p>
	 * 
	 * @return <code>String</code> - Taxi car company name
	 */
	public String getCompanyName()
	{
		return companyName;
	}

	/**
	 * <p>
	 * This method is used to set the taxi car driver company name.
	 * </p>
	 * 
	 * @param companyName
	 *            <code>String</code> - Taxi car driver company name
	 */
	public void setCompanyName(String companyName)
	{
		// Taxi company name validation
		if (companyName != null && !companyName.equals(""))
		{
			this.companyName = companyName;
		}
	}

	/**
	 * <p>
	 * This method is used to get the taxi car company address.
	 * </p>
	 * 
	 * @return <code>String</code> - Taxi car company address
	 */
	public String getCompanyAddress()
	{
		return companyAddress;
	}

	/**
	 * <p>
	 * This method is used to set the taxi car driver company address.
	 * </p>
	 * 
	 * @param companyAddress
	 *            <code>String</code> - Taxi car driver company address
	 */
	public void setCompanyAddress(String companyAddress)
	{
		// Taxi company address validation
		if (companyAddress != null && !companyAddress.equals(""))
		{
			this.companyAddress = companyAddress;
		}
	}
}