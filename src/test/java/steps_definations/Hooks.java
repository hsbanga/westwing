/**
 * @author Harjinder Singh Banga
 * @email harjinder.banga@loginradius.com
 * @create date 01-APR-2021 
 * @modify By:  date:
 * 
 * @desc This class is to defined the cucumber's hooks.
 */

package steps_definations;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import utilities.JavaUtils;
import utilities.LogBack;
import utilities.SeleniumUtils;
import utilities.UtilsManager;

public class Hooks {

	// Variables/Objects declaration
	private UtilsManager utilsManager = null;
	WebDriver driver;
	public static boolean takeAllScrShots = Boolean
			.parseBoolean(JavaUtils.javaUtilsSelfObj.getSystemProperty("TAKE_ALL_SCREENSHOTS", "false"));

	// Constructor declaration
	public Hooks(UtilsManager utilsManager) {
		this.utilsManager = utilsManager;
	}

	/**
	 * This Scenario Startup method runs automatically before each Scenarios defined
	 * inside the each cucumber feature files and which creates the driver instance
	 * and Logs the scenarios startup.
	 *
	 * @param scenario : passing cucumber's Scenario object variable.
	 */
	@Before(order = 0)
	public void scnStartup(Scenario scenario) {

		utilsManager.seleniumUtils.getDriver();

		LogBack.startScenario(scenario.getName());

		if (takeAllScrShots == false) {

			scenario.log("Taking screenshots at each step option is disabled");
			LogBack.log.info("Taking screenshots at each step option is disabled");
		}

	}

	/**
	 * This method runs automatically after each Scenarios defined inside the each
	 * cucumber feature files and takes the screenshot if scenarios got fail.
	 * 
	 *
	 * @param scenario : passing cucumber's Scenario object variable.
	 */
	@After
	public void takeScreenShot(Scenario scenario) {

		if (scenario.isFailed()) {

			byte[] screen = ((TakesScreenshot) utilsManager.seleniumUtils.getDriver())
					.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screen, "image/png", scenario.getName());

		}
	}

	/**
	 * This Scenario end up method runs automatically after each Scenarios defined
	 * inside the each cucumber feature files and quit the driver if scenarios got
	 * fail and logs the scenario wind-up.
	 * 
	 *
	 * @param scenario : passing cucumber's Scenario object variable.
	 */
	@After(order = 10)
	public void scnWindup(Scenario scenario) {

		if (SeleniumUtils._doNotQuitDriver == false) {

			utilsManager.seleniumUtils.closeBrowser();
		}

		else {

			SeleniumUtils._doNotQuitDriver = false;
		}

		LogBack.endScenario(scenario.getName());
	}

	/**
	 * This Method is created or can be utilize to take the necessary action
	 * automatically before each Steps defined for the scenarios.
	 *
	 */
	@BeforeStep
	public void beforeStep() {

	}

	/**
	 * This method runs automatically after each Steps defined for the scenarios for
	 * taking the all the test screen's screenshot of test execution if
	 * TAKE_ALL_SCREENSHOTS(default:false) environment property set as true or if
	 * not explicitly skipped on particular step.
	 * 
	 *
	 * @param scenario : passing cucumber's Scenario object variable.
	 */

	@AfterStep
	public void screenshotAfterEachStep(Scenario scenario) {

		if (takeAllScrShots == true) {

			if (SeleniumUtils._skipScreenshotAfterStep == true) {

				scenario.log("Either screenshot is not required for the step or screen is on the same page");
				LogBack.log.info("Either screenshot is not required for the step or screen is on the same page");

			}

			else {

				byte[] screen = ((TakesScreenshot) utilsManager.seleniumUtils.getDriver())
						.getScreenshotAs(OutputType.BYTES);
				scenario.attach(screen, "image/png", scenario.getName());

			}
		}

		SeleniumUtils._skipScreenshotAfterStep = false;

	}


}
