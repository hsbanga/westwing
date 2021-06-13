/**
 * @author Harjinder Singh Banga
 * @email hsbanga@yahoo.com
 * @create date 08-June-2021 
 * @modify By:  date:
 * 
 * @desc This Class contains the log4j methods and data members.
 */

package utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogBack {
	
	 // Configure the log4j property file.
	 static{PropertyConfigurator.configure("src/test/resources/configuration/log4j.properties");}
	 // Initialize Log4j logs.
	 public static Logger log = Logger.getLogger(LogBack.class.getName());//
	 
	 /**This is to print log for the beginning of the test scenarios.
	  * 
	  * @param testScenarioName : Scenarios Name.
	  */
	 public static void startScenario(String testScenarioName){
	 
	 log.info("****************************************************************************************");
	 
	 log.info("$$$$$$$$$$$$$$$$$$$$$        -START-"+testScenarioName+"        $$$$$$$$$$$$$$$$$$$$$$$$$");
	 
	 log.info("****************************************************************************************");
	 
	 }
	 
	/**This is to print log for the ending of the test scenarios.
	 * 
	 * @param testScenarioName : Scenarios Name.
	 */
	 public static void endScenario(String testScenarioName){

	 log.info("XX");
	 
	 log.info("XXXXXXXXXXXXXXXXXXXXXXX        -END-"+testScenarioName+"        XXXXXXXXXXXXXXXXXXXXXX");
	 
	 log.info("XX");
	 
	 }

}
