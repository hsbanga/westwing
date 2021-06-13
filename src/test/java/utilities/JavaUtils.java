/**
 * @author Harjinder Singh Banga
 * @email hsbanga@yahoo.com
 * @create date 08-June-2021 
 * @modify By:  date:
 * 
 * @desc This Class contains Java method or data member to utilizes during test execution and creation.
 */

package utilities;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class JavaUtils {
	
	// Variables/Objects declaration
	public static JavaUtils javaUtilsSelfObj= new JavaUtils();
	public static String envName = javaUtilsSelfObj.getSystemProperty("TEST_ENV", "prod").toLowerCase();
	static Properties globalPropertyObj=new Properties();
	Properties envPropertyObj=null;
	String global_property_file_path="src/test/resources/configuration/global.properties";
	String test_env_property_file_path="src/test/resources/configuration/{envName_placeholder}Env.properties";
	
	// Methods Declaration
	
	/**
	 * This method utilize to loads the Global and test environment(dev/stag/prod) property files.
	 * Test Environment Need to be pass from CLA otherwise pick default one i.e. dev.
	 *
	 */
	public void load_Properties() throws IOException{
		
		if(globalPropertyObj.size()==0) {
			
			try {
				envPropertyObj=new Properties();
				
				FileInputStream globalFileObj = new FileInputStream(global_property_file_path);
				globalPropertyObj.load(globalFileObj);
				
				FileInputStream envFileObj = new FileInputStream(test_env_property_file_path.replace("{envName_placeholder}", envName));
				envPropertyObj.load(envFileObj);
				
				globalPropertyObj.putAll(envPropertyObj);
				
				globalFileObj.close();
				envFileObj.close();
				
			}
			 catch (FileNotFoundException e) {
				e.printStackTrace();
				LogBack.log.error(e);
			}
		}		
	}
	
	/**
	 * This overloaded Method used to load the additional test application specific property files and merge
	 * it with the global property object to access the the properties globally with single object .
	 *
	 * @param property_file_path : path of the properties file
	 */
	public void load_Properties(String property_file_path) throws IOException{
		
		if(globalPropertyObj.size()!=0) {
			
			try {
				Properties randomPropertyObj=new Properties();
				FileInputStream randomFileObj = new FileInputStream(property_file_path);
				randomPropertyObj.load(randomFileObj);
				globalPropertyObj.putAll(randomPropertyObj);
				randomFileObj.close();
				
			}
			 catch (FileNotFoundException e) {
				e.printStackTrace();
				LogBack.log.error(e);
			}
		}
		
		else {
			try {
				throw new NullPointerException("utilities.JavaUtils.globalPropertyObj object not found or Instantiated!");
			}
			catch (NullPointerException e) {
				e.printStackTrace();
				LogBack.log.error("utilities.JavaUtils.globalPropertyObj object not found or Instantiated!", e);
			}
		}
		
	}
	
	/**
	 * Returns specific property value by key as parameter of all the real time loaded properties using global property object.
	 *
	 * @param property_name : Name of the Property to access the value(data) 
	 * @return The value(data) of specific property passes through parameter.
	 */
	public String get_Property(String property_name){
		
		return globalPropertyObj.getProperty(property_name);
	}
	
	/**
	 * Get environment properties passes from the Command line or Default value.
	 *
	 * @param propertyName : Name the property for Command line argument reference.
     * @param defaultValue : If required then set default value of property to utilize else pass empty string example:"".
     * @return The Value Passes from the Command line otherwise returns default value.
	 */
	public String getSystemProperty(String propertyName, String defaultValue) {
		
		String propertyValue = null;
		
		if(defaultValue.isEmpty()) {
			if(System.getProperty(propertyName)==null) {
				LogBack.log.error("Please provide the valid "+propertyName+" value");
			}
			else {
				propertyValue = System.getProperty(propertyName);
			}	
		}
		else {
			propertyValue = (System.getProperty(propertyName)!=null)?System.getProperty(propertyName):defaultValue;
		}
		
		return propertyValue;	
	}
	
	/**
	 * Performs a Thread.sleep using this time unit.This is a convenience method that converts time arguments into the form required by the Thread.sleep method.
	 *
	 * @param milliseconds : timeout the minimum time to sleep. If less than
     * or equal to zero, do not sleep at all.
     * @throws InterruptedException if interrupted while sleeping
	 */
    public void pause(long milliseconds){
	    try {
	        TimeUnit.MILLISECONDS.sleep(milliseconds);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
    }
	
	/**
	 * Extracting the text that was copied to the clipboard.
	 *
     * @return Current copied text from the clipboard. 
	 * @throws IOException 
	 * @throws UnsupportedFlavorException 
	 * @throws HeadlessException 
	 */
    public String getCurrentTextFromClipboard() throws HeadlessException, UnsupportedFlavorException, IOException {

    	String copiedTextToClipboard = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
    	
    	return copiedTextToClipboard;
    }

	/**
	 * Returns lower case alphabetical random String of given size as integer parameter.
	 * 
	 * @param length - of characters in returned String required
	 * @return Alphabetical lower case letters random String
	 */
	public String randomLowerAlphabeticStringOf(int length) {

		Random rand = new Random();
		String chars = "abcdefghijklmnopqrstuvwxyz";

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}

		return sb.toString();
	}

	/**
	 * Returns lower and upper both's mix case alphabetical random String of given size as integer parameter.
	 * 
	 * @param length - of characters in returned String required
	 * @return Alphabetical mix cases letters random String
	 */
	public String randomMixCaseAlphabeticStringOf(int length) {

		Random rand = new Random();
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
						+ "abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}

		return sb.toString();
	}

	/**
	 * Returns lower alphabets plus numeric random String of given size as integer parameter.
	 *
	 * @param length - number of characters in returned String required
	 * @return Alphanumeric with lower case letters random String 
	 */
	public String randomLowerAlphaNumericStringOf(int length) {

		Random rand = new Random();
		String chars = "abcdefghijklmnopqrstuvxyz"
						+ "0123456789";

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}

		return sb.toString();
	}

	/**
	 * Returns lower alphabets plus numeric random String of given size as integer parameter.
	 *
	 * @param length - number of characters in returned String required
	 * @return Alphanumeric mix cases letters random String 
	 */
	public String randomMixCaseAlphaNumericStringOf(int length) {

		Random rand = new Random();
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
						+ "abcdefghijklmnopqrstuvxyz"
						+ "0123456789";

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}

		return sb.toString();
	}

	/**
	 * Returns numeric random String of given size as integer parameter.
	 *
	 * @param length - number of characters in returned String required
	 * @return Numeric random String
	 */
	public int randomNumberOfLength(int length) {

		Random rand = new Random();
		String chars = "0123456789";

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		}

		return Integer.parseInt(sb.toString());
	}

  /** 
   * Generates Random number between the given range included.
   *
   * @param minInclusive - Minimum range of random number included.
   * @param maxInclusive - Maximum range of random number included.
   * @return Random number between and including the given range as parameter.
   */
	public int randomNumberBetween(int minInclusive, int maxInclusive) {
		
		Random rand = new Random();
		int number = rand.nextInt((maxInclusive-minInclusive)+1)+minInclusive;
		
	    return number;
	}

	/**
	 * Returns random String from the given array of strings.
	 *
	 * @param stringsArray - Array of String elements.
	 * @return Random String from a String Array.
	 */
	public String randomStringFrom(String[] stringsArray) {

		Random random = new Random();
		String randomString = stringsArray[random.nextInt(stringsArray.length)];

		return randomString.toString();
	}

	/**
	 * Returns the today's date in string with given format.
	 *
	 * @param dateFormat - the date format in which time to be formatted for example: "dd/MM/yyyy".
	 * @return returns the date string in given format.
	 */
	public String getCurrentDate(String dateFormat) {

		Date date = new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat); 
		String formatedDate= formatter.format(date);

		return formatedDate;
	}

	/**
	 * Returns the future date by days from today's date in string with given format.
	 *
	 * @param dateFormat - the date format in which time to be formatted for example: "dd/MM/yyyy".
	 * @param howFarFromCurrentDateInDays - how many Days need to add into current date to get future date.
	 * @return returns the future date string in given format.
	 */
	public String getFutureDateFromCurrentByDays(String dateFormat, int howFarFromCurrentDateInDays ) {

		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Calendar calander = Calendar.getInstance();
		calander.setTime(new Date());
		calander.add(Calendar.DATE, howFarFromCurrentDateInDays);
		String futureDate = sdf.format(calander.getTime());

		return futureDate;
	}

	/**
	 * Returns the future date by Week from today's date in string with given format.
	 *
	 * @param dateFormat - the date format in which time to be formatted for example: "dd/MM/yyyy".
	 * @param howFarFromCurrentDateInWeeks - how many Weeks need to add into current date to get future date.
	 * @return returns the future date string in given format.
	 */
	public String getFutureDateFromCurrentByWeeks(String dateFormat, int howFarFromCurrentDateInWeeks ) {

		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Calendar calander = Calendar.getInstance();
		calander.setTime(new Date());
		calander.add(Calendar.WEEK_OF_MONTH, howFarFromCurrentDateInWeeks);
		String futureDate = sdf.format(calander.getTime());

		return futureDate;
	}

	/**
	 * Returns the future date by Month from today's date in string with given format.
	 *
	 * @param dateFormat - the date format in which time to be formatted for example: "dd/MM/yyyy".
	 * @param howFarFromCurrentDateInMonths - how many Months need to add into current date to get future date.
	 * @return returns the future date string in given format.
	 */
	public String getFutureDateFromCurrentByMonths(String dateFormat, int howFarFromCurrentDateInMonths ) {

		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Calendar calander = Calendar.getInstance();
		calander.setTime(new Date());
		calander.add(Calendar.MONTH, howFarFromCurrentDateInMonths);
		String futureDate = sdf.format(calander.getTime());

		return futureDate;
	}

	/**
	 * Returns the future date by years from today's date in string with given format.
	 *
	 * @param dateFormat - the date format in which time to be formatted for example: "dd/MM/yyyy".
	 * @param howFarFromCurrentDateInYears - how many Years need to add into current date to get future date.
	 * @return returns the future date string in given format.
	 */
	public String getFutureDateFromCurrentByYears(String dateFormat, int howFarFromCurrentDateInYears ) {

		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Calendar calander = Calendar.getInstance();
		calander.setTime(new Date());
		calander.add(Calendar.YEAR, howFarFromCurrentDateInYears);
		String futureDate = sdf.format(calander.getTime());

		return futureDate;
	}
	
}