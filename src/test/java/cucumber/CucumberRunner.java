/**
 * @author Harjinder Singh Banga
 * @email hsbanga@yahoo.com
 * @create date 08-June-2021 
 * @modify By:  date:
 * 
 * @desc This Class implemented for running the cucumber test using TestNG Methods annotations and Abstract Class and contains the Cucumber Options.
 */

package cucumber;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utilities.JavaUtils;
import utilities.SeleniumUtils;

@CucumberOptions(features = { "src/test/resources/feature_files" }, glue = {
		"steps_definations" }, tags = "@westwingnow", monochrome = true, dryRun = false, plugin = { "pretty",
				"timeline:target/cucumber-parallel-report", "testng:target/testng-cucumber-reports/testngReport.xml",
				"json:target/cucumberReport.json", "junit:target/cucumber-reports/Cucumber.xml" })
public class CucumberRunner extends AbstractTestNGCucumberTests {

	// Variables/Objects declaration
	private static boolean doUploadQmetryReport = Boolean
			.parseBoolean(JavaUtils.javaUtilsSelfObj.getSystemProperty("UPLOAD_QMETRY_REPORT", "false").toLowerCase());

	/**
	 * This method, overriding the scenarios method to execute all the scenarios in
	 * parallel with by default 10 threads count by setting the dataProvider
	 * parallel option to true. or keep it false to execute in sequence.
	 *
	 */
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {

		return super.scenarios();
	}

	/**
	 * This Method is created to do the initial one time setup for entire test
	 * execution. Example: Loading the Properties file or connecting to database
	 * etc.
	 *
	 */
	@BeforeClass
	private void initSetup(ITestContext context) throws IOException {

		JavaUtils.javaUtilsSelfObj.load_Properties();
		System.out.println("Test Environment: " + JavaUtils.envName);
		System.out.println("Test Browser: " + SeleniumUtils.browser);
		System.out.println("Maximum Parallel Thread Count: "
				+ context.getCurrentXmlTest().getSuite().getDataProviderThreadCount());

	}

	/**
	 * This Method is created to take the necessary action after completing the
	 * entire test execution. Example: Closing the database connection etc.
	 *
	 */
	@AfterClass
	private void finalTearDown() {

	}
}
