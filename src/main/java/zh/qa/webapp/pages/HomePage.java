package zh.qa.webapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import zh.qa.webapp.constants.AppConstants;
import zh.qa.webapp.utils.EleUtil;

@Listeners(AllureTestNg.class)
public class HomePage {
	private WebDriver driver;
	private EleUtil eleutil;
	private By loginButton = By.cssSelector(".H6-NpN a[title='Login'] span");
	private By ReuestOTPbtn = By.cssSelector("button._2KpZ6l");
	private By loginPageContent = By.cssSelector("p._1-pxlW span");
	private By searchBar = By.name("q");
	private By searchBtn = By.cssSelector("button[type='submit']");
	private By gitelen = By.cssSelector("giter']");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleutil = new EleUtil(this.driver);
	}
@Step("Navigating to the Login page while click on {0} ")
	public String isLoginButtonNavigating() {
		eleutil.clickwithWait(loginButton, AppConstants.SHORT_WAIT_TIME);
		String getContent = eleutil.waitforVisibleofEle(loginPageContent, AppConstants.SHORT_WAIT_TIME).getText();
		return getContent;
	}
@Step("Chekcing the OTP button displaying in the screen adn returning boolean")
	public boolean isOTPbtnPresent() {

		return eleutil.waitforPresence(ReuestOTPbtn, AppConstants.SHORT_WAIT_TIME).isDisplayed();
	}
@Step("getting the login page content and return it")
	public String isOTPbtnContentCheck() {
		return eleutil.waitforPresence(ReuestOTPbtn, AppConstants.SHORT_WAIT_TIME).getText();
	}
@Step("navigating to the search result page based on the key: {0} entered")
	public SearchResultPage navigateToSrchResultPage(String searchKey) {
		eleutil.sendText(searchBar, searchKey);
		eleutil.clickElement(searchBtn);
		return new SearchResultPage(driver);

	}
}
