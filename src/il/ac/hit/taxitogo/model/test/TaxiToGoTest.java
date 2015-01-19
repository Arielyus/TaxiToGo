package il.ac.hit.taxitogo.model.test;

import static org.junit.Assert.*;
import il.ac.hit.taxitogo.model.exception.*;
import il.ac.hit.taxitogo.model.taxicompany.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Class for testing purpose
 * 
 * @version 22.01.2015
 * @author Victor Zhinzherov
 * @author Lior Kizer
 * @author Ariel Yusupov
 */
public class TaxiToGoTest
{
	private MySQLTaxiCompanyDAO taxiComapanyDAO;

	/**
	 * <p>
	 * This method is used to set-up the class TaxiToGoTest for JUnit tests.
	 * </p>
	 * 
	 * @return <code>void</code>
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		taxiComapanyDAO = MySQLTaxiCompanyDAO.getInstance();
	}

	/**
	 * <p>
	 * This method is used to tear-down the class TaxiToGoTest for JUnit tests.
	 * </p>
	 * 
	 * @return <code>void</code>
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		taxiComapanyDAO = null;
	}

	/**
	 * <p>
	 * This method is used to test the class taxiComapanyDAO using JUnit.<br>
	 * In this test we check that a taxi car company does actually exist in the
	 * database.<br>
	 * </p>
	 * <p>
	 * For this test the taxi car company is already in the database.
	 * </p>
	 * 
	 * @return <code>void</code>
	 * @throws Exception
	 */
	@Test
	public void testIsCompanyExists()
	{
		Boolean expectedResult = true;
		Boolean actualResult = false;

		try
		{
			actualResult = taxiComapanyDAO.isCompanyExists("Get Taxi");
		}

		catch (TaxiToGoException e)
		{
			e.printStackTrace();
		}

		assertEquals(expectedResult, actualResult);
	}

	/**
	 * <p>
	 * This method is used to test the class taxiComapanyDAO using JUnit.<br>
	 * In this test we check that a taxi car company was actually added to the
	 * database.<br>
	 * </p>
	 * <p>
	 * For this test the taxi car company is already in the database.
	 * </p>
	 * 
	 * @return <code>void</code>
	 * @throws Exception
	 */
	@Test
	public void testAddTaxiCompany()
	{
		TaxiCompany taxiComapany = new TaxiCompany("Check", null);

		Boolean expectedResult = true;
		Boolean actualResult = false;

		try
		{
			taxiComapanyDAO.addTaxiCompany(taxiComapany);
			actualResult = taxiComapanyDAO.isCompanyExists("Check");
		}

		catch (TaxiToGoException e)
		{
			e.printStackTrace();
		}

		assertEquals(expectedResult, actualResult);
	}

	/**
	 * <p>
	 * This method is used to test the class taxiComapanyDAO using JUnit.<br>
	 * In this test we check that a taxi car company was actually deleted from the
	 * database.<br>
	 * </p>
	 * <p>
	 * For this test the taxi car company is already in the database.
	 * </p>
	 * 
	 * @return <code>void</code>
	 * @throws Exception
	 */
	@Test
	public void testDeleteTaxiCompany()
	{
		Boolean expectedResult = true;
		Boolean actualResult = false;

		try
		{
			actualResult = taxiComapanyDAO.deleteTaxiCompany("KOKO");
		}

		catch (TaxiToGoException e)
		{
			e.printStackTrace();
		}

		assertEquals(expectedResult, actualResult);
	}
}