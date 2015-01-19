package il.ac.hit.taxitogo.model.taxicar;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>
 * Class that represent Taxi car object. Using this class we describe our taxi
 * cars.
 * </p>
 * 
 * @version 22.01.2015
 * @author Victor Zhinzherov
 * @author Lior Kizer
 * @author Ariel Yusupov
 */
@XmlRootElement
public class TaxiCar implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String taxiCompanyName;
	private int taxiPlateNumber;
	private String driverName;
	private int taxiDriverRating;
	private String taxiClassification;
	private double latitude;
	private double longitude;
	private String workingHours;

	/**
	 * <p>
	 * Class Constructor [Empty]. Creates a new taxi car and driver
	 * </p>
	 */
	public TaxiCar()
	{
	}

	/**
	 * <p>
	 * Class Constructor. Creates a new taxi car and driver.
	 * </p>
	 * 
	 * @param taxiCompanyName
	 *            <code>String</code> - Company name
	 * @param taxiPlateNumber
	 *            <code>int</code> - Taxi car plate number
	 * @param driverName
	 *            <code>String</code> - Driver name
	 * @param taxiDriverRating
	 *            <code>int</code> - Driver rating
	 * @param taxiClassification
	 *            <code>String</code> - Car classification
	 * @param latitude
	 *            <code>double</code> - Taxi car latitude coordinate
	 * @param longitude
	 *            <code>double</code> - Taxi car longitude coordinate
	 * @param workingHours
	 *            <code>String</code> - Driver ending shift
	 */
	public TaxiCar(String taxiCompanyName, int taxiPlateNumber,
			String driverName, int taxiDriverRating, String taxiClassification,
			double latitude, double longitude, String workingHours)
	{
		setTaxiCompanyName(taxiCompanyName);
		setTaxiPlateNumber(taxiPlateNumber);
		setDriverName(driverName);
		setTaxiDriverRating(taxiDriverRating);
		setTaxiClassification(taxiClassification);
		setLatitude(latitude);
		setLongitude(longitude);
		setWorkingHours(workingHours);
	}

	/**
	 * <p>
	 * This method is used to get the taxi car company name.
	 * </p>
	 * 
	 * @return <code>String</code> - Taxi car company name
	 */
	public String getTaxiCompanyName()
	{
		return taxiCompanyName;
	}

	/**
	 * <p>
	 * This method is used to set the taxi car company name.
	 * </p>
	 * 
	 * @param taxiCompanyName
	 *            <code>String</code> - Taxi car driver company name
	 */
	public void setTaxiCompanyName(String taxiCompanyName)
	{
		// Taxi company name validation
		if (taxiCompanyName != null && !taxiCompanyName.equals(""))
		{
			this.taxiCompanyName = taxiCompanyName;
		}
	}

	/**
	 * <p>
	 * This method is used to get the taxi car plate number.
	 * </p>
	 * 
	 * @return <code>int</code> - Taxi car plate number
	 */
	public int getTaxiPlateNumber()
	{
		return taxiPlateNumber;
	}

	/**
	 * <p>
	 * This method is used to set the taxi car plate number.
	 * </p>
	 * 
	 * @param taxiPlateNumber
	 *            <code>int</code> - Taxi car plate number
	 */
	public void setTaxiPlateNumber(int taxiPlateNumber)
	{
		// Taxi car plate number validation
		if (taxiPlateNumber >= 0 && taxiPlateNumber <= 9999999)
		{
			this.taxiPlateNumber = taxiPlateNumber;
		}
	}

	/**
	 * <p>
	 * This method is used to get the taxi car driver full name.
	 * </p>
	 * 
	 * @return <code>String</code> - Taxi car driver full name
	 */
	public String getDriverName()
	{
		return driverName;
	}

	/**
	 * <p>
	 * This method is used to set the taxi car driver full name.
	 * </p>
	 * 
	 * @param driverName
	 *            <code>String</code> - Taxi car driver full name
	 */
	public void setDriverName(String driverName)
	{
		// Taxi car driver name validation
		if (driverName != null && !driverName.equals(""))
		{
			this.driverName = driverName;
		}
	}

	/**
	 * <p>
	 * This method is used to get the taxi car driver rating.
	 * </p>
	 * 
	 * @return <code>int</code> - Taxi car driver rating
	 */
	public int getTaxiDriverRating()
	{
		return taxiDriverRating;
	}

	/**
	 * <p>
	 * This method is used to set the taxi car driver rating.
	 * </p>
	 * 
	 * @param taxiDriverRating
	 *            <code>int</code> - Taxi car driver rating
	 */
	public void setTaxiDriverRating(int taxiDriverRating)
	{
		// Taxi car driver rating validation
		if (taxiDriverRating >= 0 && taxiDriverRating <= 5)
		{
			this.taxiDriverRating = taxiDriverRating;
		}
	}

	/**
	 * <p>
	 * This method is used to get the taxi car classification.
	 * </p>
	 * 
	 * @return <code>String</code> - Taxi car classification
	 */
	public String getTaxiClassification()
	{
		return taxiClassification;
	}

	/**
	 * <p>
	 * This method is used to set the taxi car classification.
	 * </p>
	 * 
	 * @param taxiClassification
	 *            <code>String</code> - Taxi car classification
	 */
	public void setTaxiClassification(String taxiClassification)
	{
		// Taxi car classification validation
		if (taxiClassification != null && !taxiClassification.equals(""))
		{
			this.taxiClassification = taxiClassification;
		}
	}

	/**
	 * <p>
	 * This method is used to get the taxi car latitude coordinates.
	 * </p>
	 * 
	 * @return <code>double</code> - Taxi car latitude coordinates
	 */
	public double getLatitude()
	{
		return latitude;
	}

	/**
	 * <p>
	 * This method is used to set the taxi car latitude coordinates.
	 * </p>
	 * 
	 * @param latitude
	 *            <code>double</code> - Taxi car latitude coordinates
	 */
	public void setLatitude(double latitude)
	{
		// Latitude coordinate validation
		if (latitude >= 0 && latitude <= 90)
		{
			this.latitude = latitude;
		}
	}

	/**
	 * <p>
	 * This method is used to get the taxi car longitude coordinates.
	 * </p>
	 * 
	 * @return <code>double</code> - Taxi car longitude coordinates
	 */
	public double getLongitude()
	{
		return longitude;
	}

	/**
	 * <p>
	 * This method is used to set the taxi car longitude coordinates.
	 * </p>
	 * 
	 * @param longitude
	 *            <code>double</code> - Taxi car longitude coordinates
	 */
	public void setLongitude(double longitude)
	{
		// Longitude coordinate validation
		if (longitude >= 0 && longitude <= 180)
		{
			this.longitude = longitude;
		}
	}

	/**
	 * <p>
	 * This method is used to get the taxi car driver working hours.
	 * </p>
	 * 
	 * @return <code>String</code> - Taxi car driver working hours
	 */
	public String getWorkingHours()
	{
		return workingHours;
	}

	/**
	 * <p>
	 * This method is used to set the taxi car driver working hours.
	 * </p>
	 * 
	 * @param workingHours
	 *            <code>String</code> - Taxi car driver working hours
	 */
	public void setWorkingHours(String workingHours)
	{
		this.workingHours = workingHours;
	}
}