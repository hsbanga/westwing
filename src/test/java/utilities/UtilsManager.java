/**
 * @author Harjinder Singh Banga
 * @email hsbanga@yahoo.com
 * @create date 08-June-2021 
 * @modify By:  date:
 * 
 * @desc This Class contains modified selenium pre defined method or data member to utilizes during test execution and creation.
 */

package utilities;

import cucumber.ScenarioContext;

public class UtilsManager {
	
	public SeleniumUtils seleniumUtils = null;
	public JavaUtils javaUtils = null;
	public ScenarioContext scenarioContext = null;
	
	public UtilsManager(SeleniumUtils seleniumUtils,JavaUtils javaUtils) {
		this.seleniumUtils = seleniumUtils;
		this.javaUtils = javaUtils;
		
	}
	
}
