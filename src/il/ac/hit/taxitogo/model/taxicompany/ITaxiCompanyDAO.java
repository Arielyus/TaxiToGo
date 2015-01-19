package il.ac.hit.taxitogo.model.taxicompany;

import il.ac.hit.taxitogo.model.exception.TaxiToGoException;

import java.util.Collection;

/**
 * <p>
 * An Interface class for taxi car company DAO purpose.
 * </p>
 * 
 * @version 22.01.2015
 * @author Victor Zhinzherov
 * @author Lior Kizer
 * @author Ariel Yusupov
 */
public interface ITaxiCompanyDAO
{
	/**
	 * <p>
	 * This method is used to add new taxi car company into the database.
	 * </p>
	 * 
	 * @param taxiCompanyToAdd
	 *            <code>TaxiCompany</code> - Taxi company object
	 * @return <code>void</code>
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	public void addTaxiCompany(TaxiCompany taxiCompanyToAdd) throws TaxiToGoException;

	/**
	 * <p>
	 * This method is used to get taxi car company from the database.
	 * </p>
	 * 
	 * @param taxiCompanyName
	 *            <code>String</code> - Taxi car company name
	 * @return <code>TaxiCompany</code> - Taxi car company object
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	public TaxiCompany getTaxiCompany(String taxiCompanyName) throws TaxiToGoException;

	/**
	 * <p>
	 * This method is used to get taxi cars companies list from the database.
	 * </p>
	 * 
	 * @return <code>Collection</code> - Taxi cars companies list
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	public Collection<TaxiCompany> getTaxiCompanys() throws TaxiToGoException;

	/**
	 * <p>
	 * This method is used to delete taxi car company from the database.
	 * </p>
	 * 
	 * @param taxiCompanyName
	 *            <code>String</code> - Taxi car company name
	 * @return <code>boolean</code> - True if taxi car company was deleted.
	 *         False if taxi car company wasn't deleted.
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	public boolean deleteTaxiCompany(String taxiCompanyName) throws TaxiToGoException;

	/**
	 * <p>
	 * This method is used to update taxi car company info in the database.
	 * </p>
	 * 
	 * @param taxiCompanyToUpdate
	 *            <code>TaxiCompany</code> - Taxi car company object
	 * @return <code>void</code>
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	public void updateTaxiCompany(TaxiCompany taxiCompanyToUpdate) throws TaxiToGoException;

	/**
	 * <p>
	 * This method is used to check, if a certain taxi car company exist in the
	 * database.
	 * </p>
	 * 
	 * @param companyName
	 *            <code>String</code> - Taxi car company name
	 * @return <code>boolean</code> True if taxi car company is exist. False if
	 *         taxi car company doesn't exist.
	 * @throws TaxiToGoException
	 *             If there was a problem connecting the database
	 */
	public boolean isCompanyExists(String companyName) throws TaxiToGoException;
}