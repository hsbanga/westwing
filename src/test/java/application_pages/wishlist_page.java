package application_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.UtilsManager;

public class wishlist_page {

	private UtilsManager utilsManager;

	public wishlist_page(WebDriver driver, UtilsManager utilsManager) {
		PageFactory.initElements(driver, this);
		this.utilsManager = utilsManager;
	}

	// Locators Segment
	@FindBy(how = How.XPATH, using = "//*[@id=\"onetrust-accept-btn-handler\"]")
	WebElement accept_cookies;

	@FindBy(how = How.XPATH, using = "//header/div[6]/div[1]/form[1]/input[1]")
	WebElement textbox_productSearch;

	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Möbel')]")
	WebElement product_page_title;

	@FindBy(how = How.XPATH, using = "//body/div[@id='app-root']/div[1]/div[1]/div[7]/div[1]/div[1]/main[1]/div[1]/div[2]/div[1]/a[1]/div[1]/div[2]/div[2]/div[1]")
	WebElement first_Product_wishlist;

	@FindBy(how = How.XPATH, using = "//div[contains(@data-testid, 'login_and_register')]")
	WebElement login_overlay;

	@FindBy(how = How.XPATH, using = "//button[contains(@data-testid, 'login_reg_switch_btn')]")
	WebElement button_loginInHere;

	@FindBy(how = How.XPATH, using = "//*[@name='email'] ")
	WebElement textbox_Email;
	
	@FindBy(how = How.XPATH, using = "//*[@name='password'] ")
	WebElement textbox_password;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Anmelden')]")
	WebElement button_login;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@data-testid, 'wishlist-counter')]")
	WebElement icon_wishlist;
	
	@FindBy(how = How.XPATH, using = "//body/div[@id='pageContainer']/div[@id='page']/div[@id='content']/div[3]/div[1]/div[1]/div[4]/div[1]/ul[1]/li[1]/button[1]")
	WebElement icon_remove;
	
	
	

	// Action Segment

	public void setProductSearchBox(String searchTerm) throws InterruptedException {
		
		utilsManager.seleniumUtils.clickElement(accept_cookies);
		utilsManager.seleniumUtils.setElement(textbox_productSearch, searchTerm);
		Thread.sleep(4000);
		utilsManager.seleniumUtils.pressEnter("//header/div[6]/div[1]/form[1]/input[1]");
	}

	public void assertSearchTerm() {
		utilsManager.seleniumUtils.isElementPresent(product_page_title, 20);
	}

	public void clickWishlistButton() {
		utilsManager.seleniumUtils.clickElement(first_Product_wishlist);
	}

	public void assertLoginOverlay() {
		utilsManager.seleniumUtils.isElementPresent(login_overlay, 20);
	}

	public void clickLoginHereButton() {
		utilsManager.seleniumUtils.clickElement(button_loginInHere);
	}

	public void setemail(String email) {
		utilsManager.seleniumUtils.setElement(textbox_Email, email);
	}

	public void setPassword(String password) {
		utilsManager.seleniumUtils.setElement(textbox_password, password);
	}

	public void clickLoginButton() {
		utilsManager.seleniumUtils.clickElement(button_login);
	}
	
	public void assertWishlistIconCounter() {
		Assert.assertEquals(1,utilsManager.seleniumUtils.jsExecutorGetText(icon_wishlist));
	}

	public void goToWhishlistPage() {
		utilsManager.seleniumUtils.loadUrl("https://www.westwingnow.de/customer/wishlist/");
	}
	
	
	public void deleteAddedItem() {
		utilsManager.seleniumUtils.clickElement(icon_remove);
	}
	
	
	
}