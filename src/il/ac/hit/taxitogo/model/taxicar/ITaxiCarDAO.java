package il.ac.hit.taxitogo.model.taxicar;

import il.ac.hit.taxitogo.model.exception.TaxiToGoException;

import java.util.Collection;

/**
 * <p>
 * An Interface class for taxi car DAO purpose.
 * </p>
 * 
 * @version 22.01.2015
 * @author Victor Zhinzherov
 * @author Lior Kizer
 * @author Ariel Yusupov
 */
public interface ITaxiCarDAO
{
	/**
	 * <p>
	 * This method is used to add new taxi car and driver into the database.
	 * </p>
	 * 
	 * @param taxiCar
	 *            <code>TaxiCar</code> - Taxi car and driver object
	 * @return <code>void</code>
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	public void addTaxiCar(TaxiCar taxiCar) throws TaxiToGoException;

	/**
	 * <p>
	 * This method is used to get a specific taxi car and driver from the database.
	 * </p>
	 * 
	 * @param taxiNumberPlate
	 *            <code>int</code> - Taxi car plate number
	 * @return <code>TaxiCar</code> - Taxi car and driver object
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	public TaxiCar getTaxiCar(int taxiNumberPlate) throws TaxiToGoException;

	/**
	 * <p>
	 * This method is used to get taxi cars and drivers list from the database.
	 * </p>
	 * 
	 * @return <code>Collection</code> - Taxi cars and drivers list
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	public Collection<TaxiCar> getTaxiCars() throws TaxiToGoException;

	/**
	 * <p>
	 * This method is used to get a five of the most closest taxi cars and drivers as a list
	 * from the database by users latitude and longitude coordinates.
	 * </p>
	 * 
	 * @param latitude
	 *            <code>double</code> - Taxi car latitude coordinate
	 * @param longitude
	 *            <code>double</code> - Taxi car longitude coordinate
	 * @return <code>Collection</code> - List of a five of the most closest taxi cars and drivers
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	public Collection<TaxiCar> getFiveClosestTaxiCars(double latitude,
			double longitude) throws TaxiToGoException;

	/**
	 * <p>
	 * This method is used to delete an existing taxi car and driver from the database.
	 * </p>
	 * 
	 * @param taxiNumberPlate
	 *            <code>int</code> - Taxi car plate number
	 * @return <code>boolean</code> - True if taxi car and driver was deleted. False if
	 *         taxi car and driver wasn't deleted.
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	public boolean deleteTaxiCar(int taxiNumberPlate) throws TaxiToGoException;

	/**
	 * <p>
	 * This method is used to update an existing taxi car and driver info in the database.
	 * </p>
	 * 
	 * @param taxiCarToUpdate
	 *            <code>TaxiCar</code> - Taxi car and driver object
	 * @return <code>void</code>
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	public void updateTaxiCar(TaxiCar taxiCarToUpdate) throws TaxiToGoException;
}