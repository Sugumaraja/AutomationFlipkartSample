package zh.qa.webapp.test;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.testng.AllureTestNg;
import zh.qa.webapp.baseTest.BaseTest;

@Listeners(AllureTestNg.class)
@Epic("Search Bar in Homepage")
@Story("The UI And Functions of search bar")
@Feature("Searching products in the Homepage")
public class SearchPageTest extends BaseTest{
@DataProvider
public Object[][] searchContent() {
return new Object[][] {{"watch","digital"},{"belt","women"}};//,{"wallet","leather"},{"cooker","Leo"}
}
//@DataProvider(name="searchContent")
//@BeforeTest (dataProvider = "searchContent")
//public void serachPageSetup(String searchKey) {
//	homepage.navigateToSrchResultPage(searchKey);
//}
@Description("To Validate the Multiple searching with product name: {0} and check any product title containing the passing value:{1} ")
@Test(dataProvider = "searchContent")	
public void validateTheResultTitleTest(String searchKey,String searchResult) {
	srchpage=homepage.navigateToSrchResultPage(searchKey);
	boolean Title=srchpage.validateSearchResult(searchResult);
	Assert.assertTrue(Title, "some content are not getting relevant search key in title");
}

}
