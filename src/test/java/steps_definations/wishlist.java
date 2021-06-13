package steps_definations;

import application_pages.wishlist_page;
import io.cucumber.java.en.*;
import utilities.UtilsManager;

public class wishlist {
	private UtilsManager utilsManager;
	private wishlist_page wishlistPage;
	String search1 = null;

	public wishlist(UtilsManager utilsManager) {
		this.utilsManager = utilsManager;
		wishlistPage = new wishlist_page(utilsManager.seleniumUtils.getDriver(), utilsManager);

	}

	@Given("^I am on the WestwingNow home page$")
	public void i_am_on_the_westwingnow_home_page() throws Throwable {
		utilsManager.seleniumUtils.loadUrl(utilsManager.javaUtils.get_Property("baseURL"));
	}

	@When("I search for {string}")
	public void i_search_for(String searchTerm) throws InterruptedException {
		wishlistPage.setProductSearchBox(searchTerm);
		search1 = searchTerm;
	}

	@Then("I should see product listing page with a list of products")
	public void i_should_see_product_listing_page_with_a_list_of_products() {
		wishlistPage.assertSearchTerm();
	}

	@When("I click on wishlist icon of the first found product")
	public void i_click_on_wishlist_icon_of_the_first_found_product() {
		wishlistPage.clickWishlistButton();
	}

	@Then("I should see the login or registration overlay")
	public void i_should_see_the_login_or_registration_overlay() {
		wishlistPage.assertLoginOverlay();
	}

	@When("I switch to login form of the overlay")
	public void i_switch_to_login_form_of_the_overlay() {
		wishlistPage.clickLoginHereButton();
	}

	@When("I log in with email {string} and password {string} credentials")
	public void i_log_in_with_email_and_password_credentials(String email, String password) {
		wishlistPage.setemail(email);
		wishlistPage.setPassword(password);
		wishlistPage.clickLoginButton();
	}
	
	@Then("^the product should be added to the wishlist$")
	public void the_product_should_be_added_to_the_wishlist() throws Throwable {
		wishlistPage.assertWishlistIconCounter();
	}

	@And("^I go to the wishlist page$")
	public void i_go_to_the_wishlist_page() throws Throwable {
		wishlistPage.goToWhishlistPage();;
	}

	@And("^I delete the product from my wishlist$")
	public void i_delete_the_product_from_my_wishlist() throws Throwable {
		wishlistPage.deleteAddedItem();
	}

}
