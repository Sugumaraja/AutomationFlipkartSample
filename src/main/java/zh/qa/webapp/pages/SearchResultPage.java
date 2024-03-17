package zh.qa.webapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;

import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import zh.qa.webapp.constants.AppConstants;
import zh.qa.webapp.utils.EleUtil;

@Listeners(AllureTestNg.class)
public class SearchResultPage {
	WebDriver driver;
	EleUtil eleutil;
	private By resultTitles = By.xpath("//div[@class='_1YokD2 _3Mn1Gg']/div[@class]//a[@title]");//("//div[@class='_1YokD2 _3Mn1Gg']//div[@class='_2B099V']/a[@title]");
//	private By resultPrice = By.xpath("//div[@class='_1YokD2 _3Mn1Gg']//div[@class='_2B099V']/a[@class='_3bPFwb']");
	private By searchBar = By.name("q");

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new EleUtil(driver);
	}
	@Step("will check the result page if the text: {0} available in the result title will return true")
	public boolean validateSearchResult(String srchResultKey) {
		boolean flag = eleutil.isSearchResultInTitles(resultTitles, srchResultKey, AppConstants.MED_WAIT_TIME);
		WebElement searchBarEle=eleutil.getElement(searchBar);
		String textInField=searchBarEle.getAttribute("value");
		eleutil.ClearTextByBackspace(textInField, searchBarEle);
		return flag;
	}
}
